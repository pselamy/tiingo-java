package com.github.pselamy.tiingo.api.rest;

import com.github.pselamy.tiingo.api.rest.RestClient.RestClientException;
import com.github.pselamy.tiingo.api.testing.FakeHttpRequestTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.testing.junit.testparameterinjector.TestParameter;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URI;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(TestParameterInjector.class)
public class RestClientTest {
  @Inject private Gson gson;

  @Before
  public void setUp() {
    Guice.createInjector(RestClientModule.builder().build()).injectMembers(this);
  }

  @Test
  public void get_formsCorrectUrl(@TestParameter GetFormsCorrectUrlTestCase testCase)
      throws RestClientException {
    // Arrange
    FakeHttpRequestTransport requestTransport = FakeHttpRequestTransport.create(testCase.response);
    HttpRequestFactory requestFactory = requestTransport.createRequestFactory();
    URI basePath = URI.create(testCase.baseUrl);
    RestClient restClient =
        RestClient.builder()
            .setBasePath(basePath)
            .setGson(gson)
            .setRequestFactory(requestFactory)
            .build();
    RestClient.GetParams<FakeResponse> getParams =
        RestClient.GetParams.<FakeResponse>builder()
            .setResource(testCase.resource)
            .setResponseType(FakeResponse.class)
            .build();

    // Act
    restClient.get(getParams);
    ImmutableMap<String, String> actual = requestTransport.requests().build();

    // Assert
    assertThat(actual).isEqualTo(testCase.expectedRequests);
  }

  @Test
  public void get_returnsExpectedResult(@TestParameter GetReturnsExpectedResultTestCase testCase)
      throws RestClientException {
    // Arrange
    HttpRequestFactory requestFactory =
        FakeHttpRequestTransport.create(testCase.response).createRequestFactory();
    RestClient restClient =
        RestClient.builder()
            .setBasePath(URI.create("http://website.com"))
            .setGson(gson)
            .setRequestFactory(requestFactory)
            .build();
    RestClient.GetParams<FakeResponse> getParams =
        RestClient.GetParams.<FakeResponse>builder()
            .setResource(testCase.resource)
            .setResponseType(FakeResponse.class)
            .build();

    // Act
    FakeResponse actual = restClient.get(getParams);

    // Assert
    assertThat(actual).isEqualTo(testCase.expectedResult);
  }

  @Test
  public void get_throwsRestClientException(
      @TestParameter GetThrowsRestClientExceptionTestCase testCase) throws RestClientException {
    // Arrange
    HttpRequestFactory requestFactory =
        FakeHttpRequestTransport.create(testCase.response).createRequestFactory();
    RestClient restClient =
        RestClient.builder()
            .setBasePath(URI.create("http://website.com"))
            .setGson(gson)
            .setRequestFactory(requestFactory)
            .build();
    RestClient.GetParams<FakeResponse> getParams =
        RestClient.GetParams.<FakeResponse>builder()
            .setResource(testCase.resource)
            .setResponseType(FakeResponse.class)
            .build();

    // Act / Assert
    assertThrows(RestClientException.class, () -> restClient.get(getParams));
  }

  enum GetFormsCorrectUrlTestCase {
    SIMPLE(
        "http://website.com/",
        "resource",
        new MockLowLevelHttpResponse().setContent("{\"value1\":\"value1\",\"value2\":\"value2\"}"),
        ImmutableMap.of("GET", "http://website.com/resource")),
    NO_TRAILING_SLASH(
        "http://website.com",
        "resource",
        new MockLowLevelHttpResponse().setContent("{\"value1\":\"value1\",\"value2\":\"value2\"}"),
        ImmutableMap.of("GET", "http://website.com/resource")),
    TRAILING_SLASH_AND_LEADING_SLASH(
        "http://website.com/",
        "/resource",
        new MockLowLevelHttpResponse().setContent("{\"value1\":\"value1\",\"value2\":\"value2\"}"),
        ImmutableMap.of("GET", "http://website.com/resource")),
    LEADING_SLASH(
        "http://website.com",
        "/resource",
        new MockLowLevelHttpResponse().setContent("{\"value1\":\"value1\",\"value2\":\"value2\"}"),
        ImmutableMap.of("GET", "http://website.com/resource"));

    private final String baseUrl;

    private final String resource;
    private final MockLowLevelHttpResponse response;
    private final ImmutableMap<String, String> expectedRequests;

    GetFormsCorrectUrlTestCase(
        String baseUrl,
        String resource,
        MockLowLevelHttpResponse response,
        ImmutableMap<String, String> expectedRequests) {
      this.baseUrl = baseUrl;
      this.resource = resource;
      this.response = response;
      this.expectedRequests = expectedRequests;
    }
  }

  enum GetReturnsExpectedResultTestCase {
    SUCCESS_1(
        "resource-1",
        new MockLowLevelHttpResponse()
            .addHeader("custom_header", "value")
            .setStatusCode(200)
            .setContentType(Json.MEDIA_TYPE)
            .setContent("{\"value1\":\"value1\",\"value2\":\"value2\"}"),
        FakeResponse.create("value1", "value2")),
    SUCCESS_2(
        "resource-2",
        new MockLowLevelHttpResponse()
            .addHeader("custom_header", "value")
            .setStatusCode(200)
            .setContentType(Json.MEDIA_TYPE)
            .setContent("{\"value1\":\"value-one\",\"value2\":\"value-two\"}"),
        FakeResponse.create("value-one", "value-two"));

    private final String resource;
    private final LowLevelHttpResponse response;
    private final FakeResponse expectedResult;

    GetReturnsExpectedResultTestCase(
        String resource, LowLevelHttpResponse response, FakeResponse expectedResult) {
      this.resource = resource;
      this.response = response;
      this.expectedResult = expectedResult;
    }
  }

  enum GetThrowsRestClientExceptionTestCase {
    NOT_FOUND(
        "resource-3",
        new MockLowLevelHttpResponse()
            .addHeader("custom_header", "value")
            .setStatusCode(404)
            .setContentType(Json.MEDIA_TYPE)
            .setContent("{\"error\":\"not found\"}")),
    INTERNAL_SERVER_ERROR(
        "resource-4",
        new MockLowLevelHttpResponse()
            .addHeader("custom_header", "value")
            .setStatusCode(500)
            .setContentType(Json.MEDIA_TYPE)
            .setContent("{\"error\":\"internal server error\"}"));

    private final String resource;
    private final LowLevelHttpResponse response;

    GetThrowsRestClientExceptionTestCase(String resource, LowLevelHttpResponse response) {
      this.resource = resource;
      this.response = response;
    }
  }

  @AutoValue
  @GenerateTypeAdapter
  public abstract static class FakeResponse {
    private static FakeResponse create(String value1, String value2) {
      return new AutoValue_RestClientTest_FakeResponse(value1, value2);
    }

    abstract String value1();

    abstract String value2();
  }
}

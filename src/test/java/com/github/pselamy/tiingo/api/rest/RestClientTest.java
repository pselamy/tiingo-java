package com.github.pselamy.tiingo.api.rest;

import com.github.pselamy.tiingo.api.rest.RestClient.RestClientException;
import com.google.api.client.http.HttpRequestFactory;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(TestParameterInjector.class)
public class RestClientTest {
  private static final String BASE_PATH = "https://api.tiingo.com";

  private Gson gson;

  private HttpRequestFactory requestFactory;

  @Before
  public void setUp() throws Exception {}

  @Test
  public void get_returnsExpectedResult() throws RestClientException {
    // Arrange
    RestClient restClient =
        RestClient.builder().setGson(gson).setRequestFactory(requestFactory).build();
    RestClient.GetParams<FakeResponse> getParams =
        RestClient.GetParams.<FakeResponse>builder().setResponseType(FakeResponse.class).build();

    // Act
    FakeResponse actual = restClient.get(getParams);
  }

  @AutoValue
//  @GenerateTypeAdapter
  abstract static class FakeResponse {
    abstract String field1();

    abstract String field2();
  }
}

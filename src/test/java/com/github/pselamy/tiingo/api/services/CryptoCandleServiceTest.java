package com.github.pselamy.tiingo.api.services;

import com.github.pselamy.tiingo.api.models.ApiKey;
import com.github.pselamy.tiingo.api.models.CandleResponse;
import com.github.pselamy.tiingo.api.models.Granularity;
import com.github.pselamy.tiingo.api.rest.RestClient.RestClientException;
import com.github.pselamy.tiingo.api.rest.RestClientModule;
import com.github.pselamy.tiingo.api.services.CandleService.GetCandlesRequest;
import com.github.pselamy.tiingo.api.testing.FakeHttpRequestTransport;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.testing.junit.testparameterinjector.TestParameter;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.function.Supplier;

import static com.google.common.truth.Truth.assertThat;

@RunWith(TestParameterInjector.class)
public class CryptoCandleServiceTest {
  @Bind
  private static final ApiKey API_KEY = ApiKey.create("98908a4d329d2781e2c1cdcd51cb9dbb4c090705");

  private HttpRequestFactory httpRequestFactory;

  private final Supplier<HttpRequestFactory> httpRequestFactorySupplier = () -> httpRequestFactory;

  private LowLevelHttpResponse response = new MockLowLevelHttpResponse();

  private final Supplier<LowLevelHttpResponse> responseSupplier = () -> response;

  @Inject private Gson gson;
  @Inject private CryptoCandleService service;

  @Before
  public void setUp() {
    Guice.createInjector(
            BoundFieldModule.of(this),
            RestClientModule.builder()
                .requestFactory(
                    FakeHttpRequestTransport.create(responseSupplier).createRequestFactory())
                .build())
        .injectMembers(this);
  }

  @Test
  public void getCandles_returnsCandles(@TestParameter GetCandlesReturnsCandlesTestCase testCase)
      throws RestClientException {
    // Arrange
    response = testCase.response;

    GetCandlesRequest request =
        GetCandlesRequest.builder()
            .addTicker("BTCUSD")
            .endDate(Instant.now())
            .granularity(Granularity.FIVE_MINUTES)
            .build();

    // Act
    ImmutableList<CandleResponse> actual = service.getCandles(request);

    // Assert
    assertThat(actual).isNotEmpty();
  }

  enum GetCandlesReturnsCandlesTestCase {
    ONE_TICKER(
        new MockLowLevelHttpResponse()
            .setContent(
                "[{\"ticker\":\"btcusd\",\"baseCurrency\":\"btc\",\"quoteCurrency\":\"usd\",\"priceData\":[{\"date\":\"2022-10-17T00:00:00+00:00\",\"open\":19262.877667487857,\"high\":19301.817315405588,\"low\":19252.03112169749,\"close\":19295.423596363737,\"volume\":966.9304131950028,\"volumeNotional\":18657331.910804592,\"tradesDone\":20726.0},{\"date\":\"2022-10-17T00:05:00+00:00\",\"open\":19293.701653663975,\"high\":19309.12763475489,\"low\":19280.168227895894,\"close\":19292.340347366375,\"volume\":740.1965513460019,\"volumeNotional\":14280123.79251392,\"tradesDone\":15647.0}]}]"));

    private final LowLevelHttpResponse response;

    GetCandlesReturnsCandlesTestCase(LowLevelHttpResponse response) {
      this.response = response;
    }
  }
}

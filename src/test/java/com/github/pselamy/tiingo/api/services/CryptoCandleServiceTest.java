package com.github.pselamy.tiingo.api.services;

import com.github.pselamy.tiingo.api.models.ApiKey;
import com.github.pselamy.tiingo.api.models.CandleResponse;
import com.github.pselamy.tiingo.api.models.Granularity;
import com.github.pselamy.tiingo.api.rest.RestClientModule;
import com.github.pselamy.tiingo.api.services.CandleService.GetCandlesRequest;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.testing.junit.testparameterinjector.TestParameterInjector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.Instant;

import static com.google.common.truth.Truth.assertThat;

@RunWith(TestParameterInjector.class)
public class CryptoCandleServiceTest {
  @Bind
  private static final ApiKey API_KEY = ApiKey.create("test-api-key");

  @Inject private CryptoCandleService service;

  @Before
  public void setUp() {
    Guice.createInjector(BoundFieldModule.of(this), RestClientModule.create()).injectMembers(this);
  }

  @Test
  public void getCandles_returnsCandles() {
    // Arrange
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
}

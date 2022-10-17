package com.github.pselamy.tiingo.api.services;

import com.github.pselamy.tiingo.api.models.ApiKey;
import com.github.pselamy.tiingo.api.models.AssetType;
import com.github.pselamy.tiingo.api.models.Candle;
import com.github.pselamy.tiingo.api.models.CandleResponse;
import com.github.pselamy.tiingo.api.rest.RestClient;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

class CryptoCandleService implements CandleService {
  private static final Joiner COMMA_JOINER = Joiner.on(",");
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_INSTANT;

  private static final String REST_PATH = "tiingo/crypto/prices";

  private final ApiKey apiKey;
  private final RestClient restClient;

  @Inject
  CryptoCandleService(ApiKey apiKey, RestClient restClient) {
    this.apiKey = apiKey;
    this.restClient = restClient;
  }

  @Override
  public ImmutableList<CandleResponse> getCandles(GetCandlesRequest getCandlesRequest) {
    String resampleFrequency = getCandlesRequest.granularity().minutes() + "min";
    RestClient.GetParams.Builder<ImmutableList<CandleResponse>> paramsBuilder =
        RestClient.GetParams.<ImmutableList<CandleResponse>>builder()
            .addParam("resampleFreq", resampleFrequency)
            .addParam("tickers", COMMA_JOINER.join(getCandlesRequest.tickers()))
            .addParam("token", apiKey.get())
            .setResource(REST_PATH)
            .setResponseType(new TypeToken<ImmutableList<CandleResponse>>() {}.getType());
    getCandlesRequest
        .startDate()
        .ifPresent(
            startDate ->
                paramsBuilder.addParam("startDate", DATE_TIME_FORMATTER.format(startDate)));
    getCandlesRequest
        .endDate()
        .ifPresent(
            endDate -> paramsBuilder.addParam("endDate", DATE_TIME_FORMATTER.format(endDate)));
    RestClient.GetParams<ImmutableList<CandleResponse>> params = paramsBuilder.build();
    return ImmutableList.copyOf(restClient.get(params));
  }

  @Override
  public AssetType assetType() {
    return AssetType.CRYPTO;
  }
}

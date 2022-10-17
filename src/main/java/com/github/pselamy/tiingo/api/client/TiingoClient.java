package com.github.pselamy.tiingo.api.client;

import com.github.pselamy.tiingo.api.models.AssetType;
import com.github.pselamy.tiingo.api.services.CandleService;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

public class TiingoClient {
  private final ImmutableMap<AssetType, CandleService> candleServices;

  @Inject
  TiingoClient(ImmutableMap<AssetType, CandleService> candleServices) {
    this.candleServices = candleServices;
  }

  public CandleService getCandleService(AssetType assetType) {
    return candleServices.get(assetType);
  }
}

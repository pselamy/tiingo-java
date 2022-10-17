package com.github.pselamy.tiingo.api.services;

import com.github.pselamy.tiingo.api.models.AssetType;
import com.github.pselamy.tiingo.api.rest.RestClientModule;
import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;

import java.util.Set;

public class ServicesModule extends AbstractModule {
  @Override
  protected void configure() {
    Multibinder<CandleService> candleServiceBinder =
        Multibinder.newSetBinder(binder(), CandleService.class);
    candleServiceBinder.addBinding().to(CryptoCandleService.class);

    install(RestClientModule.create());
  }

  @Provides
  ImmutableMap<AssetType, CandleService> candleServiceMap(
      Set<CandleService> services) {
    ImmutableMap.Builder<AssetType, CandleService> builder = ImmutableMap.builder();
    services.forEach(service -> builder.put(service.assetType(), service));
    return builder.build();
  }
}

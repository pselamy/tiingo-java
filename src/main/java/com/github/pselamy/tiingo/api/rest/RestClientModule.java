package com.github.pselamy.tiingo.api.rest;

import com.github.pselamy.tiingo.api.models.GsonModule;
import com.github.pselamy.tiingo.api.models.GsonModule.Adapter;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import java.net.URI;

@AutoValue
public abstract class RestClientModule extends AbstractModule {
  public static Builder builder() {
    return new AutoValue_RestClientModule.Builder()
        .requestFactory(new NetHttpTransport().createRequestFactory());
  }

  public static RestClientModule create() {
    return builder().build();
  }

  abstract ImmutableList<Adapter<?>> adapters();

  abstract HttpRequestFactory requestFactory();

  @Override
  protected void configure() {
    GsonModule.Builder gsonModuleBuilder = GsonModule.builder();
    adapters().forEach(gsonModuleBuilder::addAdapter);

    install(gsonModuleBuilder.build());
  }

  @Provides
  RestClient providesRestClient(Gson gson) {
    return RestClient.builder()
        .setGson(gson)
        .setBasePath(URI.create("https://api.tiingo.com"))
        .setRequestFactory(requestFactory())
        .build();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    abstract ImmutableList.Builder<Adapter<?>> adaptersBuilder();

    public Builder addAdapter(Adapter<?> adapter) {
      adaptersBuilder().add(adapter);
      return this;
    }

    public abstract Builder requestFactory(HttpRequestFactory requestFactory);

    public abstract RestClientModule build();
  }
}

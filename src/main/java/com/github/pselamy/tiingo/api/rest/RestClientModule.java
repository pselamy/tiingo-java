package com.github.pselamy.tiingo.api.rest;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.lang.reflect.Type;
import java.net.URI;

@AutoValue
public abstract class RestClientModule extends AbstractModule {
  public static Builder builder() {
    return new AutoValue_RestClientModule.Builder();
  }

  abstract ImmutableMap<Type, TypeAdapter<?>> typeAdapters();

  @Override
  protected void configure() {
    super.configure();
  }

  @Provides
  Gson providesGson() {
    GsonBuilder gsonBuilder =
        new GsonBuilder().registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY);
    typeAdapters().forEach(gsonBuilder::registerTypeAdapter);
    return gsonBuilder.create();
  }

  @Provides
  RestClient providesRestClient(Gson gson) {
    return RestClient.builder()
        .setGson(gson)
        .setBasePath(URI.create("https://api.tiingo.com"))
        .build();
  }

  interface Adapter<T> {
    Class<T> type();

    TypeAdapter<T> typeAdapter();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    abstract ImmutableMap.Builder<Type, TypeAdapter<?>> typeAdaptersBuilder();

    public Builder addAdapter(Adapter<?> adapter) {
      typeAdaptersBuilder().put(adapter.type(), adapter.typeAdapter());
      return this;
    }

    public abstract RestClientModule build();
  }
}

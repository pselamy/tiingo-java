package com.github.pselamy.tiingo.api.models;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

import java.lang.reflect.Type;

@AutoValue
public abstract class GsonModule extends AbstractModule {
  public static Builder builder() {
    return new AutoValue_GsonModule.Builder();
  }

  public static GsonModule create() {
    return builder().build();
  }

  abstract ImmutableMap<Type, TypeAdapter<?>> typeAdapters();

  @Override
  protected void configure() {
    super.configure();
  }

  @Provides
  Gson providesGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapterFactory(GenerateTypeAdapter.FACTORY);
    typeAdapters().forEach(gsonBuilder::registerTypeAdapter);
    return gsonBuilder.create();
  }

  public interface Adapter<T> {
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

    public abstract GsonModule build();
  }
}

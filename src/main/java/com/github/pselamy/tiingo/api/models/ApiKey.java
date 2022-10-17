package com.github.pselamy.tiingo.api.models;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ApiKey {
  public static ApiKey create(String apiKey) {
    return new AutoValue_ApiKey(apiKey);
  }

  public abstract String get();
}

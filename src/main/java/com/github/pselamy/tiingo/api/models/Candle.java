package com.github.pselamy.tiingo.api.models;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;

@AutoValue
public abstract class Candle {
  public static Builder builder() {
    return new AutoValue_Candle.Builder();
  }

  public abstract BigDecimal open();

  public abstract BigDecimal high();

  public abstract BigDecimal low();

  public abstract BigDecimal close();

  public abstract BigDecimal volume();

  public abstract String date();

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder open(BigDecimal open);

    public abstract Builder high(BigDecimal high);

    public abstract Builder low(BigDecimal low);

    public abstract Builder close(BigDecimal close);

    public abstract Builder volume(BigDecimal volume);

    public abstract Builder date(String date);

    public abstract Candle build();
  }
}

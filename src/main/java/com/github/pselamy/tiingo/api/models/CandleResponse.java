package com.github.pselamy.tiingo.api.models;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;
import java.util.ArrayList;
import java.util.Collection;

@AutoValue
@GenerateTypeAdapter
public abstract class CandleResponse {
  public static CandleResponse create(String ticker, Collection<Candle> candles) {
    return new AutoValue_CandleResponse(ticker, new ArrayList<>(candles));
  }

  public abstract String ticker();

  @SerializedName("priceData")
  public abstract ArrayList<Candle> candles();
}

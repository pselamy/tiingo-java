package com.github.pselamy.tiingo.api.models;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

import java.util.ArrayList;

@AutoValue
@GenerateTypeAdapter
public abstract class CandleResponse {
  public abstract String ticker();

  @SerializedName("priceData")
  public abstract ArrayList<Candle> candles();
}

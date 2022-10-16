package com.github.pselamy.tiingo.api.models;

import java.math.BigDecimal;
import java.time.Instant;

public abstract class Candle {
  public abstract BigDecimal open();

  public abstract BigDecimal high();

  public abstract BigDecimal low();

  public abstract BigDecimal close();

  public abstract BigDecimal volume();

  public abstract Instant start();
}

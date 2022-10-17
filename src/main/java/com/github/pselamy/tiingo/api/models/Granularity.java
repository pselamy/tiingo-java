package com.github.pselamy.tiingo.api.models;

import java.time.Duration;

@SuppressWarnings("unused")
public enum Granularity {
  ONE_MINUTE(60),
  FIVE_MINUTES(300),
  FIFTEEN_MINUTES(900),
  THIRTY_MINUTES(1800),
  ONE_HOUR(3600),
  SIX_HOURS(21600),
  ONE_DAY(86400);

  private final int seconds;

  Granularity(int seconds) {
    this.seconds = seconds;
  }

  public Duration duration() {
    return Duration.ofSeconds(seconds);
  }

  public int minutes() {
    return seconds / 60;
  }
}

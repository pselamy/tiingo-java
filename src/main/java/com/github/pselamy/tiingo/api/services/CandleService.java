package com.github.pselamy.tiingo.api.services;

import com.github.pselamy.tiingo.api.models.AssetType;
import com.github.pselamy.tiingo.api.models.CandleResponse;
import com.github.pselamy.tiingo.api.models.Granularity;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import java.time.Instant;
import java.util.Optional;

public interface CandleService {
  ImmutableList<CandleResponse> getCandles(GetCandlesRequest getCandlesRequest);

  AssetType assetType();

  @AutoValue
  abstract class GetCandlesRequest {
    public static Builder builder() {
      return new AutoValue_CandleService_GetCandlesRequest.Builder();
    }

    abstract ImmutableSortedSet<String> tickers();

    abstract Optional<Instant> startDate();

    abstract Optional<Instant> endDate();

    abstract Granularity granularity();

    @AutoValue.Builder
    public abstract static class Builder {
      abstract ImmutableSortedSet.Builder<String> tickersBuilder();

      public Builder addTicker(String ticker) {
        tickersBuilder().add(ticker);
        return this;
      }

      public abstract Builder startDate(Instant startDate);

      public abstract Builder endDate(Instant endDate);

      public abstract Builder granularity(Granularity granularity);

      public Builder granularity(String granularity) {
        return this.granularity(Granularity.valueOf(granularity));
      }
      
      public abstract GetCandlesRequest build();
    }
  }
}

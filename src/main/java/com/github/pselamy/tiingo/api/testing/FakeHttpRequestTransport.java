package com.github.pselamy.tiingo.api.testing;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.auto.value.AutoValue;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMap;

import java.util.function.Supplier;

@AutoValue
public abstract class FakeHttpRequestTransport extends MockHttpTransport {
  public static FakeHttpRequestTransport create(Supplier<LowLevelHttpResponse> responseSupplier) {
    return new AutoValue_FakeHttpRequestTransport(ImmutableMap.builder(), responseSupplier);
  }

  public static FakeHttpRequestTransport create(LowLevelHttpResponse response) {
    return create(Suppliers.ofInstance(response));
  }

  public abstract ImmutableMap.Builder<String, String> requests();

  abstract Supplier<LowLevelHttpResponse> response();

  @Override
  public LowLevelHttpRequest buildRequest(String method, String url) {
    requests().put(method, url);
    return new MockLowLevelHttpRequest() {
      @Override
      public LowLevelHttpResponse execute() {
        return response().get();
      }
    };
  }
}

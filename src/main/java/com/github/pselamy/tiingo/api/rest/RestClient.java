package com.github.pselamy.tiingo.api.rest;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;

/** REST client for Tiingo API. */
@AutoValue
public abstract class RestClient {
  static Builder builder() {
    return new AutoValue_RestClient.Builder()
        .setBasePath(URI.create("https://api.tiingo.com"))
        .setRequestFactory(new NetHttpTransport().createRequestFactory());
  }

  abstract Gson gson();

  abstract HttpRequestFactory requestFactory();

  abstract URI basePath();

  <T> T get(GetParams<T> getParams) throws RestClientException {
    try {
      URI uri = basePath().resolve(getParams.resource());
      GenericUrl genericUrl = new GenericUrl(uri);
      String response = requestFactory().buildGetRequest(genericUrl).execute().parseAsString();
      return gson().fromJson(response, getParams.responseType());
    } catch (IOException e) {
      throw new RestClientException(e);
    }
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder setGson(Gson gson);

    public abstract Builder setRequestFactory(HttpRequestFactory requestFactory);

    public abstract Builder setBasePath(URI basePath);

    public abstract RestClient build();
  }

  @AutoValue
  public abstract static class GetParams<T> {
    public static <T> Builder<T> builder() {
      return new AutoValue_RestClient_GetParams.Builder<>();
    }

    abstract String resource();

    abstract ImmutableMap<String, String> headers();

    abstract ImmutableMap<String, String> queryParams();

    abstract Class<T> responseType();

    @AutoValue.Builder
    public abstract static class Builder<T> {
      abstract ImmutableMap.Builder<String, String> headersBuilder();

      abstract ImmutableMap.Builder<String, String> queryParamsBuilder();

      public Builder<T> addHeader(String key, String value) {
        headersBuilder().put(key, value);
        return this;
      }

      public Builder<T> addQueryParam(String key, String value) {
        queryParamsBuilder().put(key, value);
        return this;
      }

      public abstract Builder<T> setResource(String resource);

      public abstract Builder<T> setResponseType(Class<T> responseType);

      public abstract GetParams<T> build();
    }
  }

  static class RestClientException extends RuntimeException {
    RestClientException(Throwable cause) {
      super(cause);
    }
  }
}

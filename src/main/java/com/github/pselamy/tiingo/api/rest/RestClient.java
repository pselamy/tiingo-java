package com.github.pselamy.tiingo.api.rest;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequestFactory;
import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;

/** REST client for Tiingo API. */
@AutoValue
public abstract class RestClient {
  public static Builder builder() {
    return new AutoValue_RestClient.Builder();
  }

  abstract Gson gson();

  abstract HttpRequestFactory requestFactory();

  abstract URI basePath();

  public <T> T get(GetParams<T> getParams) throws RestClientException {
    try {
      String response =
          requestFactory()
              .buildGetRequest(createGenericUrl(getParams))
              .setHeaders(createHttpHeaders(getParams))
              .execute()
              .parseAsString();
      return gson().fromJson(response, getParams.responseType());
    } catch (IOException e) {
      throw new RestClientException(e);
    }
  }

  private <T> GenericUrl createGenericUrl(GetParams<T> getParams) {
    URI uri = createUri(getParams);
    GenericUrl genericUrl = new GenericUrl(uri);
    genericUrl.putAll(getParams.queryParams());
    return genericUrl;
  }

  private <T> HttpHeaders createHttpHeaders(GetParams<T> getParams) {
    HttpHeaders headers = new HttpHeaders();
    getParams.headers().forEach(headers::set);
    return headers;
  }

  private <T> URI createUri(GetParams<T> getParams) {
    String basePath = basePath().toString();
    String resource = getParams.resource();
    if (!basePath.endsWith("/")) {
      basePath += "/";
    }

    if (resource.startsWith("/")) {
      resource = resource.substring(1);
    }

    return URI.create(basePath).resolve(resource);
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

    abstract Type responseType();

    @AutoValue.Builder
    public abstract static class Builder<T> {
      abstract ImmutableMap.Builder<String, String> headersBuilder();

      abstract ImmutableMap.Builder<String, String> queryParamsBuilder();

      public Builder<T> addHeader(String key, String value) {
        headersBuilder().put(key, value);
        return this;
      }

      public Builder<T> addParam(String key, String value) {
        queryParamsBuilder().put(key, value);
        return this;
      }

      public abstract Builder<T> setResource(String resource);

      public abstract Builder<T> setResponseType(Type responseType);

      public abstract GetParams<T> build();
    }
  }

  public static class RestClientException extends RuntimeException {
    RestClientException(Throwable cause) {
      super(cause);
    }
  }
}

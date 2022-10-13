package com.github.pselamy.tiingo.api.client;

abstract class TiingoClient {
    abstract String apiKey();

    interface RestClient {
        String get(String url);
    }
}

package(default_visibility = [
    "//src/main/java/com/github/pselamy/tiingo:__subpackages__",
    "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
])

java_library(
    name = "candle_service",
    srcs = ["CandleService.java"],
    deps = [
        "//:autovalue",
        "//src/main/java/com/github/pselamy/tiingo/api/models:asset_type",
        "//src/main/java/com/github/pselamy/tiingo/api/models:candle",
        "//src/main/java/com/github/pselamy/tiingo/api/models:candle_response",
        "//src/main/java/com/github/pselamy/tiingo/api/models:granularity",
        "@maven//:com_google_guava_guava",
    ],
)

java_library(
    name = "crypto_candle_service",
    srcs = ["CryptoCandleService.java"],
    deps = [
        ":candle_service",
        "//src/main/java/com/github/pselamy/tiingo/api/models:api_key",
        "//src/main/java/com/github/pselamy/tiingo/api/models:asset_type",
        "//src/main/java/com/github/pselamy/tiingo/api/models:candle",
        "//src/main/java/com/github/pselamy/tiingo/api/models:candle_response",
        "//src/main/java/com/github/pselamy/tiingo/api/models:granularity",
        "//src/main/java/com/github/pselamy/tiingo/api/rest:rest_client",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_inject_guice",
    ],
)

java_library(
    name = "services_module",
    srcs = ["ServicesModule.java"],
    deps = [
        ":candle_service",
        ":crypto_candle_service",
        "//src/main/java/com/github/pselamy/tiingo/api/models:asset_type",
        "//src/main/java/com/github/pselamy/tiingo/api/rest:rest_client_module",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_inject_guice",
    ],
)

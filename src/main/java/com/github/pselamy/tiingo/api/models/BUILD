package(default_visibility = [
    "//src/main/java/com/github/pselamy/tiingo:__subpackages__",
    "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
])

java_library(
    name = "api_key",
    srcs = ["ApiKey.java"],
    deps = [
        "//:autovalue",
    ],
)

java_library(
    name = "asset_type",
    srcs = ["AssetType.java"],
    deps = [],
)

java_library(
    name = "candle",
    srcs = ["Candle.java"],
    deps = [
        "//:autovalue",
        "//:autovalue_gson",
    ],
)

java_library(
    name = "candle_response",
    srcs = ["CandleResponse.java"],
    deps = [
        ":candle",
        "//:autovalue",
        "//:autovalue_gson",
        "@maven//:com_google_guava_guava",
    ],
)

java_library(
    name = "granularity",
    srcs = ["Granularity.java"],
)

java_library(
    name = "gson_module",
    srcs = ["GsonModule.java"],
    visibility = [
        "//src/main/java/com/github/pselamy/tiingo:__subpackages__",
        "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
    ],
    deps = [
        "//:autovalue",
        "//:autovalue_gson",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_inject_guice",
    ],
)

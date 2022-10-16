java_library(
    name = "rest_client",
    srcs = ["RestClient.java"],
    visibility = [
        "//src/main/java/com/github/pselamy/tiingo:__subpackages__",
        "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
    ],
    deps = [
        "//:autovalue",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_http_client_google_http_client",
    ],
)

java_binary(
    name = "rest_client_module",
    srcs = ["RestClientModule.java"],
    visibility = [
        "//src/main/java/com/github/pselamy/tiingo:__subpackages__",
        "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
    ],
    deps = [
        ":rest_client",
        "//:autovalue",
        "//:autovalue_gson",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_inject_guice",
    ],
)

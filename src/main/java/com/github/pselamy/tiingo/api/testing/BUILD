package(default_visibility = [
    "//src/test/java/com/github/pselamy/tiingo:__subpackages__",
])

java_library(
    name = "fake_http_request_transport",
    testonly = 1,
    srcs = ["FakeHttpRequestTransport.java"],
    deps = [
        "//:autovalue",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_http_client_google_http_client",
    ],
)

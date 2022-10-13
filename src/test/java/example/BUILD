java_test(
    name = "hello_world_logger_junit4_test",
    srcs = ["HelloWorldLoggerJunit4Test.java"],
    test_class = "example.HelloWorldLoggerJunit4Test",
    deps = [
        "//src/main/java/example:hello_world",
        "//src/main/java/example:hello_world_logger",
        "//src/main/java/example:hello_world_module",
        "@maven//:com_google_inject_guice",
        "@maven//:com_google_truth_truth",
    ],
)

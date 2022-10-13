package(default_visibility = ["//:__subpackages__"])

java_binary(
    name = "hello_world",
    srcs = ["HelloWorld.java"],
    main_class = "HelloWorld",
    deps = [
        ":hello_world_logger",
        ":hello_world_module",
        "@maven//:com_google_inject_guice",
    ],
)

java_library(
    name = "hello_world_logger",
    srcs = ["HelloWorldLogger.java"],
    deps = [
        "@maven//:com_google_guava_guava",
    ],
)

java_library(
    name = "hello_world_logger_impl",
    srcs = ["HelloWorldLoggerImpl.java"],
    deps = [
        ":hello_world_logger",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_inject_guice",
    ],
)

java_library(
    name = "hello_world_module",
    srcs = ["HelloWorldModule.java"],
    deps = [
        ":hello_world_logger",
        ":hello_world_logger_impl",
        "@maven//:com_google_inject_guice",
    ],
)

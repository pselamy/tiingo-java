java_plugin(
    name = "autovalue_plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    deps = [
        "@maven//:com_google_auto_value_auto_value",
    ],
)

java_library(
    name = "autovalue",
    exported_plugins = [
        ":autovalue_plugin",
    ],
    neverlink = 1,
    visibility = ["//visibility:public"],
    exports = [
        "@maven//:com_google_auto_value_auto_value",
        "@maven//:com_google_auto_value_auto_value_annotations",
    ],
)

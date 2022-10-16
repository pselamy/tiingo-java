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

java_library(
    name = "autovalue_gson",
    exported_plugins = [
        ":autovalue_gson_plugin",
    ],
    neverlink = 1,
    visibility = ["//visibility:public"],
    exports = [
        "@maven//:com_ryanharter_auto_value_auto_value_gson",
        "@maven//:com_ryanharter_auto_value_auto_value_gson_annotations",
        "@maven//:com_ryanharter_auto_value_auto_value_gson_factory",
    ],
)

java_plugin(
    name = "autovalue_plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    deps = [
        "@maven//:com_google_auto_value_auto_value",
        "@maven//:com_ryanharter_auto_value_auto_value_gson_factory",
    ],
)

java_plugin(
    name = "autovalue_gson_plugin",
    processor_class = "com.ryanharter.auto.value.gson.factory.AutoValueGsonAdapterFactoryProcessor",
    deps = [
        "@maven//:com_ryanharter_auto_value_auto_value_gson",
    ],
)

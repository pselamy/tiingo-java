workspace(name = "java_binary_bazel_template")

load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")

######################
#### JAVA SUPPORT ####
######################
git_repository(
    name = "contrib_rules_jvm",
    commit = "f7c08ec6d73ef691b03f843e0c2c3dbe766df584",
    remote = "https://github.com/bazel-contrib/rules_jvm",
    shallow_since = "1642674503 +0000",
)

load("@contrib_rules_jvm//:repositories.bzl", "contrib_rules_jvm_deps")

contrib_rules_jvm_deps()

load("@contrib_rules_jvm//:setup.bzl", "contrib_rules_jvm_setup")

contrib_rules_jvm_setup()

git_repository(
    name = "rules_jvm_external",
    remote = "https://github.com/bazelbuild/rules_jvm_external",
    tag = "4.2",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "com.google.auto.value:autovalue:1.9",
        "com.google.auto.value:auto-value-annotations:1.9",
        "com.google.code.gson:gson:2.9.1",
        "com.google.guava:guava:31.1-jre",
        "com.google.http-client:google-http-client:1.42.2",
        "com.google.inject:guice:5.1.0",
        "com.google.testparameterinjector:test-parameter-injector:1.9",
        "com.google.truth:truth:1.1.3",
        "com.ryanharter.auto.value:auto-value-gson:1.3.1",
        "com.ryanharter.auto.value:auto-value-gson-annotations:0.8.0",
        "com.ryanharter.auto.value:auto-value-gson-factory:1.3.1",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

#######################
###### CORE LIBS ######
#######################
git_repository(
    name = "autovalue_bazel",
    commit = "70ac248bce76d9354ecd8af10dbb6cd20a2f53c9",
    remote = "https://github.com/pselamy/autovalue-bazel",
    shallow_since = "1642674503 +0000",
)

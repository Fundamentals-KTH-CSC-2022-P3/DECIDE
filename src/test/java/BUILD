package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

# Use java_test after bazel officially support junit5
java_junit5_test(
    name = "testsuite",
    srcs = glob([
        "GreetingTest.java",
        "CMVTest.java",
        "ParametersTest.java",
    ]),
    test_package = "com.example",
    deps = [
        "//src/main/java/com/example/greeter:greeter",
	    "//src/main/java/com/example/core:core",
    ],
)

package(
    default_visibility = ["//src/test:__subpackages__"],
)

load("//:junit5.bzl", "java_junit5_test")

# Use java_test after bazel officially support junit5
java_junit5_test(
    name = "testsuite",
    srcs = glob([
        "CMVTest.java",
    ]),
    test_package = "edu.dd2480.group14.decide",
    deps = [
	"//src/main/java/edu/dd2480/group14/decide/core:core",
    ],
)

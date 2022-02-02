# DECIDE

- [DECIDE](#decide)
  - [Summary](#summary)
  - [Installation](#installation)
  - [Build](#build)
    - [Run the testsuite](#run-the-testsuite)
    - [Configure for development with Intellij](#configure-for-development-with-intellij)
      - [About the java version](#about-the-java-version)
      - [IntelliJ IDEA](#intellij-idea)
      - [Bazel](#bazel)
      - [Configuration](#configuration)
        - [Importing Bazel Project](#importing-bazel-project)
        - [Run/Debug Configuration](#rundebug-configuration)
        - [Test Configuration](#test-configuration)
  - [Contribute](#contribute)
    - [Development workflow](#development-workflow)
    - [Statement Of Contributions](#statement-of-contributions)

Welcome to the first assignment in the course Software Engineering Fundamentals (DD2480).
We will get you up to speed with what this assignment is about and
what we have done. Furthermore, how to run and debug the program on your own
computer and run the test suite.

## Summary

The goal of this assignment is to implement a Launch Interceptor Program
from a given requirement specification.
Several steps need to be taken to correctly implement the program.
The most time-demanding steps were to implement 15 different Launch Interceptor Conditions (LICs).
In addition, creating unit tests for each LIC to ensure correctness.
While the assignment was about implementing a specific program,
the main purpose of the assignment was to learn to work with teams, git,
and other software methods and tools.

## Installation

TODO

## Build

TODO
### Run the testsuite

TODO
### Configure for development with Intellij

This section describes how to configure the project for development in IntelliJ.

This assumes the following setup

These tools are used in the project:

- Java 17
- IntelliJ IDEA
- Bazel
- Bazel For IntelliJ Plugin

#### About the java version

We'd recommend using the OpenJDK version of Java 17, though it should still work independently of JVM implementation.

#### IntelliJ IDEA

This project is created and coded with IntelliJ IDEA Ultimate. You can follow the link here for the
[license for the IDEA](https://www.jetbrains.com/community/education/#students), and then install it
through any package manager of your choice. Use your license to verify your copy.

#### Bazel

Install Bazel through a package manager, and you're ready to go.

#### Configuration

After cloning this repository, open the folder as a project in IntelliJ. Go to:

__Preferences -> Plugins__

and search for Bazel. Install the plugin Bazel by Google, and when prompted to restart your IDEA do so.

##### Importing Bazel Project

Go to __File -> Import Bazel Project...__ and choose the DECIDE folder as the Workspace. When prompted to import
project view, choose to build from BUILD file, and choose the file _DECIDE/BUILD_. Finish the import. This will start up
the Bazel Console, where syncing is underway. When this finishes, you should be able to see the Bazel logo in the upper
right corner.

##### Run/Debug Configuration

Go to the menu bar and choose __Run -> Edit Configurations...__.  Above the empty list of configurations, press the +.
Add a new run/debug configuration of type Bazel Command. Choose the target expression to be the same name as the main file,
_//:DECIDE_ and choose the Bazel command to be of type _Run_. Press Apply and you should now be able to both run
and debug the software.

##### Test Configuration

Go to the menu bar and choose __Run -> Edit Configurations...__.  Then, press the +.
Add a new test configuration of type Bazel Command. Choose the target expression to be _//src/test/java/decide:testsuite_,
and choose the Bazel command to be of type _Test_. Press Apply and you should now be able to run the test suite.

## Contribute

### Development workflow

Contributions should follow the following procedure

1. open an issue (if one doesn't already exist) that clearly describes what the feature/bug is
2. open a feature branch from `main` with the following syntax `issue-[ISSUE NUMBER]/slug-of-the-issue-title`
3. open a PR for merging the feature branch into main
4. only merges after one approval and all unit tests pass

### Statement Of Contributions

How the work load was separated between the group members, and who
did what can be seen in the list below.

- Ludwig Kristoffersson:
  - Setup build and testing environment
  - Add CI workflows
  - LIC 13
  - Documentation in tests
  - Code for running the actual program that should output "YES" or "NO"
- Arvid Siberov:
  - LIC 0
  - LIC 4
  - LIC 8
  - LIC 9
  - LIC 12
  - Verify parameter values
- Katrina Liang:
  - LIC 5
  - LIC 6
  - Create the FUV
- Marcus Alevärn:
  - LIC 2
  - LIC 3
  - LIC 7
  - LIC 11
  - LIC 14
  - Create the PUM
- Samuel Philipson:
  - Setup build and testing environment.
  - Code for checking if 3 points are on a circle
  - LIC 1
  - LIC 10


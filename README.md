# DECIDE

Welcome to the first assignment. Let's get you up to speed with what you'll have to do to run this project

## Setup

### Tools
These tools are used in the project:
- Java 17
- IntelliJ IDEA
- Bazel
- Bazel For IntelliJ Plugin

#### Java 17
We'd recommend using the OpenJDK version of Java 17, though it should still work independently of JVM implementation.

#### IntelliJ IDEA
This project is created and coded with IntelliJ IDEA Ultimate. You can follow the link here for the 
[license for the IDEA](https://www.jetbrains.com/community/education/#students), and then install it 
through any package manager of your choice. Use your license to verify your copy. 

#### Bazel
Install Bazel through a package manager, and you're ready to go. 

### Configuration

After cloning this repository, open the folder as a project in IntelliJ. Go to:  
__Preferences -> Plugins__ 

and search for Bazel. Install the plugin Bazel by Google, and when prompted to restart your IDEA do so.

#### Importing Bazel Project

Go to __File -> Import Bazel Project...__ and choose the DECIDE folder as the Workspace. When prompted to import 
project view, choose to build from BUILD file, and choose the file _DECIDE/BUILD_. Finish the import. This will start up 
the Bazel Console, where syncing is under way. When this finishes, you should be able to see the Bazel logo in the upper
right corner.

#### Run/Debug Configuration

Go to the meny bar and choose __Run -> Edit Configurations...__.  Above the empty list of configurations, press the +.
Add a new run/debug configuration of type Bazel Command. Choose the target expression to be the same name as the main file,
_//:DECIDE_ and choose the Bazel command to be of type _Run_. Press Apply and you should now be able to both run 
and debug the software. 
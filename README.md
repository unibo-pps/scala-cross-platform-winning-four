# Multi-Platform Winning Four! 🎮

A cross-platform implementation of **Connect Four** (Winning Four) written in Scala 3, targeting the **JVM**, **Scala.js**, and **Scala Native** runtimes.

---

## 📂 Project Structure

The project leverages the portable Scala cross-project structure:

*   **[`shared/`](file:///home/gianluca/Programming/scala-cross-platform-winning-four/shared)** — Contains the core Connect Four game board representation, game loop, AI logic, and abstract rendering interfaces shared by all platforms.
*   **[`jvm/`](file:///home/gianluca/Programming/scala-cross-platform-winning-four/jvm)** — The JVM-specific entry point that runs the console version of the game using the JVM runtime.
*   **[`js/`](file:///home/gianluca/Programming/scala-cross-platform-winning-four/js)** — The Scala.js-specific entry point that allows running the game logic using JavaScript runtimes.
*   **[`native/`](file:///home/gianluca/Programming/scala-cross-platform-winning-four/native)** — The Scala Native-specific entry point that compiles the game directly to machine-level native code.

---

## ⚙️ Prerequisites

Ensure you have the following installed on your system:

1.  **JDK 11 or higher** (JDK 21+ recommended)
2.  **sbt** (Scala Build Tool)
3.  **Node.js** (required to run the Scala.js target)
4.  **C Compiler Toolchain** (required for Scala Native):
    *   *Linux/Ubuntu:* `sudo apt install clang libssl-dev zlib1g-dev gc-dev`
    *   *macOS:* `xcode-select --install`

---

## 🚀 How to Launch the Applications

You can launch and interact with each platform directly through the **sbt** shell or from your terminal.

### 1. JVM Application
Runs the console-based Connect Four game inside a JVM.

*   **Run directly:**
    ```bash
    sbt winningFourJVM/run
    ```
*   **From the sbt shell:**
    ```sbt
    project winningFourJVM
    run
    ```

---

### 2. JavaScript Application
Compiles the application to JavaScript and executes it using Node.js.

*   **Run directly:**
    ```bash
    sbt winningFourJS/run
    ```
*   **Compile / Fast Link (Development):**
    Generates a fast-optimized `.js` file suitable for development under `js/target/scala-3.3.5/scala-cross-target-fastopt/`.
    ```bash
    sbt winningFourJS/fastLinkJS
    ```
*   **Compile / Full Link (Production):**
    Generates a highly-optimized, minified `.js` file under `js/target/scala-3.3.5/scala-cross-target-opt/`.
    ```bash
    sbt winningFourJS/fullLinkJS
    ```

---

### 3. Native Application
Compiles the application down to an LLVM-optimized standalone binary executable.

*   **Run directly:**
    ```bash
    sbt winningFourNative/run
    ```
*   **Build Executable Binary:**
    This generates a native executable at `native/target/scala-3.3.5/scala-cross-target-out`.
    ```bash
    sbt winningFourNative/nativeLink
    ```
*   **Build Release Binary (Optimized):**
    To build a fully-optimized binary suitable for production distribution:
    ```bash
    sbt 'set winningFourNative / nativeMode := "release-fast"; winningFourNative/nativeLink'
    ```

---

## 🧪 Testing and Formatting

To run the unified cross-platform test suite or apply automated code formatting:

*   **Run tests on all platforms:**
    ```bash
    sbt +test
    ```
*   **Auto-format the entire codebase:**
    ```bash
    sbt scalafmtAll
    ```
*   **Verify code formatting:**
    ```bash
    sbt scalafmtCheckAll
    ```

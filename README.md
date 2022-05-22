# Multi-Platform Winning Four!

A simple example of a pure scala platform that runs in several runtimes

## Project structure:
- shared: contains the code in common between runtimes
- jvm: JVM application specific part
- js: JS application specific part
- native: Native specific part

## Run
In an sbt shell, select one of the project available (e.g. winningFourJS, winningFourJVM, winningFourNative) and then type `run`

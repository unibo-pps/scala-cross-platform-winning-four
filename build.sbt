// Sbt cross target structure, for a more structure example use g8 https://github.com/arturopala/cross-scala.g8
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"
import scala.scalanative.build._

lazy val scalaTest = "3.2.12"

lazy val winningFour = (
  crossProject(JSPlatform, JVMPlatform, NativePlatform)
    .crossType(CrossType.Full) // default Pure
    .in(file("."))
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true
  ) // Js settings (e.g. library, ...)
  .nativeSettings() // Native settings (e.g. optimization, ...)
  .settings( // Common setting for all platform, note the %%%!!
    name := "scala-cross-target",
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest" % scalaTest
    )
  ) // Common settings..

lazy val root = project.in(file(".")).aggregate(winningFour.js, winningFour.jvm, winningFour.native)

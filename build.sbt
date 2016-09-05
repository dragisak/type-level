organization := "com.dragisak"

name := "type-level"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"      % "3.0.0"             % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-unchecked",
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code", // N.B. doesn't work well with the ??? hole
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import",
  "-Xfuture"
)

organization := "com.dragisak"

name := "type-level"

version := "1.0"

scalaVersion := "2.13.0"

libraryDependencies ++= List(
  "com.chuusai"   %% "shapeless" % "2.3.3",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

scalacOptions ++= List(
  "-feature",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:existentials",
  "-deprecation",
  "-encoding",
  "UTF-8", // yes, this is 2 args
  "-unchecked",
  "-Xfatal-warnings"
)

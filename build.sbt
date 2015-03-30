organization := "com.dragisak"

name := "type-level"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions"
)

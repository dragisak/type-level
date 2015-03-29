organization := "com.dragisak"

name := "type-level"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq(
  "-feature",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions"
)

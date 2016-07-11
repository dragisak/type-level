organization := "com.dragisak"

name := "type-level"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.scalatest"     %% "scalatest"      % "2.2.4"             % Test,
  "org.scala-lang"    % "scala-reflect"   % scalaVersion.value  % Test
)

scalacOptions ++= Seq(
  "-feature",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions"
)

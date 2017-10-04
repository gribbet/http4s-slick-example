import sbt.Keys._
import sbt._

val http4sVersion = "0.10.1"
lazy val project = Project(
  id = "http4s-slick-example",
  base = file("."))
  .settings(
    version := "1.0.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
      "com.typesafe.slick" %% "slick" % "3.0.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
      "org.http4s" %% "http4s-blaze-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.http4s" %% "http4s-argonaut" % http4sVersion,
      "org.slf4j" % "slf4j-simple" % "1.7.12",
      "com.h2database" % "h2" % "1.4.190"))

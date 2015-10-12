import sbt.Keys._
import sbt._
import spray.revolver.RevolverPlugin.Revolver

object Build extends sbt.Build {
  lazy val project = Project(
    id = "http4s-slick-example",
    base = file("."))
    .settings(
      version := "1.0.0",
      scalaVersion := "2.11.7",
      libraryDependencies ++= Seq(
        "com.typesafe.slick" %% "slick" % "3.0.3",
        "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
        "org.slf4j" % "slf4j-simple" % "1.7.12",
        "com.h2database" % "h2" % "1.4.190"))
    .settings(Revolver.settings: _*)
}

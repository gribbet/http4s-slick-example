import sbt.Keys._
import sbt._

object Build extends sbt.Build {
  lazy val project = Project(
    id = "http4s-slick-example",
    base = file("."))
    .settings(
      version := "1.0.0",
      scalaVersion := "2.11.7")
}

import Dependencies._

lazy val scala213 = "2.13.10"
lazy val scala212 = "2.12.17"
lazy val scala211 = "2.11.12"
lazy val supportedScalaVersions = List(scala213, scala212, scala211)
ThisBuild / organization := "com.crystailx.scalaflagr"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := scala213
ThisBuild / trackInternalDependencies := TrackLevel.TrackAlways
//ThisBuild / exportJars := true
ThisBuild / libraryDependencies ++= Dependencies.test.map(_ % Test)

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)

lazy val moduleSettings = Seq(
  crossScalaVersions := supportedScalaVersions,
  libraryDependencies ++= Dependencies.test
    .map(_ % Test) ++ Dependencies.log
)

lazy val core = (project in file("scalaflagr-core"))
  .settings(moduleSettings)

lazy val sttp1Client = (project in file("scalaflagr-client-sttp1"))
  .settings(moduleSettings)
  .settings(libraryDependencies ++= Dependencies.sttp1)
  .dependsOn(core)

lazy val catsEffect = (project in file("scalaflagr-effect-cats"))
  .settings(moduleSettings)
  .settings(libraryDependencies ++= Dependencies.cats)
  .dependsOn(core)

lazy val jsonCirce = (project in file("scalaflagr-json-circe"))
  .settings(moduleSettings)
  .settings(libraryDependencies ++= Dependencies.circe)
  .dependsOn(core)

lazy val demo = (project in file("scalaflagr-demo"))
  .settings(moduleSettings)
  .settings(
    exportToInternal := TrackLevel.NoTracking,
    libraryDependencies ++= Dependencies.sttp1Akka
  )
  .dependsOn(core, sttp1Client, jsonCirce, catsEffect)

lazy val root = (project in file("."))
  .settings(
    crossScalaVersions := Nil,
    publish / skip := true,
    update / aggregate := false
  )
  .aggregate(core, sttp1Client, catsEffect, jsonCirce, demo)

scmInfo := Some(
  ScmInfo(
    url("https://github.com/crystailx/scalaflagr"),
    "scm:git:git@github.com:crystailx/scalaflagr.git"
  )
)

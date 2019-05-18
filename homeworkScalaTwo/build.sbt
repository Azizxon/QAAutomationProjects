name := "scalaHomework"

version := "0.1"

scalaVersion := "2.12.5"


resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"

libraryDependencies += "org.scalamock" %% "scalamock" % "4.1.0" % Test

val testThreads = 64

concurrentRestrictions in Global := Seq(
  Tags.limit(Tags.Test, testThreads)
)

parallelExecution in Test := true
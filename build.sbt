lazy val `fp-red-book` = (project in file("fp-red-book")).
  settings(
    name := "fp-red-book",
    scalaVersion := "2.13.1"
  )

lazy val root = (project in file(".")).
  settings(
    name := "scala-examples",
    scalaVersion := "2.13.1",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % "2.6.5"
  )

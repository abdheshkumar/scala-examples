lazy val `fp-red-book` = (project in file("fp-red-book")).
  settings(
    name := "fp-red-book",
    scalaVersion := "2.12.3"
  )

lazy val root = (project in file(".")).
  settings(
    name := "scala-examples",
    scalaVersion := "2.12.3"
  )

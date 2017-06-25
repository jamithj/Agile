name := "Agile"

version := "0.0.1"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
}
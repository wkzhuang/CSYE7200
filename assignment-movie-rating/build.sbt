name := "assignment-movie-rating"

version := "1.0"

scalaVersion := "2.12.15"

val sparkVersion = "3.3.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "org.apache.spark" %% "spark-sql" % sparkVersion

)
name := "SparkWordCount"

version := "0.1"

scalaVersion := "2.12.15"

scalacOptions in(Compile, doc) ++= Seq("-groups", "-implicits", "-deprecation", "-Ywarn-dead-code", "-Ywarn-value-discard", "-Ywarn-unused" )

val scalaTestVersion = "3.2.3"

libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % "test"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.1"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.1"

parallelExecution in Test := false
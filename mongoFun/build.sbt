name := "mongoFUN"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "0.9.6",
  "org.reactivemongo" % "reactivemongo_2.10" % "0.12.2",
  "org.slf4j" % "slf4j-simple" % "1.7.25"
)

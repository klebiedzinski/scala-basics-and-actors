name := "Kolokwium"
version := "1.0.0"

scalaVersion := "2.13.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-explaintypes", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.6.15"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV
  )
}

javaOptions in reStart += "-Dfile.encoding=utf8"


val scalaTest  = "org.scalatest" %% "scalatest"     % "2.2.4" % "test"
val scalaz     = "org.scalaz"    %% "scalaz-core"   % "7.1.6"

lazy val commonSettings = Seq(
  organization := "com.glasban",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "21s",
    libraryDependencies ++= Seq(scalaTest, scalaz)
  )

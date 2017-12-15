name := """dataloaderApp"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "net.codingwell" %% "scala-guice" % "4.0.1",
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars.npm" % "angular" % "1.6.2",
  "org.webjars.npm" % "angular-cookies" % "1.6.2",
  "org.webjars.npm" % "angular-animate" % "1.6.2",
  "org.webjars.npm" % "angular-aria" % "1.6.2",
  "org.webjars.npm" % "angular-material" % "1.1.3",
  "org.webjars.npm" % "angular-moment" % "1.0.0",
  "org.webjars.bower" % "angular-ui-router" % "1.0.0-rc.1",
  "org.webjars.npm" % "angular-messages" % "1.6.2",
  "org.webjars.npm" % "angular-resource" % "1.6.2",
  "org.webjars.npm" % "ng-file-upload" % "12.2.12"

)

libraryDependencies += "org.rahul" % "reactivejobqueue_2.11" % "1.0"

// https://mvnrepository.com/artifact/joda-time/joda-time
libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

resolvers += Resolver.file("Local", file( Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)

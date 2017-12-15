name := """workerApp"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json_2.11
libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.6.0-M4"

libraryDependencies += "org.rahul" % "reactivejobqueue_2.11" % "1.0"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

// https://mvnrepository.com/artifact/joda-time/joda-time
libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.5"

// https://mvnrepository.com/artifact/commons-net/commons-net
libraryDependencies += "commons-net" % "commons-net" % "3.6"

resolvers += Resolver.file("Local", file( Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)





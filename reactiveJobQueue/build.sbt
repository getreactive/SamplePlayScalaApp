name := """reactiveJobQueue"""

version := "1.0"

scalaVersion := "2.11.7"

organization := "org.rahul"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "0.10.2.0"
// https://mvnrepository.com/artifact/org.apache.kafka/kafka_2.11
libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "0.10.2.0"

// https://mvnrepository.com/artifact/commons-net/commons-net
libraryDependencies += "commons-net" % "commons-net" % "3.6"

// https://mvnrepository.com/artifact/com.jcraft/jsch
libraryDependencies += "com.jcraft" % "jsch" % "0.1.54"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json_2.11
libraryDependencies += "com.typesafe.play" % "play-json_2.11" % "2.6.0-M4"

// https://mvnrepository.com/artifact/joda-time/joda-time
libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.5"


libraryDependencies += "com.typesafe" % "config" % "1.3.1"

resolvers += Resolver.file("Local", file( Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)

resolvers += "Local Maven Repository" at file(
  Path.userHome.absolutePath+"/.m2/repository").toURL.toString
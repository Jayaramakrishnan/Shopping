name := """shoppingKart"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  cache,
  javaWs,
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  filters,
  "javax.inject" % "javax.inject" % "1",
  "commons-beanutils" % "commons-beanutils" % "1.8.0",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.springframework" % "spring-context" % "3.2.5.RELEASE",
  "org.springframework.data" % "spring-data-jpa" % "1.3.5.RELEASE",
  "org.springframework" % "spring-expression" % "3.2.5.RELEASE",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final",
  "org.springframework" % "spring-test" % "3.2.5.RELEASE",
  "log4j" % "log4j" % "1.2.17",
  "org.hibernate" % "hibernate-envers" % "4.3.5.Final",
  "org.hibernate" % "hibernate-core" % "4.3.8.Final",
  "org.codehaus.jackson" % "jackson-core-asl" % "1.9.13",
  "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.13",
  "net.sf.uadetector" % "uadetector-core" % "0.9.18",
  "net.sf.uadetector" % "uadetector-resources" % "2014.06",
  "org.apache.commons" % "commons-email" % "1.3.3",
  "org.apache.commons" % "commons-lang3" % "3.0",
  "org.apache.commons" % "commons-io" % "1.3.2",
  "commons-collections" % "commons-collections" % "3.0",
  "commons-lang" % "commons-lang" % "2.6",
  "net.sf.jt400" % "jt400" % "6.7",
  "org.elasticsearch" % "elasticsearch" % "1.7.3",
  "com.google.code.gson" % "gson" % "2.3.1"
)
lazy val root = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)
  .settings(
    name := "Ferchinator3000",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.13.4",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      "mysql" % "mysql-connector-java" % "5.1.47",
      "com.google.code.gson" % "gson" % "2.8.5",
      "com.fasterxml.jackson.core" % "jackson-core" % "2.12.1",
      "com.h2database" % "h2" % "1.4.199",
      "org.awaitility" % "awaitility" % "3.1.6" % Test,
      "org.assertj" % "assertj-core" % "3.12.2" % Test,
      "org.mockito" % "mockito-core" % "3.0.0" % Test,
      // To provide an implementation of JAXB-API, which is required by Ebean.
      "javax.xml.bind" % "jaxb-api" % "2.3.1",
      "javax.activation" % "activation" % "1.1.1",
      "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2",
    ),
    testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
    javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
  )

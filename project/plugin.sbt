logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases/"

resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"

resolvers += "Flyway" at "https://flywaydb.org/repo"

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "2.1.1")

// Binary version (2.11) for dependency org.scala-lang#scala-library;2.11.8 warning is scalafmt problem. wait for upgrade.
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "0.4.8")

addSbtPlugin("com.typesafe.sbt" % "sbt-aspectj" % "0.10.6")

addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.1")

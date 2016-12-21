lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.github.smdmts",
      scalaVersion := "2.11.8"
    )),
    name := "flink-streaming-example"
  )

// make run command include the provided dependencies
run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in (Compile, run), runner in (Compile, run))

fork in run := true

scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf"))

resourceDirectory in Compile := baseDirectory.value / "src/conf"

libraryDependencies ++= flinkDependencies ++ otherDependency

val flinkVersion = "1.1.3"

def otherDependency = Seq(
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)

val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % flinkVersion,
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion,
   "org.apache.flink" % "flink-connector-kafka-0.9_2.10" % flinkVersion
)

resolvers ++= Seq(
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases" ,
  "Atlassian Releases" at "https://maven.atlassian.com/public/",
  "Artima Maven Repository" at "http://repo.artima.com/releases",
  "sonatype snaoshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  Resolver.jcenterRepo
)

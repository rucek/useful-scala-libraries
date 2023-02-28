import sbt._

object Dependencies {
  
  object V {
    val pureconfig = "0.17.2"
    val chimney = "0.7.0"
    val tapir = "1.2.9"
    val quicklens = "1.9.0"
    val refined = "0.10.1"
  }

  val pureconfig = "com.github.pureconfig" %% "pureconfig" % V.pureconfig

  val chimney = "io.scalaland" %% "chimney" % V.chimney

  val tapir = Seq(
    "tapir-core",
    "tapir-akka-http-server",
    "tapir-play-server",
    "tapir-json-circe",
    "tapir-json-circe",
    "tapir-swagger-ui-bundle",
    "tapir-sttp-client"
  ).map("com.softwaremill.sttp.tapir" %% _ % V.tapir)

  val quicklens = "com.softwaremill.quicklens" %% "quicklens" % V.quicklens

  val refined = "eu.timepit" %% "refined" % V.refined
}

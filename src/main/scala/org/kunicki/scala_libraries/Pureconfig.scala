package org.kunicki.scala_libraries

import com.typesafe.config.ConfigFactory
import pureconfig._
import pureconfig.generic.auto._


object Pureconfig {
  val config = ConfigFactory.load().getConfig("server")
  val host = config.getString("host")
  val port = config.getInt("port")

  case class ServerConfig(host: String, port: Int)
  val serverConfig = ConfigSource.default.at("server").loadOrThrow[ServerConfig]
}

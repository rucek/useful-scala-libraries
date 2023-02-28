package org.kunicki.scala_libraries

import io.scalaland.chimney.dsl.TransformerOps

object Chimney {
  case class DomainUser(login: String, firstName: String, lastName: String, age: Int)
  case class ApiUser(login: String, fullName: String, howOld: Int)

  val domainUser = DomainUser("jkowalski", "Jan", "Kowalski", 42)
  val apiUser = ApiUser(domainUser.login, s"${domainUser.firstName} ${domainUser.lastName}", domainUser.age)

  val apiUser2 = domainUser.into[ApiUser]
    .withFieldComputed(_.fullName, u => s"${u.firstName} ${u.lastName}")
    .withFieldRenamed(_.age, _.howOld)
    .transform
}

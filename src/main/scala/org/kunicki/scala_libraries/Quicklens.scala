package org.kunicki.scala_libraries

import com.softwaremill.quicklens._

object Quicklens {

  case class Street(name: String)
  case class Address(street: Street)
  case class Person(address: Address, age: Int)

  val person = Person(Address(Street("Funkcyjna")), 42)

  val person2 = person.copy(address = person.address.copy(street = person.address.street.copy(name = "Obiektowa 2")))

  val person3 = person.modify(_.address.street.name).setTo("Obiektowa")

  val modifyStreetName = modifyLens[Person](_.address.street.name)
  val person4 = modifyStreetName.setTo("Obiektowa")(person)
}

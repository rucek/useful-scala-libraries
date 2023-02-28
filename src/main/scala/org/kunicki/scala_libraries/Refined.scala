package org.kunicki.scala_libraries

import eu.timepit.refined.api._
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._

object Refined extends App {
  case class Adult(name: String, age: Int) {
    require(age > 18)
  }

  type AdultAge = Int Refined Greater[18]
  case class RefinedAdult(name: String, age: AdultAge)

  RefinedAdult("Jan Kowalski", 42)
  RefinedAdult("Janek Kowalski", 7) // doesn't compile: Predicate failed: (7 > 18).

  val age = 7
  RefinedAdult("Janek Kowalski", age)

  val adultAge: Either[String, AdultAge] = RefType.applyRef[AdultAge](age)
}

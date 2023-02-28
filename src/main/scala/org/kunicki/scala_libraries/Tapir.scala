package org.kunicki.scala_libraries

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import io.circe.generic.auto._
import sttp.client3._
import sttp.tapir._
import sttp.tapir.client.sttp.SttpClientInterpreter
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter
import sttp.tapir.server.play.PlayServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Tapir {

  private implicit val system: ActorSystem = ActorSystem()

  case class Book(title: String, year: Int)

  val getBooksByYear =
    endpoint
      .get
      .in("books")
      .in(query[Int]("year"))
      .out(jsonBody[List[Book]])

  def getBooksByYearLogic(year: Int): Future[List[Book]] =
    Future.successful(
      List(
        Book("Nad Niemnem", 1888),
        Book("Designing Data-Intensive Applications", 2017)
      )
    )

  val serverEndpoint = getBooksByYear.serverLogicSuccess(getBooksByYearLogic)
  val swaggerEndpoints = SwaggerInterpreter().fromEndpoints(List(getBooksByYear), "My API", "1.0")

  val akkaHttpRoutes: Route = AkkaHttpServerInterpreter().toRoute(getBooksByYear.serverLogicSuccess(getBooksByYearLogic))
  val playRoutes = PlayServerInterpreter().toRoutes(getBooksByYear.serverLogicSuccess(getBooksByYearLogic))

  val booksClient = SttpClientInterpreter().toQuickClient(getBooksByYear, Some(uri"http://localhost:8080"))
  val books: Either[Unit, List[Book]] = booksClient(1984)
}

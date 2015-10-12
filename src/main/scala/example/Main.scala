package example

import java.util.UUID
import java.util.concurrent.Executors

import com.typesafe.scalalogging.LazyLogging
import example.database.Database
import example.model.Widget
import example.service.WidgetService
import example.json.Codecs._
import example.http.Encoders._
import org.http4s.dsl._
import org.http4s.server.HttpService
import org.http4s.server.blaze.BlazeBuilder

import scalaz.stream.io

object Main extends App with LazyLogging {
  implicit val executorService = Executors.newFixedThreadPool(4)
  implicit val database = Database.database
  implicit val widgetService = new WidgetService

  val id = UUID.randomUUID

  val server = for {
    _ <- widgetService.initialize
    _ <- widgetService.create(Widget(id, "Test Widget"))
    server <- BlazeBuilder
      .withServiceExecutor(executorService)
      .bindHttp(8080, "0.0.0.0")
      .withNio2(true)
      .mountService(
        HttpService {
          case request@GET -> Root => widgetService.find(id) flatMap {
            case None => NotFound()
            case Some(widget) => Ok(widget)
          }
        })
      .start
    _ <- io.stdInLines.take(1).run
    _ <- server.shutdown
  } yield server

  server.run

  executorService.shutdown
}


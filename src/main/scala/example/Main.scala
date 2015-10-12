package example

import java.util.UUID

import com.typesafe.scalalogging.LazyLogging
import example.database.Database
import example.http.{HttpServices, WidgetResource}
import example.model.Widget
import example.service.WidgetService
import org.http4s.server.blaze.BlazeBuilder

import scalaz.stream.io

object Main extends App with LazyLogging {
  implicit val executorService = Executor()
  implicit val database = Database.database
  implicit val widgetService = new WidgetService

  val server = for {
    _ <- widgetService.initialize
    _ <- widgetService.create(Widget(UUID.randomUUID, "Test Widget"))
    server <- BlazeBuilder
      .withServiceExecutor(executorService)
      .bindHttp(8080, "0.0.0.0")
      .withNio2(true)
      .mountService(HttpServices())
      .start
    _ <- io.stdInLines.take(1).run
    _ <- server.shutdown
  } yield server

  server.run

  executorService.shutdown
}


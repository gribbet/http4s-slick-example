package example

import java.util.UUID
import java.util.concurrent.Executors

import com.typesafe.scalalogging.LazyLogging
import example.database.Database
import example.model.Widget
import example.service.WidgetService
import org.http4s.server.blaze.BlazeBuilder

import scala.io.StdIn

object Main extends App with LazyLogging {
  implicit val executorService = Executors.newFixedThreadPool(4)
  implicit val database = Database.database
  implicit val widgetService = new WidgetService

  def run = {
    val id = UUID.randomUUID()
    for {
      _ <- widgetService.initialize
      _ <- widgetService.create(Widget(id, "Widget"))
      widget <- widgetService.find(id)
    } yield widget
  }

  logger.info(run.run.toString)

  val server = BlazeBuilder
    .withServiceExecutor(executorService)
    .bindHttp(8080, "0.0.0.0")
    .withNio2(true)
    .start
    .run

  StdIn.readLine

  server.shutdownNow

  executorService.shutdown
}

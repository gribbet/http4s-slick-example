package example

import java.util.UUID
import java.util.concurrent.Executors

import com.typesafe.scalalogging.LazyLogging
import example.database.Database
import example.model.Widget
import example.service.WidgetService

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

  executorService.shutdown
}

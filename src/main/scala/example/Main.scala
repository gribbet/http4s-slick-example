package example

import java.util.UUID

import com.typesafe.scalalogging.LazyLogging
import example.database.Driver
import example.model.Widget
import example.service.WidgetService

import scala.concurrent.ExecutionContext

object Main extends App with LazyLogging {
  implicit val executionContext = ExecutionContext.global
  implicit val database = Driver.api.Database.forURL("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
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
}

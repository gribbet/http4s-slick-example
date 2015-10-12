package example

import java.util.UUID

import com.typesafe.scalalogging.LazyLogging
import example.database.Driver
import example.database.Driver.api._
import example.model.Widget
import example.query.Widgets
import example.service.WidgetService

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}

object Main extends App with LazyLogging {
  implicit val executionContext = ExecutionContext.global
  implicit val database = Driver.api.Database.forURL("jdbc:h2:mem:example;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
  implicit val widgetService = new WidgetService

  val id = UUID.randomUUID()

  logger.info(Await.result(
    for {
      _ <- database.run(Widgets.schema.create)
      _ <- widgetService.create(Widget(id, "Widget"))
      widget <- widgetService.find(id)
    } yield widget, Duration.Inf).toString)
}

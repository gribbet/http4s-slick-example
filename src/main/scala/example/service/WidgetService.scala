package example.service

import example.query.Widgets
import example.service.airphrame.service.ModelService
import slick.jdbc.JdbcBackend

import scala.concurrent.ExecutionContext

class WidgetService(implicit
  database: JdbcBackend#DatabaseDef,
  executionContext: ExecutionContext)
  extends ModelService(Widgets)
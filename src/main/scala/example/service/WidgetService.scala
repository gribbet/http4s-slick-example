package example.service

import java.util.concurrent.ExecutorService

import example.query.Widgets
import example.service.airphrame.service.ModelService
import slick.jdbc.JdbcBackend

class WidgetService(implicit
  database: JdbcBackend#DatabaseDef,
  executionService: ExecutorService)
  extends ModelService(Widgets)
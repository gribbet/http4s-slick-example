package example.service

import java.util.concurrent.ExecutorService

import example.query.Widgets
import example.service.airphrame.service.ModelService
import slick.basic.BasicBackend

class WidgetService(implicit
  database: BasicBackend#DatabaseDef,
  executionService: ExecutorService)
  extends ModelService(Widgets)
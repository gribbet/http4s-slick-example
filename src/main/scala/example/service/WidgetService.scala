package example.service

import java.util.concurrent.ExecutorService

import example.query.Widgets
import example.service.airphrame.service.ModelService
import slick.backend.DatabaseComponent

class WidgetService(implicit
  database: DatabaseComponent#DatabaseDef,
  executionService: ExecutorService)
  extends ModelService(Widgets)
package example.http

import example.json.Codecs._
import example.service.WidgetService

object WidgetResource {
  def apply()(implicit widgetService: WidgetService) =
    ModelResource()
}

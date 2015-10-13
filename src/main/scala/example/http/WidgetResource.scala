package example.http

import example.json.Codecs._
import example.model.Widget
import example.service.WidgetService

object WidgetResource {
  def apply()(implicit widgetService: WidgetService) =
    Path("widgets", ModelResource[Widget]())
}

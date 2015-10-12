package example.http

import example.service.WidgetService

object HttpServices {
  def apply()(implicit
    widgetService: WidgetService) = {
    val filters = LoggingFilter andThen GZipFilter
    filters(WidgetResource())
  }
}

package example.http

import example.service.WidgetService

object HttpServices {
  def apply()(implicit
    widgetService: WidgetService) =
    (LoggingFilter andThen GZipFilter)(WidgetResource())
}

package example.query

import example.model.Widget
import example.table.WidgetTable

object Widgets extends ModelTableQuery[Widget, WidgetTable](new WidgetTable(_))
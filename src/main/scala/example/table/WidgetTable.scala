package example.table

import example.database.Driver.api._
import example.model.Widget

class WidgetTable(tag: Tag) extends ModelTable[Widget](tag, "widget") {

  def * = (id, name) <>(Widget.tupled, Widget.unapply _)

  def name = column[String]("name")
}


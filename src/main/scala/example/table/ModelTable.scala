package example.table

import java.util.UUID

import example.database.Driver.api._

abstract class ModelTable[T](tag: Tag, tableName: String) extends Table[T](tag, tableName) {

  def id = column[UUID]("id", O.PrimaryKey)
}



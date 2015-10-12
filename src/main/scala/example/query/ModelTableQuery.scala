package example.query

import java.util.UUID

import example.database.Driver.api._
import example.model.Model
import example.table.ModelTable
import slick.lifted.TableQuery

abstract class ModelTableQuery[M <: Model, MT <: ModelTable[M]](
  cons: Tag => MT)
  extends TableQuery[MT](cons) {

  def initialize() =
    this.schema.create

  def list() =
    this.result

  def find(id: UUID) =
    filter(_.id === id).result

  def insert(model: M) =
    this += model

  def update(model: M) =
    filter(_.id === model.id).update(model)

  def delete(model: M) =
    filter(_.id === model.id).delete
}


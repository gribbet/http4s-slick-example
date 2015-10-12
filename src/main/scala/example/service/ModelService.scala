package example.service

package airphrame.service

import java.util.UUID

import example.model.Model
import example.query.ModelTableQuery
import example.table.ModelTable
import slick.jdbc.JdbcBackend

import scala.concurrent.{ExecutionContext, Future}


abstract class ModelService[M <: Model, MT <: ModelTable[M]](
  query: ModelTableQuery[M, MT])
  (implicit
    db: JdbcBackend#DatabaseDef,
    ex: ExecutionContext) {

  def find(id: UUID): Future[Option[M]] =
    db.run(query.find(id)).map(_.headOption)

  def create(model: M): Future[_] =
    db.run(query.insert(model))

  def update(model: M): Future[_] =
    db.run(query.update(model))

  def delete(model: M): Future[_] =
    db.run(query.delete(model))
}

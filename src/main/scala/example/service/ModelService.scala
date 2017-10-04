package example.service

package airphrame.service

import java.util.UUID
import java.util.concurrent.ExecutorService

import example.Main.executorService
import example.model.Model
import example.query.ModelTableQuery
import example.table.ModelTable
import fs2.{Strategy, Task}
import slick.backend.DatabaseComponent
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext

abstract class ModelService[M <: Model, +MT <: ModelTable[M]](
  query: ModelTableQuery[M, MT])
  (implicit
    database: DatabaseComponent#DatabaseDef,
    executionService: ExecutorService) {
  implicit val executionContext = ExecutionContext.fromExecutor(executorService)

  def initialize() =
    task(query.initialize)

  def find(id: UUID): Task[Option[M]] =
    task(query.find(id)).map(_.headOption)

  protected def task[R](action: DBIO[R]): Task[R] = {
    implicit val strategy = Strategy.fromExecutor(executorService)
    Task.async { cb =>
      database
        .run(action)
        .onComplete(x =>
          cb(x.toEither))
    }
  }

  def create(model: M): Task[_] =
    task(query.insert(model))

  def update(model: M): Task[_] =
    task(query.update(model))

  def delete(model: M): Task[_] =
    task(query.delete(model))

  def list(): Task[List[M]] =
    task(query.list.map(_.toList))
}

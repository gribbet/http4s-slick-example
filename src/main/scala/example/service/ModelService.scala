package example.service

package airphrame.service

import java.util.UUID
import java.util.concurrent.ExecutorService

import example.model.Model
import example.query.ModelTableQuery
import example.table.ModelTable
import slick.backend.DatabaseComponent
import slick.dbio.DBIO

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}
import scalaz._
import scalaz.concurrent.Task

abstract class ModelService[M <: Model, +MT <: ModelTable[M]](
  query: ModelTableQuery[M, MT])
  (implicit
    database: DatabaseComponent#DatabaseDef,
    executorService: ExecutorService) {

  def initialize() =
    task(query.initialize)

  def find(id: UUID): Task[Option[M]] =
    task(query.find(id)).map(_.headOption)

  def create(model: M): Task[_] =
    task(query.insert(model))

  def update(model: M): Task[_] =
    task(query.update(model))

  def delete(model: M): Task[_] =
    task(query.delete(model))

  def list(): Task[Seq[M]] =
    task(query.list)

  protected def task[R](action: DBIO[R]): Task[R] = {
    implicit val executionContext = ExecutionContext.fromExecutorService(executorService)
    Task.async { cb =>
      database.run(action) onComplete {
        case Success(x) => cb(\/-(x))
        case Failure(x) => cb(-\/(x))
      }
    }
  }
}

package example.database

import java.util.concurrent.ExecutorService

import slick.util.AsyncExecutor

import scala.concurrent.ExecutionContext

object Database {
  def database(implicit executorService: ExecutorService) =
    Driver.api.Database.forURL(
      "jdbc:h2:mem:example;DB_CLOSE_DELAY=-1",
      driver = "org.h2.Driver",
      executor = new ExecutorServiceAsyncExecutor(executorService))

  private class ExecutorServiceAsyncExecutor(executorService: ExecutorService)
    extends AsyncExecutor {
    val executionContext = ExecutionContext.fromExecutorService(executorService)

    def close = Unit
  }

}

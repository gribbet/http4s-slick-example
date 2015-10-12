package example

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.{Executors, ThreadFactory}

import com.typesafe.scalalogging.LazyLogging

import scala.util.{Failure, Try}

object Executor extends LazyLogging {
  def apply() = Executors.newFixedThreadPool(4, new ThreadFactory() {
    private val thread = new AtomicInteger(1)

    override def newThread(runnable: Runnable) =
      new Thread(new Runnable {
        override def run() = Try(runnable.run) match {
          case Failure(e) => logger.error("Executor failure", e)
          case _ => Unit
        }
      }, s"Executor-${thread.getAndIncrement}")
  })
}

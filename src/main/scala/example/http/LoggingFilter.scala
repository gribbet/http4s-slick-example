package example.http

import java.time.{Duration, LocalDateTime}

import com.typesafe.scalalogging.LazyLogging
import org.http4s.Request
import org.http4s.server._

object LoggingFilter extends HttpFilter with LazyLogging {
  def apply(service: HttpService): HttpService = Service.lift {
    (request: Request) =>
      val start = LocalDateTime.now()
      service(request) map { response =>
        val duration = Duration.between(start, LocalDateTime.now())
        logger.info(s"${response.status.code} ${request.method} ${request.uri} ${duration}")
        response
      }
  }
}

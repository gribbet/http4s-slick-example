package example.http

import org.http4s.HttpService
import org.http4s.server.middleware.GZip

object GZipFilter extends HttpFilter {
  override def apply(service: HttpService): HttpService = GZip(service)
}
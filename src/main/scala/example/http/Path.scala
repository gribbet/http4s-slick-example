package example.http

import org.http4s.server._

object Path {
  def apply(prefix: String, service: HttpService) = {
    val path = s"/$prefix"
    HttpService {
      case request if request.uri.path startsWith path =>
        service(request.copy(uri =
          request.uri.copy(path =
            request.uri.path.replaceFirst(path, ""))))
    }
  }
}

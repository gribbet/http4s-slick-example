package example.http

import org.http4s.HttpService

object Path {
  def apply(prefix: String, service: HttpService) = {
    val path = s"/$prefix"
    HttpService {
      case request if request.uri.path startsWith path =>
        service(request.withUri(
          request.uri.copy(path =
            request.uri.path.replaceFirst(path, ""))))
          .map(_.orNotFound)
    }
  }
}

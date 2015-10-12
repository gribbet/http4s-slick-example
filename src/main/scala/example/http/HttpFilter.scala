package example.http

import org.http4s.server._

abstract class HttpFilter extends (HttpService => HttpService)

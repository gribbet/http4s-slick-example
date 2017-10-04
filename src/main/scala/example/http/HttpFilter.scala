package example.http

import org.http4s.HttpService

trait HttpFilter extends (HttpService => HttpService)

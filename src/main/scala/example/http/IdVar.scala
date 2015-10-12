package example.http

import java.util.UUID

import scala.util.Try

object IdVar {
  def unapply(string: String): Option[UUID] =
    Try(UUID.fromString(string)).toOption
}

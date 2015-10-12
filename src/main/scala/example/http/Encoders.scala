package example.http

import argonaut.EncodeJson
import org.http4s.argonaut.ArgonautInstances

object Encoders {
  private val argonautInstances = new ArgonautInstances {}

  implicit def modelEncoder[T](implicit encodeJson: EncodeJson[T]) = argonautInstances.jsonEncoderOf[T]
}

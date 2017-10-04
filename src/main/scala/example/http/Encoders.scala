package example.http

import argonaut.{DecodeJson, EncodeJson, PrettyParams}
import example.model.Model
import org.http4s.argonaut.ArgonautInstances

object Encoders {
  private val argonautInstances =
    ArgonautInstances.withPrettyParams(PrettyParams.spaces4)

  implicit def modelEncoder[T <: Model](implicit encodeJson: EncodeJson[T]) =
    argonautInstances.jsonEncoderOf[T]

  implicit def modelListEncoder[T <: Model](implicit encodeJson: EncodeJson[T]) =
    argonautInstances.jsonEncoderOf[List[T]]

  implicit def modelDecoder[T <: Model](implicit decodeJson: DecodeJson[T]) =
    argonautInstances.jsonOf[T]
}

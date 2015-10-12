package example.http

import argonaut.{DecodeJson, EncodeJson}
import example.model.Model
import org.http4s.argonaut.ArgonautInstances

object Encoders {
  private val argonautInstances = new ArgonautInstances {}

  implicit def modelEncoder[T <: Model](implicit encodeJson: EncodeJson[T]) =
    argonautInstances.jsonEncoderOf[T]

  implicit def modelSeqEncoder[T <: Model](implicit encodeJson: EncodeJson[T]) =
    argonautInstances.jsonEncoderOf[Seq[T]]

  implicit def modelDecoder[T <: Model](implicit decodeJson: DecodeJson[T]) =
    argonautInstances.jsonOf[T]
}

package example.http

import example.model.Model
import io.circe._
import org.http4s.circe._

object Encoders {
  private val argonautInstances =
    CirceInstances.withPrinter(Printer.spaces4)

  implicit def modelEncoder[T <: Model](implicit encoder: Encoder[T]) =
    argonautInstances.jsonEncoderOf[T]

  implicit def modelListEncoder[T <: Model](implicit encoder: Encoder[T]) =
    argonautInstances.jsonEncoderOf[List[T]]

  implicit def modelDecoder[T <: Model](implicit decoder: Decoder[T]) =
    argonautInstances.jsonOf[T]
}

package example.json

import java.util.UUID

import example.model.Widget
import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

object Codecs {
  implicit val uuidEncoder =
    Encoder.instance[UUID](_.toString().asJson)
  implicit val uuidDecoder =
    Decoder.instance[UUID](_.as[String].map(UUID.fromString(_)))

  implicit val widgetEncoder = Encoder[Widget]
  implicit val widgetDecoder = Decoder[Widget]
}

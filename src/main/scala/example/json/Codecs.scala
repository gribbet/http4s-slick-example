package example.json

import java.util.UUID

import argonaut.Argonaut._
import argonaut.CodecJson
import example.model.Widget

object Codecs {
  implicit val uuidCodec = CodecJson[UUID](
    _.toString.asJson,
    _.as[String].map(UUID.fromString(_)))

  implicit val widgetEncodeJson =
    casecodec2(Widget.apply, Widget.unapply)("id", "name")
}

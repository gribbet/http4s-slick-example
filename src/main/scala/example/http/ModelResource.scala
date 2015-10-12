package example.http

import argonaut.{DecodeJson, EncodeJson}
import example.http.Encoders._
import example.model.Model
import example.service.airphrame.service.ModelService
import org.http4s.dsl._
import org.http4s.server.HttpService

object ModelResource {
  def apply[T <: Model]()(implicit
    modelService: ModelService[T, _],
    encodeJson: EncodeJson[T],
    decodeJson: DecodeJson[T]) = HttpService {

    case request@GET -> Root => modelService.list.flatMap(Ok(_))

    case request@POST -> Root => request.decode[T] { model =>
      modelService.create(model).flatMap(_ =>Created())
    }
  }
}

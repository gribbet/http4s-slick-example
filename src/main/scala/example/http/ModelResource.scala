package example.http

import example.http.Encoders._
import example.model.Model
import example.service.airphrame.service.ModelService
import io.circe._
import org.http4s.HttpService
import org.http4s.dsl._

object ModelResource {
  def apply[T <: Model]()(implicit
    modelService: ModelService[T, _],
    encodeJson: Encoder[T],
    decodeJson: Decoder[T]) = HttpService {

    case request@GET -> Root =>
      modelService.list
        .flatMap(Ok(_))

    case request@POST -> Root =>
      request.decode[T] { model =>
        modelService.create(model)
          .flatMap(_ => Created())
      }

    case request@GET -> Root / IdVar(id) =>
      modelService.find(id) flatMap {
        case Some(model) => Ok(model)
        case None => NotFound()
      }

    case request@DELETE -> Root / IdVar(id) =>
      modelService.find(id) flatMap {
        case Some(model) => modelService.delete(model)
          .flatMap(_ => NoContent())
        case None => NotFound()
      }
  }
}

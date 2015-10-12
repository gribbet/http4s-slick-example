package example.model

import java.util.UUID

case class Widget(
  val id: UUID,
  val name: String)
  extends Model

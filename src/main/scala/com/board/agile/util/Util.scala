package com.board.agile.util

/**
  * Created by jamith.jayasekara on 27/06/2017.
  */
import scala.util.{Failure, Success, Try}

object ColumnName extends Enumeration {
  val STARTING, INPROGRESS, DONE = Value

  def fromString(str: String): Option[Value] =
    Try(this.withName(str.toUpperCase)) match {
      case Success(value) => Some(value)
      case  Failure(e) => None
    }

  def contains(str: String): Boolean =
    fromString(str).nonEmpty
}

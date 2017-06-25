package com.board.agile

/**
  * Created by jamit on 25/06/2017.
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

case class Column(name: ColumnName.Value , workInProgressLimit: Int)
case class Card(title: String, description: String, estimate: Int, currentColumn: Option[Column] )
class Board(iteration: Iteration)
case class Iteration(id: Int, columns: Seq[Column], cards: Seq[Card], lastMove: Option[(Card, Card)], velocity: Int) {
  def addCard(card: Card) = this.copy(cards = cards :+ card)

  def moveCard(card: Card, toColumn: Option[Column]):Iteration = {
    val updatedCard = card.copy(currentColumn = toColumn)
    val updatedCards = cards.updated(cards.indexOf(card), updatedCard)
    this.copy(cards = updatedCards, lastMove = Some((card, updatedCard)))
  }
}

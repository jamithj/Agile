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

  def moveCard(card: Card, toColumn: Option[Column]):Either[Exception, Iteration] = {
    val updatedCard = card.copy(currentColumn = toColumn)
    val updatedCards = cards.updated(cards.indexOf(card), updatedCard)
    val isWorkInProgressLimitBreached = toColumn.exists(c => getVelocity(updatedCards, c.name) > c.workInProgressLimit)
    if (isWorkInProgressLimitBreached)
      Left(new Exception(s"Work in progress limit of the column is exceeded by the move : $card to column: $toColumn"))
    else Right(this.copy(cards = updatedCards, lastMove = Some((card, updatedCard)), velocity = getVelocity(updatedCards, ColumnName.DONE)))
  }

  def getVelocity(updatedCards: Seq[Card], columnName: ColumnName.Value): Int =
    updatedCards.foldLeft(0)((acc, item) => acc + item.currentColumn.map(cc => {
      if (cc.name == columnName)
        item.estimate
      else
        0
    }).getOrElse(0))

  def undoLastMove = {
    this.lastMove.map(lm => moveCard(lm._2, lm._1.currentColumn)).getOrElse(Right(this))
  }
}

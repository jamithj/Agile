package com.board.agile.model

import com.board.agile.exception.AgileException
import com.board.agile.util.ColumnName

/**
  * Created by jamith.jayasekara on 27/06/2017.
  */
case class Iteration(id: Int, columns: Seq[Column], cards: Seq[Card], lastMove: Option[(Card, Card)], velocity: Int) {
  def addCard(card: Card) = this.copy(cards = cards :+ card)

  def moveCard(card: Card, toColumn: Option[Column]):Either[AgileException, Iteration] = {
    val updatedCard = card.copy(currentColumn = toColumn)
    val updatedCards = cards.updated(cards.indexOf(card), updatedCard)
    val isWorkInProgressLimitBreached = toColumn.exists(c => getVelocity(updatedCards, c.name) > c.workInProgressLimit)
    if (isWorkInProgressLimitBreached)
      Left(new AgileException(10011, s"Work in progress limit of the column is exceeded by the move : $card to column: $toColumn"))
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

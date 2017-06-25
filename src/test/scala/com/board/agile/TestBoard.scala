package com.board.agile

/**
  * Created by jamit on 25/06/2017.
  */

import org.scalatest.FunSuite

class TestBoard extends FunSuite{

  val columns = Seq(Column(ColumnName.STARTING, 17), Column(ColumnName.INPROGRESS, 17), Column(ColumnName.DONE, 17))
  val cards = Seq(Card("Title1", "Description1", 3, None), Card("Title2", "Description1", 5, None), Card("Title3", "Description1", 9, None), Card("Title4", "Description1", 16, None))
  var iteration = Iteration(1, columns, Nil, None, 0)
  val board = new Board(iteration)

  val card1 = cards.head
  val card2 = cards(1)
  val card3 = cards(2)
  val card4 = cards(3)
  val columnStarting = columns.head
  val columnInProgress = columns(1)
  val columnDone = columns(2)

  test("agile board") {
    iteration = iteration.addCard(card1)
    assert(iteration.cards == Seq(card1))

    //Moving card1 to starting column
    iteration = iteration.moveCard(card1, Some(columnStarting)).right.get
    val card1InStartingColumn = card1.copy(currentColumn = Some(columnStarting))
    assert(iteration.lastMove == Some(card1, card1InStartingColumn))
    assert(iteration.cards.contains(card1InStartingColumn))
    assert(iteration.velocity == 0)

    //Moving card2 to starting column
    iteration = iteration.addCard(card2)
    iteration = iteration.moveCard(card2, Some(columnStarting)).right.get
    val card2InStartingColumn = card2.copy(currentColumn = Some(columnStarting))
    assert(iteration.lastMove == Some(card2, card2InStartingColumn))
    assert(iteration.cards.contains(card2InStartingColumn))
    assert(iteration.velocity == 0)

    //Moving card3 to starting column
    iteration = iteration.addCard(card3)
    iteration = iteration.moveCard(card3, Some(columnStarting)).right.get
    val card3InStartingColumn = card3.copy(currentColumn = Some(columnStarting))
    assert(iteration.lastMove == Some(card3, card3InStartingColumn))
    assert(iteration.cards.contains(card3InStartingColumn))
    assert(iteration.velocity == 0)

    //Moving card4 to starting column should raise and exception
    iteration = iteration.addCard(card4)
    assert(iteration.moveCard(card4, Some(columnStarting)).isLeft)

  }

}

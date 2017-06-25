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
  val card1 = cards(0)

  test("agile board") {
    iteration = iteration.addCard(card1)
    assert(iteration.cards == Seq(card1))
  }

}

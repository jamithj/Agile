package com.board.agile.model

/**
  * Created by jamith.jayasekara on 27/06/2017.
  */
case class Card(title: String, description: String, estimate: Int, currentColumn: Option[Column] )

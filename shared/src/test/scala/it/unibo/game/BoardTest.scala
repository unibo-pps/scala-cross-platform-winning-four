package it.unibo.game

import org.scalatest._
import flatspec._
import matchers._
import it.unibo.game.core.WinningFour.*

class BoardTest extends AnyFlatSpec with should.Matchers {
  "An empty board" should "place a mark in each column" in {
    val board = Board()
    val firstAvailableRows = (0 to Board.width).map(board.firstAvailableRow(_))
    firstAvailableRows sameElements (0 to Board.width).map(_ => 0)
  }
}

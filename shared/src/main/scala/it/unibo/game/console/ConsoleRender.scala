package it.unibo.game.console

import it.unibo.game.Render
import it.unibo.game.core.WinningFour.Board
import it.unibo.game.core.Player
import it.unibo.game.core.WinningFour.*
object ConsoleRender:
  def apply(exitHandler: => Unit): Render = new Render {
    def render(disks: Board): Unit =
      for
        y <- Board.height to 0 by -1
        x <- 0 to Board.width
      do
        print(disks.findPlayer(x, y).map(_.toString).getOrElse("."))
        if x == Board.width then println()
    def win(player: Player): Unit =
      println(s"Player $player win the game!!")
      exitHandler
  }

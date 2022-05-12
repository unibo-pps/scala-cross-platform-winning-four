package it.unibo.game.console

import it.unibo.game.core.PlayerLogic
import it.unibo.game.core.Player
import scala.concurrent.ExecutionContext
import scala.io.StdIn
import scala.concurrent.Future
object ConsolePlayer:
  def apply(using ExecutionContext): PlayerLogic = (player, board) =>
    Future {
      println(s"$player turn:")
      StdIn.readInt
    }

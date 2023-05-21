package it.unibo.game.ai

import it.unibo.game.core.PlayerLogic
import scala.concurrent.ExecutionContext
import it.unibo.game.core.WinningFour.*
import scala.util.Random
import scala.concurrent.Future
import it.unibo.game.core.WinningFour
import it.unibo.game.core.Player
object AIPlayer:
  def deterministic(using ExecutionContext): PlayerLogic = (player, board) =>
    Future {
      availableMarks(board).head
    }

  def apply()(using ExecutionContext, Random): PlayerLogic = (player, board) =>
    Future {
      summon[Random].shuffle(availableMarks(board)).head
    }

  private def availableMarks(board: Board) = (0 to Board.width) map (x => x -> board.firstAvailableRow(x)) collect {
    case (x, Some(y)) => x
  }

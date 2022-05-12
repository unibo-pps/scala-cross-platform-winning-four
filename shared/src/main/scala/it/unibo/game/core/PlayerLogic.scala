package it.unibo.game.core
import it.unibo.game.core.WinningFour.Board
import scala.concurrent.Future

trait PlayerLogic:
  def next(player: Player, board: Board): Future[Int]

package it.unibo.game

import it.unibo.game.core.Player
import it.unibo.game.core.WinningFour.Board

trait Render:
  def render(board: Board): Unit
  def win(player: Player): Unit

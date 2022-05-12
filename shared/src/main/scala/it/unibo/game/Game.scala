package it.unibo.game

import it.unibo.game.core.PlayerLogic
import it.unibo.game.core.WinningFour.Board
import scala.concurrent.ExecutionContext
import it.unibo.game.core.WinningFour
import scala.concurrent.Future
import it.unibo.game.core.Player.*
import it.unibo.game.core.Player

object Game:
  def start(xLogic: PlayerLogic, yLogic: PlayerLogic, render: Render, winningFour: WinningFour = WinningFour())(using
      ExecutionContext
  ): Future[Unit] =
    render.render(winningFour.disks)
    gameLoop(xLogic, yLogic, render, winningFour)

  def gameLoop(xLogic: PlayerLogic, yLogic: PlayerLogic, render: Render, winningFour: WinningFour)(using
      ExecutionContext
  ): Future[Unit] = for
    xBoard <- step(xLogic, X, render, winningFour)
    yBoard <- step(yLogic, O, render, xBoard)
    next = gameLoop(xLogic, yLogic, render, yBoard)
  yield ()

  def step(logic: PlayerLogic, player: Player, render: Render, winningFour: WinningFour)(using
      ExecutionContext
  ): Future[WinningFour] =
    for
      x <- logic.next(player, winningFour.disks)
      nextGame = winningFour.place(x, player)
      _ <- Future(render.render(nextGame.disks))
      _ <- Future(nextGame.winner.foreach(p => render.win(p)))
    yield nextGame

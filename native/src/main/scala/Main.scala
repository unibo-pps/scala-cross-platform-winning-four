import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import it.unibo.game.console.*
import it.unibo.game.Game

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.StdIn
@main def main(): Unit =
  val consoleLogic = ConsolePlayer.apply
  println("Welcome!!!")
  val game = Game.start(consoleLogic, consoleLogic, ConsoleRender(System.exit(0)))
  Await.result(Future.never, Duration.Inf)
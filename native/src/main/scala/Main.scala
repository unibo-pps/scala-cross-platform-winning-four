import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import it.unibo.game.console.*
import it.unibo.game.Game
import scala.concurrent.Await
import scala.concurrent.duration.Duration
@main def main(): Unit =
  val consoleLogic = ConsolePlayer.apply
  println("Welcome!!!")
  Game.start(consoleLogic, consoleLogic, ConsoleRender(System.exit(0)))

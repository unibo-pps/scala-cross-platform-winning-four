import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import it.unibo.game.console.*
import it.unibo.game.Game
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import it.unibo.game.ai.AIPlayer
import scala.concurrent.Promise
import scala.util.Random
@main def main(): Unit = 
  val consoleLogic = ConsolePlayer.apply
  given Random = Random()
  println("Welcome!!!")
  val endPromise = Promise[Unit]
  Game.start(consoleLogic, AIPlayer(), ConsoleRender { endPromise.success(()) })
  Await.result(endPromise.future, Duration.Inf)
  System.exit(0)
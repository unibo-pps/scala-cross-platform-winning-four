import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import it.unibo.game.console.*
import it.unibo.game.Game
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.scalajs.js.timers
import scala.io.StdIn
import it.unibo.game.core.PlayerLogic
import scala.concurrent.Promise
import scalajs.js
import it.unibo.game.ai.AIPlayer
import scala.util.Random

@main def main(): Unit = 
  given Random = Random()
  val delayBetweenAi = 1000
  def delayGame(delay: Double, logic: PlayerLogic): PlayerLogic = 
    (player, board) => 
      val p = Promise[Unit]()
      js.timers.setTimeout(delay) { p.success(()) }
      p.future.flatMap(_ => logic.next(player, board))
  val aiLogic = delayGame(delayBetweenAi, AIPlayer())
  println("Welcome!!!")

  val future = Game.start(aiLogic, aiLogic, ConsoleRender(throw new Error("End..")))
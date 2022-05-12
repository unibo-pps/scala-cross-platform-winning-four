package it.unibo.game.core
import WinningFour.*
import Board.*
case class WinningFour(disks: Board = Board()):
  private val availableWinningSequence =
    for i <- (0 to width - (winWhen - 1))
    yield (i until (i + winWhen)).toList

  def place(x: Int, player: Player): WinningFour = WinningFour(disks.dropDiskIn(x, player))

  def winner: Option[Player] =
    import Board.*

    def verifyWinner(coords: Seq[Seq[(Int, Int)]]): Option[Player] = coords
      .map(coords => coords.map((x, y) => disks.findPlayer(x, y)))
      .filter(coords => coords.forall(_.isDefined))
      .map(coords => coords.map(_.get))
      .filter(coords => coords.forall(coords.head == _))
      .map(_.head)
      .headOption

    def winnerRows(): Option[Player] =
      val rows = for
        xs <- availableWinningSequence
        row <- 0 to height
      yield (xs.map(_ -> row))
      verifyWinner(rows)

    def winnerColumn(): Option[Player] =
      val columns = for
        ys <- availableWinningSequence
        column <- 0 to width
      yield (ys.map(column -> _))
      verifyWinner(columns)

    def winnerDiagonal(): Option[Player] =
      val diagonals = for
        x <- availableWinningSequence
        y <- availableWinningSequence
      yield x.zip(y)
      verifyWinner(diagonals)

    def winnerAntidiagonal(): Option[Player] =
      val diagonals = for {
        x <- availableWinningSequence
        y <- availableWinningSequence.reverse
      } yield x.zip(y)
      verifyWinner(diagonals)

    winnerRows() orElse winnerColumn() orElse winnerDiagonal() orElse winnerAntidiagonal()

object WinningFour:
  opaque type Board = Seq[Disk]

  object Board:
    val width = 7
    val height = 6
    val winWhen = 4
    def apply(): Board = Seq[Disk]()

  extension (board: Board)
    def findPlayer(x: Int, y: Int): Option[Player] = board
      .find { case Disk(`x`, `y`, player) => true; case _ => false }
      .map(_.player)

    def firstAvailableRow(x: Int): Option[Int] = (0 to Board.height)
      .map(y => y -> findPlayer(x, y))
      .collectFirst { case (y, None) => y }

    def dropDiskIn(x: Int, player: Player): Board = firstAvailableRow(x)
      .map(y => Disk(x, y, player: Player) +: board)
      .getOrElse(board)

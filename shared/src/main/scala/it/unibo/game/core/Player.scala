package it.unibo.game.core

enum Player:
  case X, O
  def other: Player = this match
    case X => O
    case _ => X

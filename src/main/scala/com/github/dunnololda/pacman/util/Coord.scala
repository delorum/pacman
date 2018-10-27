package com.github.dunnololda.pacman.util

/**
  * TODO
  *
  * @author aborunov
  */
case class Coord(x: Int = -1, y: Int = -1) {
  def up: Coord = Coord(x, y - 1)

  def down: Coord = Coord(x, y + 1)

  def left: Coord = Coord(x - 1, y)

  def right: Coord = Coord(x + 1, y)
}

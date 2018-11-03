package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait GameMap {
  def move(from: Coord, to: Coord, c: Char): Boolean

  def canGo(to: Coord): Boolean

  def randomFreePlace: Coord

  def place(coord: Coord, c: Char): Boolean

  def remove(from: Coord, c: Char): Boolean
}

package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.InitCoords
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait GameMap {
  def initCoords: InitCoords

  def move(from: Coord, to: Coord, c: Char): Boolean
}

package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.components.map.{GameMap, GameMapImpl}
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
class Pacman(map: GameMap) {
  private var x = map.initCoords.pacman.x
  private var y = map.initCoords.pacman.y

  private val c = 'X'

  def coord = Coord(x, y)

  def moveUp: Boolean = {
    val res = map.move(coord, Coord(x, y - 1), c)
    if (res) y -= 1
    res
  }

  def moveDown: Boolean = {
    val res = map.move(coord, Coord(x, y + 1), c)
    if (res) y += 1
    res
  }

  def moveLeft: Boolean = {
    val res = map.move(coord, Coord(x - 1, y), c)
    if (res) x -= 1
    res
  }

  def moveRight: Boolean = {
    val res = map.move(coord, Coord(x + 1, y), c)
    if (res) x += 1
    res
  }
}

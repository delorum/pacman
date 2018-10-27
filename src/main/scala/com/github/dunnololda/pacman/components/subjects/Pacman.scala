package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.components.map.GameMap
import com.github.dunnololda.pacman.util.{Coord, Dir}

/**
  * TODO
  *
  * @author aborunov
  */
class Pacman(map: GameMap) {
  private var x = map.initCoords.pacman.x
  private var y = map.initCoords.pacman.y

  private val c = 'X'

  private var innerDir: Dir = Dir.NoDir

  def coord = Coord(x, y)

  def dir: Dir = innerDir

  def dirUp: Boolean = {
    val res = map.canGo(Coord(x, y - 1))
    if (res) innerDir = Dir.Up
    res
  }

  def dirDown: Boolean = {
    val res = map.canGo(Coord(x, y + 1))
    if (res) innerDir = Dir.Down
    res
  }

  def dirLeft: Boolean = {
    val res = map.canGo(Coord(x - 1, y))
    if (res) innerDir = Dir.Left
    res
  }

  def dirRight: Boolean = {
    val res = map.canGo(Coord(x + 1, y))
    if (res) innerDir = Dir.Right
    res
  }

  def noDir: Boolean = {
    innerDir = Dir.NoDir
    true
  }

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

  def action(): Unit = {
    dir match {
      case Dir.Up =>
        val res = moveUp
        if (!res) noDir
      case Dir.Down =>
        val res = moveDown
        if (!res) noDir
      case Dir.Left =>
        val res = moveLeft
        if (!res) noDir
      case Dir.Right =>
        val res = moveRight
        if (!res) noDir
      case Dir.NoDir =>
    }
  }
}

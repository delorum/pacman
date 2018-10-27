package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.map.MapAware

/**
  * TODO
  *
  * @author aborunov
  */
trait PacmanMoveAware extends MapAware with PacmanCoordAware with PacmanCharAware {
  def moveUp: Boolean = {
    val res = map.move(coord, coord.up, c)
    if (res) updateCoord(coord.up)
    res
  }

  def moveDown: Boolean = {
    val res = map.move(coord, coord.down, c)
    if (res) updateCoord(coord.down)
    res
  }

  def moveLeft: Boolean = {
    val res = map.move(coord, coord.left, c)
    if (res) updateCoord(coord.left)
    res
  }

  def moveRight: Boolean = {
    val res = map.move(coord, coord.right, c)
    if (res) updateCoord(coord.right)
    res
  }
}

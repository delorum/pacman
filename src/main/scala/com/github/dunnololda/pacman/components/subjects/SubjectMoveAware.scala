package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait SubjectMoveAware extends MapAware with SubjectCoordAware with SubjectCharAware {
  protected def moveUp: Boolean = {
    move(coord.up)
  }

  protected def moveDown: Boolean = {
    move(coord.down)
  }

  protected def moveLeft: Boolean = {
    move(coord.left)
  }

  protected def moveRight: Boolean = {
    move(coord.right)
  }

  private def move(to: Coord) = {
    val res = map.move(coord, to, c)
    if (res) updateCoord(to)
    res
  }
}

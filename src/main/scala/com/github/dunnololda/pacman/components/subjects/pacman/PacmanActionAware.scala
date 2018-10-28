package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.action.ActionAware
import com.github.dunnololda.pacman.util.Dir

/**
  * TODO
  *
  * @author aborunov
  */
trait PacmanActionAware extends ActionAware with PacmanDirAware with PacmanMoveAware {
  def action(): Unit = {
    dir match {
      case Dir.Up => moveOrNoDir(moveUp)
      case Dir.Down => moveOrNoDir(moveDown)
      case Dir.Left => moveOrNoDir(moveLeft)
      case Dir.Right => moveOrNoDir(moveRight)
      case Dir.NoDir =>
    }
  }

  private def moveOrNoDir(func: => Boolean) = if (!func) noDir
}

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

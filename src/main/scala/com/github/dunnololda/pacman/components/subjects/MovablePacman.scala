package com.github.dunnololda.pacman.components.subjects

/**
  * TODO
  *
  * @author aborunov
  */
trait MovablePacman {
  def moveUp: Boolean

  def moveDown: Boolean

  def moveLeft: Boolean

  def moveRight: Boolean
}

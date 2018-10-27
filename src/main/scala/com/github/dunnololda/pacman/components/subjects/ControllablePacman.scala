package com.github.dunnololda.pacman.components.subjects

/**
  * TODO
  *
  * @author aborunov
  */
trait ControllablePacman {
  def dirUp: Boolean

  def dirDown: Boolean

  def dirLeft: Boolean

  def dirRight: Boolean
}

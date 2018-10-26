package com.github.dunnololda.pacman.components.input

import com.googlecode.lanterna.input.KeyStroke

/**
  * TODO
  *
  * @author aborunov
  */
trait InputAware {
  def pollInput: KeyStroke
}

package com.github.dunnololda.pacman.components.terminal

import com.googlecode.lanterna.terminal.Terminal

/**
  * TODO
  *
  * @author aborunov
  */
trait TerminalAware {
  def terminal: Terminal
}

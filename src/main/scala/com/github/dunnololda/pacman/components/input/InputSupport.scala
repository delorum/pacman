package com.github.dunnololda.pacman.components.input

import com.github.dunnololda.pacman.components.terminal.TerminalAware
import com.googlecode.lanterna.input.KeyStroke

/**
  * TODO
  *
  * @author aborunov
  */
trait InputSupport extends InputAware with TerminalAware {
  override def pollInput: KeyStroke = {
    terminal.pollInput()
  }
}

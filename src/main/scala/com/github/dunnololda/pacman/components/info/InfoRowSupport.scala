package com.github.dunnololda.pacman.components.info

import com.github.dunnololda.pacman.components.terminal.TerminalAware

/**
  * TODO
  *
  * @author aborunov
  */
trait InfoRowSupport extends InfoRowAware with TerminalAware {
  def printString(s: String): Unit = {
    terminal.setCursorPosition(0, 390)
    s.foreach(c => terminal.putCharacter(c))
    terminal.setCursorPosition(538, 390)
    terminal.putCharacter(' ')
    terminal.flush()
  }
}

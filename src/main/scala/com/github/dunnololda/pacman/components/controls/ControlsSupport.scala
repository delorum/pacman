package com.github.dunnololda.pacman.components.controls

import com.github.dunnololda.pacman.components.input.InputAware
import com.github.dunnololda.pacman.components.subjects.SubjectsAware
import com.github.dunnololda.pacman.components.terminal.TerminalAware
import com.googlecode.lanterna.input.KeyType

/**
  * TODO
  *
  * @author aborunov
  */
trait ControlsSupport extends ControlsAware with InputAware with SubjectsAware with TerminalAware {
  def checkInputs(): Unit = {
    val k = pollInput
    if (k != null) {
      if (k.getCharacter == 'q' && k.isCtrlDown) {
        terminal.close()
        sys.exit(0)
      } else {
        k.getKeyType match {
          case KeyType.ArrowUp =>
            pacman.moveUp
          case KeyType.ArrowDown =>
            pacman.moveDown
          case KeyType.ArrowLeft =>
            pacman.moveLeft
          case KeyType.ArrowRight =>
            pacman.moveRight
          case _ =>
        }
      }
      var skip = pollInput
      while (skip != null) skip = pollInput
    }
  }
}

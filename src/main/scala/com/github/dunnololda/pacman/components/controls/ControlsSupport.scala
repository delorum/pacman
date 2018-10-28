package com.github.dunnololda.pacman.components.controls

import com.github.dunnololda.pacman.components.input.InputAware
import com.github.dunnololda.pacman.components.subjects.SubjectsAware
import com.github.dunnololda.pacman.components.terminal.TerminalAware
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.input.KeyType._

import scala.collection.mutable.ArrayBuffer

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
          case ArrowUp => executeOrAddCommand(k.getKeyType)(pacman.dirUp)
          case ArrowDown => executeOrAddCommand(k.getKeyType)(pacman.dirDown)
          case ArrowLeft => executeOrAddCommand(k.getKeyType)(pacman.dirLeft)
          case ArrowRight => executeOrAddCommand(k.getKeyType)(pacman.dirRight)
          case _ =>
        }
      }
      var skip = pollInput
      while (skip != null) skip = pollInput
    }
    tryExecuteCommand()
  }

  private val innerCommands = ArrayBuffer[(KeyType, () => Boolean)]()

  private def executeOrAddCommand(k: KeyType)(func: => Boolean): Unit = {
    if (!func && !innerCommands.exists(_._1 == k)) {
      innerCommands += (k -> (() => func))
    }
  }

  private def tryExecuteCommand(): Unit = {
    if (innerCommands.nonEmpty) {
      val (_, func) = innerCommands.head
      if (func()) {
        innerCommands.remove(0)
      }
    }
  }
}

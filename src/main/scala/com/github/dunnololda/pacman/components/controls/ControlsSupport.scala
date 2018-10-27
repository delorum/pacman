package com.github.dunnololda.pacman.components.controls

import com.github.dunnololda.pacman.components.input.InputAware
import com.github.dunnololda.pacman.components.subjects.SubjectsAware
import com.github.dunnololda.pacman.components.terminal.TerminalAware
import com.googlecode.lanterna.input.KeyType

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
          case KeyType.ArrowUp =>
            val res = pacman.dirUp
            if (!res) addCommand(k.getKeyType)
          case KeyType.ArrowDown =>
            val res = pacman.dirDown
            if (!res) addCommand(k.getKeyType)
          case KeyType.ArrowLeft =>
            val res = pacman.dirLeft
            if (!res) addCommand(k.getKeyType)
          case KeyType.ArrowRight =>
            val res = pacman.dirRight
            if (!res) addCommand(k.getKeyType)
          case _ =>
        }
      }
      var skip = pollInput
      while (skip != null) skip = pollInput
    }
    executeCommand {
      case KeyType.ArrowUp =>
        pacman.dirUp
      case KeyType.ArrowDown =>
        pacman.dirDown
      case KeyType.ArrowLeft =>
        pacman.dirLeft
      case KeyType.ArrowRight =>
        pacman.dirRight
      case _ => true
    }
  }

  private val innerCommands = ArrayBuffer[KeyType]()

  def addCommand(k: KeyType): Unit = {
    if (!innerCommands.contains(k)) {
      innerCommands += k
    }
  }

  def executeCommand(func: KeyType => Boolean): Unit = {
    if (innerCommands.nonEmpty) {
      val k = innerCommands.head
      val res = func(k)
      if (res) {
        innerCommands.remove(0)
      }
    }
  }
}

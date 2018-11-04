package com.github.dunnololda.pacman.components.map
import com.github.dunnololda.pacman.components.terminal.TerminalAware

/**
  * TODO
  *
  * @author aborunov
  */
trait MapSupport extends MapAware with TerminalAware {
  override protected val map: GameMap = new GameMapImpl(terminal)
}

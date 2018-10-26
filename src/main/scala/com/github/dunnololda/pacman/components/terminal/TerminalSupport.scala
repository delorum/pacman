package com.github.dunnololda.pacman.components.terminal

import java.awt.Toolkit

import com.googlecode.lanterna.SGR
import com.googlecode.lanterna.terminal.{DefaultTerminalFactory, Terminal}

/**
  * TODO
  *
  * @author aborunov
  */
trait TerminalSupport extends TerminalAware {
  private val defaultTerminalFactory = new DefaultTerminalFactory()

  override val terminal: Terminal = {
    val terminal = defaultTerminalFactory.createAWTTerminal()

    terminal.setSize(538, 376)
    val screenDimension = Toolkit.getDefaultToolkit.getScreenSize
    terminal.setLocation(
      (screenDimension.width - terminal.getWidth) / 2,
      (screenDimension.height - terminal.getHeight) / 2)
    terminal.setVisible(true)
    terminal.setResizable(false)

    terminal.enableSGR(SGR.BOLD)
    terminal
  }
}

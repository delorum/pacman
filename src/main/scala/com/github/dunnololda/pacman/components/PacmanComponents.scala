package com.github.dunnololda.pacman.components

import com.github.dunnololda.pacman.components.controls.ControlsSupport
import com.github.dunnololda.pacman.components.input.InputSupport
import com.github.dunnololda.pacman.components.map.MapSupport
import com.github.dunnololda.pacman.components.subjects.SubjectsSupport
import com.github.dunnololda.pacman.components.terminal.TerminalSupport

/**
  * TODO
  *
  * @author aborunov
  */
class PacmanComponents
  extends TerminalSupport
    with MapSupport
    with SubjectsSupport
    with InputSupport
    with ControlsSupport

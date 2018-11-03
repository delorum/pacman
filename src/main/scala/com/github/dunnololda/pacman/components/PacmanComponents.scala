package com.github.dunnololda.pacman.components

import com.github.dunnololda.pacman.components.action.ActionSupport
import com.github.dunnololda.pacman.components.apples.ApplesSupport
import com.github.dunnololda.pacman.components.controls.ControlsSupport
import com.github.dunnololda.pacman.components.info.InfoRowSupport
import com.github.dunnololda.pacman.components.input.InputSupport
import com.github.dunnololda.pacman.components.map.{InitCoordsSupport, MapSupport}
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
    with InitCoordsSupport
    with ApplesSupport
    with SubjectsSupport
    with InputSupport
    with ControlsSupport
    with ActionSupport
    with InfoRowSupport

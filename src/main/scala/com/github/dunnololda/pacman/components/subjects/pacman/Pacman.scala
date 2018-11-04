package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.init.InitCoordsAware
import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.components.subjects._

/**
  * TODO
  *
  * @author aborunov
  */
abstract class Pacman
  extends PacmanCharAware
    with SubjectCoordAware
    with SubjectDirAware
    with SubjectMoveAware
    with SubjectActionAware
    with MapAware
    with InitCoordsAware
    with SubjectInitAware

package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.map.{InitCoordsAware, MapAware}

/**
  * TODO
  *
  * @author aborunov
  */
abstract class Pacman
  extends PacmanCharAware
    with PacmanCoordAware
    with PacmanDirAware
    with PacmanMoveAware
    with PacmanActionAware
    with MapAware
    with InitCoordsAware

package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.map.GameMap

/**
  * TODO
  *
  * @author aborunov
  */
class Pacman(val map: GameMap)
  extends PacmanCharAware
    with PacmanCoordAware
    with PacmanDirAware
    with PacmanMoveAware
    with PacmanActionAware

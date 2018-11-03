package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.common.Symbols
import com.github.dunnololda.pacman.components.map.{InitCoordsAware, MapAware}
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait PacmanCoordAware
  extends MapAware
    with InitCoordsAware
    with PacmanCharAware {
  private var x = initCoords.pacman.x
  private var y = initCoords.pacman.y

  def init(): Unit = {
    map.place(initCoords.bySymbol(c), Symbols.PACMAN)
  }

  def updateCoord(newCoord: Coord = coord): Unit = {
    x = newCoord.x
    y = newCoord.y
  }

  def coord = Coord(x, y)
}

package com.github.dunnololda.pacman.components.subjects.pacman

import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait PacmanCoordAware extends MapAware {
  private var x = map.initCoords.pacman.x
  private var y = map.initCoords.pacman.y

  def updateCoord(newCoord: Coord = coord): Unit = {
    x = newCoord.x
    y = newCoord.y
  }

  def coord = Coord(x, y)
}

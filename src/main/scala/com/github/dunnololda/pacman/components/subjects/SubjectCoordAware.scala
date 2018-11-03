package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.common.Symbols
import com.github.dunnololda.pacman.components.init.InitCoordsAware
import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait SubjectCoordAware
  extends MapAware
    with InitCoordsAware
    with SubjectCharAware {
  private var x = initCoords.pacman.x
  private var y = initCoords.pacman.y

  def init(): Unit = {
    map.removeCharacter(coord, c)
    x = initCoords.pacman.x
    y = initCoords.pacman.y
    map.putCharacter(initCoords.bySymbol(c), Symbols.PACMAN)
  }

  protected def updateCoord(newCoord: Coord = coord): Unit = {
    x = newCoord.x
    y = newCoord.y
  }

  def coord = Coord(x, y)
}

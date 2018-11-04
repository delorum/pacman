package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.common.Symbols
import com.github.dunnololda.pacman.components.init.InitCoordsAware
import com.github.dunnololda.pacman.components.map.MapAware

/**
  * TODO
  *
  * @author aborunov
  */
trait SubjectInitAware extends SubjectCoordAware with MapAware with InitCoordsAware with SubjectDirAware {
  def init(): Unit = {
    map.removeCharacter(coord, c)
    updateCoord(initCoords.pacman)
    map.putCharacter(initCoords.bySymbol(c), Symbols.PACMAN)
    noDir
  }
}

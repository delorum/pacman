package com.github.dunnololda.pacman.components.init

import com.github.dunnololda.pacman.common.InitCoords

/**
  * TODO
  *
  * @author aborunov
  */
trait InitCoordsAware {
  def initCoords: InitCoords

  trait InitCoordsAwareImpl extends  InitCoordsAware {
    def initCoords: InitCoords = InitCoordsAware.this.initCoords
  }
}

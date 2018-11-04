package com.github.dunnololda.pacman.components.map

/**
  * TODO
  *
  * @author aborunov
  */
trait MapAware {
  protected def map: GameMap

  trait MapAwareImpl extends MapAware {
    protected def map: GameMap = MapAware.this.map
  }
}

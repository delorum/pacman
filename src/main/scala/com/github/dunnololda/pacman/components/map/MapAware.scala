package com.github.dunnololda.pacman.components.map

/**
  * TODO
  *
  * @author aborunov
  */
trait MapAware {
  def map: GameMap

  trait MapAwareImpl extends MapAware {
    def map: GameMap = MapAware.this.map
  }
}

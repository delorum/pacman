package com.github.dunnololda.pacman.components.subjects

import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.util.Dir

/**
  * TODO
  *
  * @author aborunov
  */
trait SubjectDirAware extends MapAware with SubjectCoordAware {
  private var innerDir: Dir = Dir.NoDir

  def dir: Dir = innerDir

  def dirUp: Boolean = {
    val res = map.canGo(coord.up)
    if (res) innerDir = Dir.Up
    res
  }

  def dirDown: Boolean = {
    val res = map.canGo(coord.down)
    if (res) innerDir = Dir.Down
    res
  }

  def dirLeft: Boolean = {
    val res = map.canGo(coord.left)
    if (res) innerDir = Dir.Left
    res
  }

  def dirRight: Boolean = {
    val res = map.canGo(coord.right)
    if (res) innerDir = Dir.Right
    res
  }

  def noDir: Boolean = {
    innerDir = Dir.NoDir
    true
  }
}

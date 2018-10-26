package com.github.dunnololda.pacman.components.subjects
import com.github.dunnololda.pacman.components.map.MapAware

/**
  * TODO
  *
  * @author aborunov
  */
trait SubjectsSupport extends SubjectsAware with MapAware {
  override val pacman: Pacman = new Pacman(map)
}

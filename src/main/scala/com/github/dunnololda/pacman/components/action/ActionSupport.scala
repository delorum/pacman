package com.github.dunnololda.pacman.components.action

import com.github.dunnololda.pacman.components.subjects.SubjectsAware

/**
  * TODO
  *
  * @author aborunov
  */
trait ActionSupport extends ActionAware with SubjectsAware {
  override def action(): Unit = {
    pacman.action()
  }
}

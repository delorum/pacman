package com.github.dunnololda.pacman

import com.github.dunnololda.pacman.components.PacmanComponents

/**
  * TODO
  *
  * @author aborunov
  */
object PacmanGame extends App {
  val components = new PacmanComponents

  components.printString("Hello World!")

  while (true) {
    components.checkInputs()
    components.action()
    components.map.flush()
    Thread.sleep(100)
  }
}

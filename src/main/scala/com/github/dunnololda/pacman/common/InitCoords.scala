package com.github.dunnololda.pacman.common

import com.github.dunnololda.pacman.util.Coord
import Symbols._

/**
  * TODO
  *
  * @author aborunov
  */
case class InitCoords(pacman: Coord,
                      enemy1: Coord,
                      enemy2: Coord,
                      enemy3: Coord,
                      enemy4: Coord) {
  def bySymbol(c: Char): Coord = {
    c match {
      case PACMAN.c => pacman
      case ENEMY1.c => enemy1
      case ENEMY2.c => enemy2
      case ENEMY3.c => enemy3
      case ENEMY4.c => enemy4
      case _ => sys.error(s"unexpected symbol $c")
    }
  }
}

class InitCoordsBuilder(var pacman: Coord = Coord(),
                        var enemy1: Coord = Coord(),
                        var enemy2: Coord = Coord(),
                        var enemy3: Coord = Coord(),
                        var enemy4: Coord = Coord()) {
  def build(): InitCoords = InitCoords(pacman, enemy1, enemy2, enemy3, enemy4)
}

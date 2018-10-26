package com.github.dunnololda.pacman

import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
case class InitCoords(pacman: Coord, enemy1: Coord, enemy2: Coord, enemy3: Coord, enemy4: Coord)

class InitCoordsBuilder(var pacman: Coord = Coord(),
                        var enemy1: Coord = Coord(),
                        var enemy2: Coord = Coord(),
                        var enemy3: Coord = Coord(),
                        var enemy4: Coord = Coord()) {
  def build(): InitCoords = InitCoords(pacman, enemy1, enemy2, enemy3, enemy4)
}

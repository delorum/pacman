package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.common.{InitCoords, InitCoordsBuilder}
import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait InitCoordsSupport extends InitCoordsAware {
  private val initCoordsBuilder = new InitCoordsBuilder()

  io.Source.fromFile("map1.txt").getLines().zipWithIndex.foreach { case (line, row) =>
    line.zipWithIndex.foreach { case (c, col) =>
      c match {
        case 'X' => initCoordsBuilder.pacman = Coord(col, row)
        case 'B' => initCoordsBuilder.enemy1 = Coord(col, row)
        case 'C' => initCoordsBuilder.enemy2 = Coord(col, row)
        case 'I' => initCoordsBuilder.enemy3 = Coord(col, row)
        case 'P' => initCoordsBuilder.enemy4 = Coord(col, row)
        case _ =>
      }
    }
  }

  val initCoords: InitCoords = initCoordsBuilder.build()
}

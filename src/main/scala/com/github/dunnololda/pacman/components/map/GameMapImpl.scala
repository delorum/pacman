package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.util.Coord
import com.github.dunnololda.pacman.{InitCoords, InitCoordsBuilder}
import com.googlecode.lanterna.terminal.Terminal

/**
  * TODO
  *
  * @author aborunov
  */
class GameMapImpl(terminal: Terminal) extends GameMap {
  private val rows = io.Source.fromFile("map1.txt").getLines().length
  private val cols = io.Source.fromFile("map1.txt").getLines().map(_.length).max

  private val live = Set('X', 'B', 'C', 'I', 'P')

  private val map = Array.ofDim[Char](cols, rows)

  private val initCoordsBuilder = new InitCoordsBuilder()

  io.Source.fromFile("map1.txt").getLines().zipWithIndex.foreach { case (line, row) =>
    line.zipWithIndex.foreach { case (c, col) =>
      terminal.setCursorPosition(col, row)
      if (live(c)) {
        c match {
          case 'X' => initCoordsBuilder.pacman = Coord(col, row)
          case 'B' => initCoordsBuilder.enemy1 = Coord(col, row)
          case 'C' => initCoordsBuilder.enemy2 = Coord(col, row)
          case 'I' => initCoordsBuilder.enemy3 = Coord(col, row)
          case 'P' => initCoordsBuilder.enemy4 = Coord(col, row)
        }
      }
      terminal.putCharacter(c)
      map(col)(row) = c
    }
  }
  terminal.flush()

  val initCoords: InitCoords = initCoordsBuilder.build()

  private val FLOOR = '.'

  def move(from: Coord, to: Coord, c: Char): Boolean = {
    if (map(to.x)(to.y) == FLOOR) {
      terminal.setCursorPosition(from.x, from.y)
      terminal.putCharacter(FLOOR)
      terminal.flush()
      terminal.setCursorPosition(to.x, to.y)
      terminal.putCharacter(c)
      terminal.setCursorPosition(cols, rows - 1)
      terminal.flush()
      map(from.x)(from.y) = FLOOR
      map(to.x)(to.y) = c
      true
    } else false
  }
}

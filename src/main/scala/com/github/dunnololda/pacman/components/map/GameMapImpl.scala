package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.util.Coord
import com.github.dunnololda.pacman.{InitCoords, InitCoordsBuilder}
import com.googlecode.lanterna.terminal.Terminal
import com.github.dunnololda.pacman.common.Symbols._

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

  def canGo(to: Coord): Boolean = {
    map(to.x)(to.y) == FLOOR
  }

  def move(from: Coord, to: Coord, c: Char): Boolean = {
    val res = canGo(to)
    if (res) {
      putCharacter(from, FLOOR)
      putCharacter(to, c)
      flush()
    }
    res
  }

  def randomFreePlace: Coord = {
    val freePlaces = map.zipWithIndex.flatMap { case (column, col) =>
      column.zipWithIndex.filter(_._1 == FLOOR).map { case (_, row) =>
        (col, row)
      }
    }
    if (freePlaces.isEmpty) sys.error("no free place")
    else {
      val (col, row) = freePlaces((math.random() * freePlaces.length).toInt)
      require(map(col)(row) == FLOOR, s"wrong free place ($col, $row) calculated")
      Coord(col, row)
    }
  }

  def place(to: Coord, c: Char): Boolean = {
    val res = canGo(to)
    if (res) {
      putCharacter(to, c)
      flush()
    }
    res
  }

  def remove(from: Coord): Unit = {
    putCharacter(from, FLOOR)
    flush()
  }

  private def putCharacter(to: Coord, c: Char): Unit = {
    terminal.setCursorPosition(to.x, to.y)
    terminal.putCharacter(c)
    map(to.x)(to.y) = c
  }

  private def flush(): Unit = {
    terminal.setCursorPosition(cols, rows - 1)
    terminal.putCharacter(' ')
    terminal.flush()
  }
}

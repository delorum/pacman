package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.common.Symbols._
import com.github.dunnololda.pacman.util.Coord
import com.googlecode.lanterna.terminal.Terminal

import scala.collection.mutable.ArrayBuffer

/**
  * TODO
  *
  * @author aborunov
  */
class GameMapImpl(terminal: Terminal) extends GameMap {
  private val rows = io.Source.fromFile("map1.txt").getLines().length
  private val cols = io.Source.fromFile("map1.txt").getLines().map(_.length).max

  private val live = Set('X', 'B', 'C', 'I', 'P')

  private val map = Array.ofDim[ArrayBuffer[Char]](cols, rows)

  io.Source.fromFile("map1.txt").getLines().zipWithIndex.foreach { case (line, row) =>
    line.padTo(cols, ' ').zipWithIndex.foreach { case (c, col) =>
      terminal.setCursorPosition(col, row)
      val cc = if (live(c)) {
        FLOOR
      } else c
      terminal.putCharacter(cc)
      map(col)(row) = ArrayBuffer[Char](cc)
    }
  }
  terminal.flush()

  def canGo(to: Coord): Boolean = {
    val c = map(to.x)(to.y).last
    c == FLOOR || c == APPLE
  }

  def move(from: Coord, to: Coord, c: Char): Boolean = {
    val res = canGo(to)
    if (res) {
      val res2 = removeCharacter(from, c)
      if (res2) {
        innerPutCharacter(to, c)
      }
      res2
    } else false
  }

  def randomFreePlace: Coord = {
    val freePlaces = for {
      col <- map.indices
      row <- map.head.indices
      coord = Coord(col, row)
      if canGo(coord)
    } yield coord
    if (freePlaces.isEmpty) sys.error("no free place")
    else {
      val c = freePlaces((math.random() * freePlaces.length).toInt)
      c
    }
  }

  def putCharacter(to: Coord, c: Char): Boolean = {
    val res = canGo(to)
    if (res) {
      innerPutCharacter(to, c)
    }
    res
  }

  def removeCharacter(from: Coord, c: Char): Boolean = {
    val arr = map(from.x)(from.y)
    val res = arr.last == c
    if (res) {
      arr.remove(arr.length - 1)
      terminal.setCursorPosition(from.x, from.y)
      terminal.putCharacter(arr.last)
    }
    res
  }

  private def innerPutCharacter(to: Coord, c: Char): Unit = {
    terminal.setCursorPosition(to.x, to.y)
    terminal.putCharacter(c)
    map(to.x)(to.y) += c
  }

  def flush(): Unit = {
    terminal.setCursorPosition(cols, rows - 1)
    terminal.putCharacter(' ')
    terminal.flush()
  }
}

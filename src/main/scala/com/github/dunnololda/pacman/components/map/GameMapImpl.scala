package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.common.Symbols._
import com.github.dunnololda.pacman.util.Coord
import com.googlecode.lanterna.TextColor
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

  private val map = Array.ofDim[ArrayBuffer[Tile]](cols, rows)

  private var prevColor: TextColor = TextColor.ANSI.WHITE

  io.Source.fromFile("map1.txt").getLines().zipWithIndex.foreach { case (line, row) =>
    line.padTo(cols, ' ').zipWithIndex.foreach { case (c, col) =>
      terminal.setCursorPosition(col, row)
      val cc = if (live(c)) {
        FLOOR
      } else bySymbol(c)
      setTerminalColor(cc.color)
      terminal.putCharacter(cc.c)
      map(col)(row) = ArrayBuffer[Tile](cc)
    }
  }

  private def setTerminalColor(color: TextColor): Unit = {
    if (color != prevColor) {
      terminal.setForegroundColor(color)
      prevColor = color
    }
  }

  terminal.flush()

  def canGo(to: Coord): Boolean = {
    val c = map(to.x)(to.y).last
    c == FLOOR || c == APPLE
  }

  def move(from: Coord, to: Coord, tile: Tile): Boolean = {
    val res = canGo(to)
    if (res) {
      val res2 = removeTopCharacter(from, tile)
      if (res2) {
        innerPutCharacter(to, tile)
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

  def putCharacter(to: Coord, tile: Tile): Boolean = {
    val res = canGo(to)
    if (res) {
      innerPutCharacter(to, tile)
    }
    res
  }

  def removeTopCharacter(from: Coord, tile: Tile): Boolean = {
    val arr = map(from.x)(from.y)
    val res = arr.last == tile
    if (res) {
      arr.remove(arr.length - 1)
      terminal.setCursorPosition(from.x, from.y)
      setTerminalColor(arr.last.color)
      terminal.putCharacter(arr.last.c)
    }
    res
  }

  def removeCharacter(from: Coord, tile: Tile): Boolean = {
    val arr = map(from.x)(from.y)
    val res = arr.contains(tile)
    if (res) {
      arr -= tile
      terminal.setCursorPosition(from.x, from.y)
      setTerminalColor(arr.last.color)
      terminal.putCharacter(arr.last.c)
    }
    res
  }

  private def innerPutCharacter(to: Coord, tile: Tile): Unit = {
    terminal.setCursorPosition(to.x, to.y)
    setTerminalColor(tile.color)
    terminal.putCharacter(tile.c)
    map(to.x)(to.y) += tile
  }

  def flush(): Unit = {
    terminal.setCursorPosition(cols, rows - 1)
    setTerminalColor(TextColor.ANSI.WHITE)
    terminal.putCharacter(' ')
    terminal.flush()
  }
}

package com.github.dunnololda.pacman.common

import com.github.dunnololda.pacman.components.map.Tile
import com.googlecode.lanterna.TextColor

/**
  * TODO
  *
  * @author aborunov
  */
object Symbols {
  val FLOOR = Tile('.', TextColor.ANSI.WHITE)
  val WALL = Tile('#', TextColor.ANSI.WHITE)
  val EMPTY = Tile(' ', TextColor.ANSI.WHITE)
  val APPLE = Tile('a', TextColor.ANSI.RED)
  val PACMAN = Tile('X', TextColor.ANSI.YELLOW)
  val ENEMY1 = Tile('B', TextColor.ANSI.BLUE)
  val ENEMY2 = Tile('C', TextColor.ANSI.BLUE)
  val ENEMY3 = Tile('I', TextColor.ANSI.BLUE)
  val ENEMY4 = Tile('P', TextColor.ANSI.BLUE)

  val all: List[Tile] = List(FLOOR, WALL, EMPTY, APPLE, PACMAN, ENEMY1, ENEMY2, ENEMY3, ENEMY4)

  val bySymbol: Map[Char, Tile] = all.map(t => (t.c, t)).toMap
}

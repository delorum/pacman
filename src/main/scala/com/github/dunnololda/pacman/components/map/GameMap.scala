package com.github.dunnololda.pacman.components.map

import com.github.dunnololda.pacman.util.Coord

/**
  * TODO
  *
  * @author aborunov
  */
trait GameMap {
  def move(from: Coord, to: Coord, tile: Tile): Boolean

  def canGo(to: Coord): Boolean

  def randomFreePlace: Coord

  def putCharacter(coord: Coord, tile: Tile): Boolean

  def removeTopCharacter(from: Coord, tile: Tile): Boolean

  def removeCharacter(from: Coord, tile: Tile): Boolean

  def flush(): Unit
}

package com.github.dunnololda.pacman.util

import org.newdawn.slick.util.pathfinding.{AStarPathFinder, PathFindingContext, TileBasedMap}

class PathFinder(width: Int,
                 height: Int,
                 val is_blocked: (Int, Int) => Boolean = (_, _) => false,
                 val cost: (Int, Int) => Float = (_, _) => 1f) {
  private val slickAstarPathFinder = new AStarPathFinder(new TileBasedMap {
    def getWidthInTiles: Int = width

    def getHeightInTiles: Int = height

    def pathFinderVisited(x: Int, y: Int) {}

    def blocked(context: PathFindingContext, tx: Int, ty: Int) = is_blocked(tx, ty)

    def getCost(context: PathFindingContext, tx: Int, ty: Int) = cost(tx, ty)
  }, 100500, true)

  def findPath(p1: Vec, p2: Vec): List[Vec] = {
    val slickPath = slickAstarPathFinder.findPath(null, p1.ix, p1.iy, p2.ix, p2.iy)
    if (slickPath != null) {
      (for (i <- 0 until slickPath.getLength) yield Vec(slickPath.getX(i), slickPath.getY(i))).toList
    } else Nil
  }
}

object PathFinder {
  def apply(width: Int,
            height: Int,
            is_blocked: (Int, Int) => Boolean = (_, _) => false,
            cost: (Int, Int) => Float = (_, _) => 1f): PathFinder = {
    new PathFinder(width, height, is_blocked, cost)
  }
}

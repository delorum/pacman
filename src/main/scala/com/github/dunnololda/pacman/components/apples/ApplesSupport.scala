package com.github.dunnololda.pacman.components.apples

import com.github.dunnololda.pacman.components.map.MapAware
import com.github.dunnololda.pacman.util.Coord
import com.github.dunnololda.pacman.common.Symbols._
import scala.collection.mutable.ArrayBuffer

/**
  * TODO
  *
  * @author aborunov
  */
trait ApplesSupport extends ApplesAware with MapAware {
  private val innerApples = ArrayBuffer[Coord]()

  (1 to 10).foreach(_ => {
    val c = map.randomFreePlace
    map.putCharacter(c, APPLE)
    innerApples += c
  })

  def appleCoords: List[Coord] = innerApples.toList

  def removeApple(i: Int): Unit = {
    if (i < 0 || i > innerApples.length - 1) sys.error(s"i=$i is out of bounds [0, ${innerApples.length - 1}]")
    else {
      val c = innerApples.remove(i)
      map.removeCharacter(c, APPLE)
    }
  }
}

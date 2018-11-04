package com.github.dunnololda.pacman.components.rules

import com.github.dunnololda.pacman.components.apples.ApplesAware
import com.github.dunnololda.pacman.components.info.InfoRowAware
import com.github.dunnololda.pacman.components.subjects.SubjectsAware

/**
  * TODO
  *
  * @author aborunov
  */
trait RulesSupport extends RulesAware with InfoRowAware with ApplesAware with SubjectsAware {
  private var gameCount = 1
  private var wins = 0
  private var loses = 0

  private var isWinMessage: Boolean = false
  private var winMessageTimerStart: Long = 0L

  updateInfoRow()

  def checkRules(): Unit = {
    if (appleCoords.isEmpty) {
      gameCount += 1
      wins += 1
      pacman.init()
      pacman.noDir
      initApples()
      printWin()
    } else {
      appleCoords.zipWithIndex.find { case (coord, _) =>
        coord == pacman.coord
      } match {
        case Some((_, idx)) =>
          removeApple(idx)
          updateInfoRow()
        case None =>
          if (isWinMessage) {
            if (System.currentTimeMillis() - winMessageTimerStart > 2000) {
              isWinMessage = false
              updateInfoRow()
            }
          }
      }
    }
  }

  private def printWin(): Unit = {
    printString("Победа!")
    isWinMessage = true
    winMessageTimerStart = System.currentTimeMillis()
  }

  private def updateInfoRow(): Unit = {
    printString(s"Игра $gameCount Яблок: ${appleCoords.length} Побед: $wins Поражений: $loses")
  }
}

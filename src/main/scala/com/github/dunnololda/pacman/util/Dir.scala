package com.github.dunnololda.pacman.util

/**
  * TODO
  *
  * @author aborunov
  */
sealed trait Dir

object Dir {
  case object Up extends Dir
  case object Down extends Dir
  case object Left extends Dir
  case object Right extends Dir
  case object NoDir extends Dir
}

package com.github.dunnololda.pacman.util

/**
  * I believe for now this glorious piece of code has all the needed to be the exact match to 'case Vec(x:Int, y:Int) {...}':
  * 1. lots of apply() methods
  * 2. one pretty cool unapply() method
  * 3. redefined equals(), hashCode() and canEquals() methods! (- that part was hard)
  */
object Vec {
  def apply(x: Float, y: Float) = new Vec(x, y)

  def apply(v: Vec): Vec = v.copy

  def apply(x: Double, y: Double) = new Vec(x.toFloat, y.toFloat)

  def apply() = new Vec(0, 0)

  def unapply(data: Any): Option[(Float, Float)] = data match {
    case v: Vec => Some((v.x, v.y))
    case _ => None
  }

  lazy val zero = new Vec(0, 0)
}

class Vec(val x: Float = 0, val y: Float = 0) {
  lazy val ix: Int = x.toInt
  lazy val iy: Int = y.toInt

  def this(v: Vec) = this(v.x, v.y)

  def this(x: Double, y: Double) = this(x.toFloat, y.toFloat)

  def this(x: Int, y: Int) = this(x.toFloat, y.toFloat)

  def this() = this(0, 0)

  def +(v: Vec) = new Vec(x + v.x, y + v.y)

  def -(v: Vec) = new Vec(x - v.x, y - v.y)

  def unary_-(): Vec = new Vec(-x, -y)

  def project(v: Vec): Vec = v * (this * v)

  def *(v: Vec): Float = x * v.x + y * v.y

  def */(v: Vec): Float = x * v.y - y * v.x // maybe another symbol as it is a closing comment symbol
  def */(k: Float): Vec = Vec(y * k, -x * k)

  def *(k: Double) = new Vec(x * k, y * k)

  def *(k: Float) = new Vec(x * k, y * k)

  def *(k: Int) = new Vec(x * k, y * k)

  def **(v: Vec) = new Vec(x * v.x, y * v.y)

  def /(k: Double): Vec = this / k.toFloat

  def /(k: Float): Vec = if (k == 0) Vec(x * 1000, y * 1000) else Vec(x / k, y / k)

  def /(k: Int): Vec = this / k.toFloat

  def norma2: Float = x * x + y * y

  def norma: Float = math.sqrt(norma2).toFloat

  def n: Vec = this / norma

  def perpendicular = new Vec(-y, x)

  def p: Vec = perpendicular.n

  def dist2(v: Vec): Float = (x - v.x) * (x - v.x) + (y - v.y) * (y - v.y)

  def dist(v: Vec): Float = math.sqrt(dist2(v)).toFloat

  def notZero: Boolean = x != 0 || y != 0

  def isZero: Boolean = x == 0 && y == 0

  override def equals(other: Any): Boolean = other match {
    case that: Vec => (that canEqual this) && this.x == that.x && this.y == that.y
    case _ => false
  }

  override val hashCode: Int = (41 * (41 + x) + y).toInt

  def canEqual(other: Any): Boolean = other.isInstanceOf[Vec]

  def absDeg(v: Vec): Float = math.acos(math.max(math.min(n * v.n, 1.0), -1.0)).toFloat / math.Pi.toFloat * 180f

  def deg(v: Vec): Float = absDeg(v)

  def signedDeg(v: Vec): Float = {
    val scalar = perpendicular * v
    if (scalar >= 0) absDeg(v) else -absDeg(v)
  }

  def absRad(v: Vec): Float = math.acos(math.max(math.min(n * v.n, 1.0), -1.0)).toFloat

  def rad(v: Vec): Float = absRad(v)

  def signedRad(v: Vec): Float = {
    val scalar = perpendicular * v
    if (scalar >= 0) absRad(v) else -absRad(v)
  }

  def rotateRad(ang_rad: Double) = new Vec((x * math.cos(ang_rad) - y * math.sin(ang_rad)).toFloat,
    (x * math.sin(ang_rad) + y * math.cos(ang_rad)).toFloat)

  def rotate(ang_rad: Double): Vec = rotateRad(ang_rad)

  def rotateDeg(ang_deg: Double): Vec = rotateRad(ang_deg / 180 * math.Pi)

  def copy = new Vec(x, y)

  def copy(x: Float = x, y: Float = y) = new Vec(x, y)

  def toDVec = new DVec(x, y)

  override def toString = s"Vec($x, $y)"

  def map[A](f: (Vec) => A): A = f(this)
}

object DVec {
  def apply(x: Float, y: Float) = new DVec(x, y)

  def apply(dv: DVec): DVec = dv.copy

  def apply(x: Double, y: Double) = new DVec(x, y)

  def apply() = new DVec(0, 0)

  def unapply(data: Any): Option[(Double, Double)] = data match {
    case dv: DVec => Some((dv.x, dv.y))
    case _ => None
  }

  lazy val dzero = new DVec(0, 0)
  lazy val zero = new DVec(0, 0)
}

class DVec(val x: Double = 0, val y: Double = 0) {
  lazy val ix: Int = x.toInt
  lazy val iy: Int = y.toInt

  def this(dv: DVec) = this(dv.x, dv.y)

  def this(v: Vec) = this(v.x, v.y)

  def this() = this(0, 0)

  def +(dv: DVec) = new DVec(x + dv.x, y + dv.y)

  def -(dv: DVec) = new DVec(x - dv.x, y - dv.y)

  def unary_-(): DVec = new DVec(-x, -y)

  def *(dv: DVec): Double = x * dv.x + y * dv.y

  def */(v: DVec): Double = x * v.y - y * v.x

  def */(k: Double): DVec = DVec(y * k, -x * k)

  def *(k: Double) = new DVec(x * k, y * k)

  def *(k: Float) = new DVec(x * k, y * k)

  def *(k: Int) = new DVec(x * k, y * k)

  def **(dv: DVec) = new DVec(x * dv.x, y * dv.y)

  def /(k: Double): DVec = if (k == 0) new DVec(x * 1000, y * 1000) else new DVec(x / k, y / k)

  def /(k: Float): DVec = this / k.toDouble

  def /(k: Int): DVec = this / k.toDouble

  def norma2: Double = x * x + y * y

  def norma: Double = math.sqrt(norma2)

  def n: DVec = this / norma

  def perpendicular = new DVec(-y, x)

  def p: DVec = perpendicular.n

  def dist2(dv: DVec): Double = (x - dv.x) * (x - dv.x) + (y - dv.y) * (y - dv.y)

  def dist(dv: DVec): Double = math.sqrt(dist2(dv))

  def notZero: Boolean = x != 0 || y != 0

  def isZero: Boolean = x == 0 && y == 0

  override def equals(other: Any): Boolean = other match {
    case that: DVec => (that canEqual this) && this.x == that.x && this.y == that.y
    case _ => false
  }

  override val hashCode: Int = (41 * (41 + x) + y).toInt

  def canEqual(other: Any): Boolean = other.isInstanceOf[DVec]

  def absDeg(dv: DVec): Double = 180 / math.Pi * math.acos(math.max(math.min(n * dv.n, 1.0), -1.0))

  def deg(dv: DVec): Double = absDeg(dv)

  def signedDeg(dv: DVec): Double = {
    val scalar = perpendicular * dv
    if (scalar >= 0) absDeg(dv) else -absDeg(dv)
  }

  def absRad(dv: DVec): Double = math.acos(math.max(math.min(n * dv.n, 1.0), -1.0))

  def rad(dv: DVec): Double = absRad(dv)

  def signedRad(dv: DVec): Double = {
    val scalar = perpendicular * dv
    if (scalar >= 0) absRad(dv) else -absRad(dv)
  }

  def rotateRad(ang_rad: Double) = new DVec(x * math.cos(ang_rad) - y * math.sin(ang_rad),
    x * math.sin(ang_rad) + y * math.cos(ang_rad))

  def rotate(ang_rad: Double): DVec = rotateRad(ang_rad)

  def rotateDeg(ang_deg: Double): DVec = rotateRad(ang_deg / 180 * math.Pi)

  def copy = new DVec(x, y)

  def copy(x: Double = x, y: Double = y) = new DVec(x, y)

  def toVec = new Vec(x, y)

  override def toString = s"DVec($x, $y)"

  def map[A](f: (DVec) => A): A = f(this)
}
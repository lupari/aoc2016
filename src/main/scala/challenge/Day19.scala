package challenge

import base.Challenge

object Day19 extends Challenge {

  def solveSimple(n: Int): Int = {
    val value = n - Integer.highestOneBit(n)
    2 * value + 1
  }

  override def run(): Any = {
    solveSimple(3018458)
  }

}

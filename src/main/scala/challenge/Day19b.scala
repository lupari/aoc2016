package challenge

import scala.collection.mutable.ArrayBuffer

import base.Challenge

object Day19b extends Challenge {

  def solve(n: Int) = {
    val left = ArrayBuffer[Int]()
    val right = ArrayBuffer[Int]()
    for (i <- 1 to n) {
      if (i < (n / 2).toInt + 1) left += i else i +=: right
    }

    while (left.nonEmpty && right.nonEmpty) {
      if (left.length > right.length) left.remove(left.length - 1)
      else right.remove(right.length - 1)
      left.remove(0) +=: right
      left += right.remove(right.length-1)
    }

    if (left.nonEmpty) left.head else right.head
  }

  override def run(): Any = {
    solve(3012210)
  }

}

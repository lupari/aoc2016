package challenge


object Day19 {

  def solveSimple(n: Int): Int = {
    val value = n - Integer.highestOneBit(n)
    2 * value + 1
  }

  def run(): Int = {
    solveSimple(3018458)
  }

}

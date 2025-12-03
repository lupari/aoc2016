package challenge


object Day19b {

  def solve(n: Int): Int = {
    var prev = 2
    for (i <- 4 to n) {
      prev += 1
      prev %= (i - 1)
      if (prev >= i / 2) prev = prev + 1
    }
    prev + 1
  }

  def run(): Int = {
    solve(3018458)
  }

}

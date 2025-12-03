import challenge.Day11
import org.scalatest.funsuite.AnyFunSuite

class Day11Test extends AnyFunSuite {

  test("Day11 should produce expected result") {
    val result = Day11.run()
    assert(result == 37, s"Bad test result for Day11, expected 37 but got ${result}")
  }

}

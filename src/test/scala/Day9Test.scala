import challenge.Day9
import org.scalatest.funsuite.AnyFunSuite

class Day9Test extends AnyFunSuite {

  test("Day9 should produce expected result") {
    val result = Day9.run()
    assert(result == 115118, s"Bad test result for Day9, expected 115118 but got ${result}")
  }

}

import challenge.Day8
import org.scalatest.funsuite.AnyFunSuite

class Day8Test extends AnyFunSuite {

  test("Day8 should produce expected result") {
    val result = Day8.run()
    assert(result == 115, s"Bad test result for Day8, expected 115 but got ${result}")
  }

}

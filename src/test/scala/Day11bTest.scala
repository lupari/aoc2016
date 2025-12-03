import challenge.Day11b
import org.scalatest.funsuite.AnyFunSuite

class Day11bTest extends AnyFunSuite {

  test("Day11b should produce expected result") {
    val result = Day11b.run()
    assert(result == 61, s"Bad test result for Day11b, expected 61 but got ${result}")
  }

}

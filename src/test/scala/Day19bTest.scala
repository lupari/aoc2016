import challenge.Day19b
import org.scalatest.funsuite.AnyFunSuite

class Day19bTest extends AnyFunSuite {

  test("Day19b should produce expected result") {
    val result = Day19b.run()
    assert(result == 1424135, s"Bad test result for Day19b, expected 1424135 but got ${result}")
  }

}

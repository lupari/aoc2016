import challenge.Day19
import org.scalatest.funsuite.AnyFunSuite

class Day19Test extends AnyFunSuite {

  test("Day19 should produce expected result") {
    val result = Day19.run()
    assert(result == 1842613, s"Bad test result for Day19, expected 1842613 but got ${result}")
  }

}

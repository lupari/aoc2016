import challenge.Day13
import org.scalatest.funsuite.AnyFunSuite

class Day13Test extends AnyFunSuite {

  test("Day13 should produce expected result") {
    val result = Day13.run()
    assert(result == 82, s"Bad test result for Day13, expected 82 but got ${result}")
  }

}

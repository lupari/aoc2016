import challenge.Day14
import org.scalatest.funsuite.AnyFunSuite

class Day14Test extends AnyFunSuite {

  test("Day14 should produce expected result") {
    val result = Day14.run()
    assert(result == 25427, s"Bad test result for Day14, expected 25427 but got ${result}")
  }

}

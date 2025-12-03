import challenge.Day2
import org.scalatest.funsuite.AnyFunSuite

class Day2Test extends AnyFunSuite {

  test("Day2 should produce expected result") {
    val result = Day2.run()
    assert(result == 98575, s"Bad test result for Day2, expected 98575 but got ${result}")
  }

}

import challenge.Day15
import org.scalatest.funsuite.AnyFunSuite

class Day15Test extends AnyFunSuite {

  test("Day15 should produce expected result") {
    val result = Day15.run()
    assert(result == 148737, s"Bad test result for Day15, expected 148737 but got ${result}")
  }

}

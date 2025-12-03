import challenge.Day10
import org.scalatest.funsuite.AnyFunSuite

class Day10Test extends AnyFunSuite {

  test("Day10 should produce expected result") {
    val result = Day10.run()
    assert(result == 141, s"Bad test result for Day10, expected 141 but got ${result}")
  }

}

import challenge.Day7
import org.scalatest.funsuite.AnyFunSuite

class Day7Test extends AnyFunSuite {

  test("Day7 should produce expected result") {
    val result = Day7.run()
    assert(result == 115, s"Bad test result for Day7, expected 115 but got ${result}")
  }

}

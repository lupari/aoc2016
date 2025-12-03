import challenge.Day1
import org.scalatest.funsuite.AnyFunSuite

class Day1Test extends AnyFunSuite {

  test("Day1 should produce expected result") {
    val result = Day1.run()
    assert(result == 262, s"Bad test result for Day1, expected 262 but got ${result}")
  }

}

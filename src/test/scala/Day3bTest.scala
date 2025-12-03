import challenge.Day3b
import org.scalatest.funsuite.AnyFunSuite

class Day3bTest extends AnyFunSuite {

  test("Day3b should produce expected result") {
    val result = Day3b.run()
    assert(result == 1649, s"Bad test result for Day3b, expected 1649 but got ${result}")
  }

}

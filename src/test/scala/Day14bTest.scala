import challenge.Day14b
import org.scalatest.funsuite.AnyFunSuite

class Day14bTest extends AnyFunSuite {

  test("Day14b should produce expected result") {
    val result = Day14b.run()
    assert(result == 22045, s"Bad test result for Day14b, expected 22045 but got ${result}")
  }

}

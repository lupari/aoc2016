import challenge.Day9b
import org.scalatest.funsuite.AnyFunSuite

class Day9bTest extends AnyFunSuite {

  test("Day9b should produce expected result") {
    val result = Day9b.run()
    assert(result == 11107527530L, s"Bad test result for Day9b, expected 11107527530L but got ${result}")
  }

}

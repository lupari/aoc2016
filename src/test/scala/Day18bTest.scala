import challenge.Day18b
import org.scalatest.funsuite.AnyFunSuite

class Day18bTest extends AnyFunSuite {

  test("Day18b should produce expected result") {
    val result = Day18b.run()
    assert(result == 19998750, s"Bad test result for Day18b, expected 19998750 but got ${result}")
  }

}

import challenge.Day12b
import org.scalatest.funsuite.AnyFunSuite

class Day12bTest extends AnyFunSuite {

  test("Day12b should produce expected result") {
    val result = Day12b.run()
    assert(result == 9227737, s"Bad test result for Day12b, expected 9227737 but got ${result}")
  }

}

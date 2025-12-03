import challenge.Day12
import org.scalatest.funsuite.AnyFunSuite

class Day12Test extends AnyFunSuite {

  test("Day12 should produce expected result") {
    val result = Day12.run()
    assert(result == 318083, s"Bad test result for Day12, expected 318083 but got ${result}")
  }

}

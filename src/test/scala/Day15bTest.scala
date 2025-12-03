import challenge.Day15b
import org.scalatest.funsuite.AnyFunSuite

class Day15bTest extends AnyFunSuite {

  test("Day15b should produce expected result") {
    val result = Day15b.run()
    assert(result == 2353212, s"Bad test result for Day15b, expected 2353212 but got ${result}")
  }

}

import challenge.Day18
import org.scalatest.funsuite.AnyFunSuite

class Day18Test extends AnyFunSuite {

  test("Day18 should produce expected result") {
    val result = Day18.run()
    assert(result == 2016, s"Bad test result for Day18, expected 2016 but got ${result}")
  }

}

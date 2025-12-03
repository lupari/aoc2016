import challenge.Day10b
import org.scalatest.funsuite.AnyFunSuite

class Day10bTest extends AnyFunSuite {

  test("Day10b should produce expected result") {
    val result = Day10b.run()
    assert(result == 1209, s"Bad test result for Day10b, expected 1209 but got ${result}")
  }

}

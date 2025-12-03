import challenge.Day24
import org.scalatest.funsuite.AnyFunSuite

class Day24Test extends AnyFunSuite {

  test("Day24 should produce expected result") {
    val result = Day24.run()
    assert(result == 428, s"Bad test result for Day24, expected 428 but got ${result}")
  }

}

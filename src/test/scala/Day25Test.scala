import challenge.Day25
import org.scalatest.funsuite.AnyFunSuite

class Day25Test extends AnyFunSuite {

  test("Day25 should produce expected result") {
    val result = Day25.run()
    assert(result == 192, s"Bad test result for Day25, expected 192 but got ${result}")
  }

}

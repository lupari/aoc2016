import challenge.Day24b
import org.scalatest.funsuite.AnyFunSuite

class Day24bTest extends AnyFunSuite {

  test("Day24b should produce expected result") {
    val result = Day24b.run()
    assert(result == 680, s"Bad test result for Day24b, expected 680 but got ${result}")
  }

}

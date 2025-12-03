import challenge.Day13b
import org.scalatest.funsuite.AnyFunSuite

class Day13bTest extends AnyFunSuite {

  test("Day13b should produce expected result") {
    val result = Day13b.run()
    assert(result == 138, s"Bad test result for Day13b, expected 138 but got ${result}")
  }

}

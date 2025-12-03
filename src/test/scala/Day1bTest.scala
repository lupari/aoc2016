import challenge.Day1b
import org.scalatest.funsuite.AnyFunSuite

class Day1bTest extends AnyFunSuite {

  test("Day1b should produce expected result") {
    val result = Day1b.run()
    assert(result == 131, s"Bad test result for Day1b, expected 131 but got ${result}")
  }

}

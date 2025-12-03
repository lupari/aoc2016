import challenge.Day7b
import org.scalatest.funsuite.AnyFunSuite

class Day7bTest extends AnyFunSuite {

  test("Day7b should produce expected result") {
    val result = Day7b.run()
    assert(result == 231, s"Bad test result for Day7b, expected 231 but got ${result}")
  }

}

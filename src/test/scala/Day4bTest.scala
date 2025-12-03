import challenge.Day4b
import org.scalatest.funsuite.AnyFunSuite

class Day4bTest extends AnyFunSuite {

  test("Day4b should produce expected result") {
    val result = Day4b.run()
    assert(result == 501, s"Bad test result for Day4b, expected 501 but got ${result}")
  }

}

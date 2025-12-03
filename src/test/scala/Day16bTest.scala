import challenge.Day16b
import org.scalatest.funsuite.AnyFunSuite

class Day16bTest extends AnyFunSuite {

  test("Day16b should produce expected result") {
    val result = Day16b.run()
    assert(result == "01101100001100100", s"Bad test result for Day16b, expected [^ ]* but got ${result}")
  }

}

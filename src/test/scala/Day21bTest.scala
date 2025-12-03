import challenge.Day21b
import org.scalatest.funsuite.AnyFunSuite

class Day21bTest extends AnyFunSuite {

  test("Day21b should produce expected result") {
    val result = Day21b.run()
    assert(result == "bcfaegdh", s"Bad test result for Day21b, expected [^ ]* but got ${result}")
  }

}

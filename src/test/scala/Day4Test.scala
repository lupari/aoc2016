import challenge.Day4
import org.scalatest.funsuite.AnyFunSuite

class Day4Test extends AnyFunSuite {

  test("Day4 should produce expected result") {
    val result = Day4.run()
    assert(result == 137896, s"Bad test result for Day4, expected 137896 but got ${result}")
  }

}

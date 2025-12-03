import challenge.Day3
import org.scalatest.funsuite.AnyFunSuite

class Day3Test extends AnyFunSuite {

  test("Day3 should produce expected result") {
    val result = Day3.run()
    assert(result == 917, s"Bad test result for Day3, expected 917 but got ${result}")
  }

}

import challenge.Day20
import org.scalatest.funsuite.AnyFunSuite

class Day20Test extends AnyFunSuite {

  test("Day20 should produce expected result") {
    val result = Day20.run()
    assert(result == 14975795, s"Bad test result for Day20, expected 14975795 but got ${result}")
  }

}

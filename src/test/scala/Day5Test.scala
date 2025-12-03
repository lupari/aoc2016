import challenge.Day5
import org.scalatest.funsuite.AnyFunSuite

class Day5Test extends AnyFunSuite {

  test("Day5 should produce expected result") {
    val result = Day5.run()
    assert(result == "f77a0e6e", s"Bad test result for Day5, expected f77a0e6e but got ${result}")
  }

}

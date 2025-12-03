import challenge.Day5b
import org.scalatest.funsuite.AnyFunSuite

class Day5bTest extends AnyFunSuite {

  test("Day5b should produce expected result") {
    val result = Day5b.run()
    assert(result == "999828ec", s"Bad test result for Day5b, expected [^ ]* but got ${result}")
  }

}

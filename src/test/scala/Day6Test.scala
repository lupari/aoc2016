import challenge.Day6
import org.scalatest.funsuite.AnyFunSuite

class Day6Test extends AnyFunSuite {

  test("Day6 should produce expected result") {
    val result = Day6.run()
    assert(result == "dzqckwsd", s"Bad test result for Day6, expected [^ ]* but got ${result}")
  }

}

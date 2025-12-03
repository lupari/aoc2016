import challenge.Day17
import org.scalatest.funsuite.AnyFunSuite

class Day17Test extends AnyFunSuite {

  test("Day17 should produce expected result") {
    val result = Day17.run()
    assert(result == "DUDRLRRDDR", s"Bad test result for Day17, expected [^ ]* but got ${result}")
  }

}

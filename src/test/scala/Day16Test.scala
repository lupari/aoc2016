import challenge.Day16
import org.scalatest.funsuite.AnyFunSuite

class Day16Test extends AnyFunSuite {

  test("Day16 should produce expected result") {
    val result = Day16.run()
    assert(result == "10111110010110110", s"Bad test result for Day16, expected [^ ]* but got ${result}")
  }

}

import challenge.Day6b
import org.scalatest.funsuite.AnyFunSuite

class Day6bTest extends AnyFunSuite {

  test("Day6b should produce expected result") {
    val result = Day6b.run()
    assert(result == "lragovly", s"Bad test result for Day6b, expected [^ ]* but got ${result}")
  }

}

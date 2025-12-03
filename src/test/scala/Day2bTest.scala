import challenge.Day2b
import org.scalatest.funsuite.AnyFunSuite

class Day2bTest extends AnyFunSuite {

  test("Day2b should produce expected result") {
    val result = Day2b.run()
    assert(result == "cd8d4", s"Bad test result for Day2b, expected [^ ]* but got ${result}")
  }

}

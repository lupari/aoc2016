import challenge.Day22b
import org.scalatest.funsuite.AnyFunSuite

class Day22bTest extends AnyFunSuite {

  test("Day22b should produce expected result") {
    val result = Day22b.run()
    assert(result == "^ Check the grid and take it from there ^", s"Bad test result for Day22b, expected [^ ]* but got ${result}")
  }

}

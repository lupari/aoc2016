import challenge.Day22
import org.scalatest.funsuite.AnyFunSuite

class Day22Test extends AnyFunSuite {

  test("Day22 should produce expected result") {
    val result = Day22.run()
    assert(result == 981, s"Bad test result for Day22, expected 981 but got ${result}")
  }

}

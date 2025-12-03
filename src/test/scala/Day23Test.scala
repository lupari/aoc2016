import challenge.Day23
import org.scalatest.funsuite.AnyFunSuite

class Day23Test extends AnyFunSuite {

  test("Day23 should produce expected result") {
    val result = Day23.run()
    assert(result == 11610, s"Bad test result for Day23, expected 11610 but got ${result}")
  }

}

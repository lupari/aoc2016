import challenge.Day23b
import org.scalatest.funsuite.AnyFunSuite

class Day23bTest extends AnyFunSuite {

  test("Day23b should produce expected result") {
    val result = Day23b.run()
    assert(result == 479008170, s"Bad test result for Day23b, expected 479008170 but got ${result}")
  }

}

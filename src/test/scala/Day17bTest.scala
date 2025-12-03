import challenge.Day17b
import org.scalatest.funsuite.AnyFunSuite

class Day17bTest extends AnyFunSuite {

  test("Day17b should produce expected result") {
    val result = Day17b.run()
    assert(result == 788, s"Bad test result for Day17b, expected 788 but got ${result}")
  }

}

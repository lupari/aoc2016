import challenge.Day20b
import org.scalatest.funsuite.AnyFunSuite

class Day20bTest extends AnyFunSuite {

  test("Day20b should produce expected result") {
    val result = Day20b.run()
    assert(result == 101, s"Bad test result for Day20b, expected 101 but got ${result}")
  }

}

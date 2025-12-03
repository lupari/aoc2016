import challenge.Day8b
import org.scalatest.funsuite.AnyFunSuite

class Day8bTest extends AnyFunSuite {

  test("Day8b should produce expected result") {
    val result = Day8b.run()
    assert(result == "^ Should spell 'EFEYKFRFIJ' :) ^", s"Bad test result for Day8b, expected ^ Should spell 'EFEYKFRFIJ' :) ^ but got ${result}")
  }

}

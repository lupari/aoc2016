import challenge.Day21
import org.scalatest.funsuite.AnyFunSuite

class Day21Test extends AnyFunSuite {

  test("Day21 should produce expected result") {
    val result = Day21.run()
    assert(result == "gbhafcde", s"Bad test result for Day21, expected [^ ]* but got ${result}")
  }

}

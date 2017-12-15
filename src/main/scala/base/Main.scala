package base

import scala.collection.immutable.ListMap

import challenge._

object Main extends App {

  def challenges: Map[String, (Challenge, Any)] = ListMap(
    "1" -> (Day1, 298),
    "1b" -> (Day1b, 158),
    "2" -> (Day2, 18843),
    "2b" -> (Day2b, "67bb9"),
    "3" -> (Day3, 1032),
    "3b" -> (Day3b, 1838),
    "4" -> (Day4, 185371),
    "4b" -> (Day4b, 984),
    "5" -> (Day5, "1a3099aa"),
    "5b" -> (Day5b, "694190cd"),
    "6" -> (Day6, "ikerpcty"),
    "6b" -> (Day6b, "uwpfaqrq"),
    "7" -> (Day7, 105),
    "7b" -> (Day7b, 258),
    "8" -> (Day8, 121),
    "8b" -> (Day8b, "^ Should spell 'RURUCEOEIL' :) ^"),
    "9" -> (Day9, 110346),
    "9b" -> (Day9b, 10774309173L),
    "10" -> (Day10, 27),
    "10b" -> (Day10b, 13727),
    "11" -> (Day11, 31),
    "11b" -> (Day11b, 55),
    "12" -> (Day12, 318117),
    "12b" -> (Day12b, 9227771),
    "13" -> (Day13, 82),
    "13b" -> (Day13b, 138),
    "14" -> (Day14, 23769),
    "14b" -> (Day14b, 20606),
    "15" -> (Day15, 121834),
    "15b" -> (Day15b, 3208099),
    "16" -> (Day16, "10101001010100001"),
    "16b" -> (Day16b, "10100001110101001"),
    "17" -> (Day17, "RDRRULDDDR"),
    "17b" -> (Day17b, 392),
    "18" -> (Day18, 1963),
    "18b" -> (Day18b, 20009568),
    "19" -> (Day19, 1830117),
    "19b" -> (Day19b, 1417887),
    "20" -> (Day20, 14975795),
    "20b" -> (Day20b, 101),
    "21" -> (Day21, "gcedfahb"),
    "21b" -> (Day21b, "hegbdcfa"),
    "22" -> (Day22, 1003),
    "22b" -> (Day22b, Nil),
    "23" -> (Day23, 12330),
    "23b" -> (Day23b, 479008890),
    "24" -> (Day24, 464),
    "24b" -> (Day24b, 652),
    "25" -> (Day25, 158)
  )

  def check(key: String): Unit = {
    val entry = challenges(key)
    val (result, expected) = (entry._1.run(), entry._2)
    println("result for " + key + " = " + result)
    assert(result == expected, "Bad test result for key " + key + ", expected " + expected + " but got " + result)
  }

  if (args.length != 1) {
    println("wrong number of args")
  } else {
    args(0) match {
      case "all" => challenges.keys.foreach(check)
      case k if challenges.contains(k) => check(k)
      case _ => println("not found")
    }
  }


}

package base

import challenge._

import scala.collection.immutable.ListMap

object Main extends App {

  def challenges: Map[String, (Challenge, Any)] = ListMap(
    "1"   -> (Day1, 262),
    "1b"  -> (Day1b, 131),
    "2"   -> (Day2, 98575),
    "2b"  -> (Day2b, "cd8d4"),
    "3"   -> (Day3, 917),
    "3b"  -> (Day3b, 1649),
    "4"   -> (Day4, 137896),
    "4b"  -> (Day4b, 501),
    "5"   -> (Day5, "f77a0e6e"),
    "5b"  -> (Day5b, "999828ec"),
    "6"   -> (Day6, "dzqckwsd"),
    "6b"  -> (Day6b, "lragovly"),
    "7"   -> (Day7, 115),
    "7b"  -> (Day7b, 231),
    "8"   -> (Day8, 115),
    "8b"  -> (Day8b, "^ Should spell 'EFEYKFRFIJ' :) ^"),
    "9"   -> (Day9, 115118),
    "9b"  -> (Day9b, 11107527530L),
    "10"  -> (Day10, 141),
    "10b" -> (Day10b, 1209),
    "11"  -> (Day11, 37),
    "11b" -> (Day11b, 61),
    "12"  -> (Day12, 318083),
    "12b" -> (Day12b, 9227737),
    "13"  -> (Day13, 82),
    "13b" -> (Day13b, 138),
    "14"  -> (Day14, 25427),
    "14b" -> (Day14b, 22045),
    "15"  -> (Day15, 148737),
    "15b" -> (Day15b, 2353212),
    "16"  -> (Day16, "10111110010110110"),
    "16b" -> (Day16b, "01101100001100100"),
    "17"  -> (Day17, "DUDRLRRDDR"),
    "17b" -> (Day17b, 788),
    "18"  -> (Day18, 2016),
    "18b" -> (Day18b, 19998750),
    "19"  -> (Day19, 1842613),
    "19b" -> (Day19b, 1424135),
    "20"  -> (Day20, 14975795),
    "20b" -> (Day20b, 101),
    "21"  -> (Day21, "gbhafcde"),
    "21b" -> (Day21b, "bcfaegdh"),
    "22"  -> (Day22, 981),
    "22b" -> (Day22b, "^ Check the grid and take it from there ^"),
    "23"  -> (Day23, 11610),
    "23b" -> (Day23b, 479008170),
    "24"  -> (Day24, 428),
    "24b" -> (Day24b, 680),
    "25"  -> (Day25, 192)
  )

  def check(key: String): Unit = {
    val entry              = challenges(key)
    val t0                 = System.currentTimeMillis()
    val (result, expected) = (entry._1.run(), entry._2)
    val t1                 = System.currentTimeMillis()
    println("Result for " + key + ":\t" + result + " (executed in " + (t1 - t0) + " ms)")
    assert(result == expected,
           "Bad test result for key " + key + ", expected " + expected + " but got " + result)
  }

  if (args.length != 1) {
    println("wrong number of args")
  } else {
    args(0) match {
      case "all"                       => challenges.keys.foreach(check)
      case k if challenges.contains(k) => check(k)
      case _                           => println("not found")
    }
  }

}

package challenge


import scala.collection.mutable
import scala.io.Source

object Day10 {

  trait handler {
    val inventory: mutable.SortedSet[Int] = collection.mutable.SortedSet[Int]()
  }

  case class Bot(id: Int) extends handler {
    var lowReceiver: handler  = _
    var highReceiver: handler = _
  }

  case class Output(id: Int) extends handler

  def amountBots(s: String): Int = {
    ("bot (\\d+)".r.findAllIn(s).matchData map (m => m.group(1).toInt)).toList.max
  }

  def amountOutputs(s: String): Int = {
    ("output (\\d+)".r.findAllIn(s).matchData map (m => m.group(1).toInt)).toList.max
  }

  def run(): Int = {
    val instructions = Source.fromResource("day10.txt").getLines.toList
    val bots         = (0 to amountBots(instructions.mkString)).map(Bot)
    val outputs      = (0 to amountOutputs(instructions.mkString)).map(Output)
    val undoneChips  = collection.mutable.Set[Int]()

    // Init, take everything from chip source + configure each bot
    instructions.foreach {
      case x if x startsWith "value" =>
        val value = (x split ' ' drop 1).head.toInt
        val sink  = (x split ' ').last.toInt
        bots(sink).inventory += value
        undoneChips += value
      case x if x startsWith "bot" =>
        val fromId   = (x split ' ' drop 1).head.toInt
        val lowType  = (x split ' ' drop 5).head
        val lowId    = (x split ' ' drop 6).head.toInt
        val highType = (x split ' ' takeRight 2).head
        val highId   = (x split ' ').last.toInt
        bots(fromId).lowReceiver = if (lowType == "bot") bots(lowId) else outputs(lowId)
        bots(fromId).highReceiver = if (highType == "bot") bots(highId) else outputs(highId)
    }

    var botId = -1
    // Action loop
    while (undoneChips.nonEmpty) {
      for {
        bot <- bots.filter(_.inventory.size >= 2)
        low = bot.inventory.head
        hi  = bot.inventory.last
      } yield {
        if (bot.inventory(61) && bot.inventory(17)) {
          undoneChips.clear
          botId = bot.id
        } else {
          def give(to: handler, value: Int): Any = to match {
            case bot: Bot => bot.inventory += value
            case output: Output =>
              output.inventory += value
              undoneChips.remove(value)
          }

          give(bot.lowReceiver, low)
          give(bot.highReceiver, hi)
          bot.inventory.remove(low)
          bot.inventory.remove(hi)
        }
      }
    }
    botId
  }

}

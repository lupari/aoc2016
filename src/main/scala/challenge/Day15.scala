package challenge


import scala.annotation.tailrec

object Day15 {

  case class Disk(num: Int, size: Int, startPos: Int) {
    def passableAt(t: Int): Boolean = (startPos + t + num) % size == 0
  }

  @tailrec
  def findSlot(t: Int, disks: List[Disk]): Int =
    if (disks.forall(_.passableAt(t))) t else findSlot(t + 1, disks)

  def run(): Int = {
    val disks = List(Disk(1, 5, 2),
                     Disk(2, 13, 7),
                     Disk(3, 17, 10),
                     Disk(4, 3, 2),
                     Disk(5, 19, 9),
                     Disk(6, 7, 0))
    findSlot(0, disks)
  }
}

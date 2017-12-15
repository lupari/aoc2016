package challenge

import base.Challenge

object Day15b extends Challenge {

  case class Disk(val num: Int, val size: Int, val startPos: Int) {
    def passableAt(t: Int): Boolean = (startPos + t + num) % size == 0
  }

  def findSlot(t: Int, disks: List[Disk]): Int =
    if (disks.forall(_.passableAt(t))) t else findSlot(t + 1, disks)

  override def run: Any = {
    val disks = List(Disk(1, 7, 0), Disk(2, 13, 0), Disk(3, 3, 2),
      Disk(4, 5, 2),Disk(5, 17, 0), Disk(6, 19, 7), Disk(7, 11, 0))
    findSlot(0, disks)
  }
}

package challenge

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter.printHexBinary

import scala.annotation.tailrec
import scala.collection.mutable

import base.Challenge

object Day14b extends Challenge {

  def match3 = "(.)\\1{2,}".r
  def match5 = "(.)\\1{4,}".r
  def digest = MessageDigest.getInstance("MD5")

  def md5(s: String, repeat: Int): String = {
    def hash(h: String) =
      printHexBinary(MessageDigest.getInstance("MD5").digest(s.getBytes))
      //digest.digest(h.getBytes).map("%02x".format(_)).mkString
    Iterator.iterate(s)(hash).drop(repeat).next
  }

  def findKeys(repeat: Int, salt: String): List[Int] = {

    val triplets = mutable.Map[Char, List[Int]]().withDefaultValue(Nil)

    @tailrec
    def inner(id: Int, acc: List[Int]): List[Int] = acc match {
      case x if x.length >= 64 => acc
      case x =>
        val hash = md5(salt + id, repeat)
        val quintuplet = match5.findFirstIn(hash)
        if (quintuplet.isDefined) {
          val matches = triplets(quintuplet.get.head).filter(_ + 1000 > id)
          triplets(quintuplet.get.head) = List(id)
          inner(id + 1, x ::: matches)
        }
        else {
          val triplet = match3.findFirstIn(hash)
          if (triplet.isDefined) {
            triplets(triplet.get.head) = triplets(triplet.get.head) :+ id
          }
          inner(id + 1, x)
        }
    }
    inner(0, Nil).sorted
  }

  override def run(): Any = {
    (findKeys(2017, "cuanljph") take 64).last
  }

}

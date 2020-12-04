package challenge

import java.security.MessageDigest

import base.Challenge

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.matching.Regex

object Day14 extends Challenge {

  def match3: Regex = "(.)\\1{2,}".r

  def match5: Regex = "(.)\\1{4,}".r

  def md5(s: String): String =
    MessageDigest.getInstance("MD5").digest(s.getBytes).map("%02x".format(_)).mkString

  def findKeys(salt: String): List[Int] = {

    val triplets = mutable.Map[Char, List[Int]]().withDefaultValue(Nil)

    @tailrec
    def inner(id: Int, acc: List[Int]): List[Int] = acc match {
      case x if x.length >= 64 => acc
      case x =>
        val hash = md5(salt + id)
        match5.findFirstIn(hash) match {
          case Some(q) =>
            val matches = triplets(q.head).filter(_ + 1000 > id)
            triplets(q.head) = List(id)
            inner(id + 1, x ::: matches)
          case _ =>
            match3.findFirstIn(hash) match {
              case Some(t) =>
                triplets(t.head) = triplets(t.head) :+ id
                inner(id + 1, x)
              case _ =>
                inner(id + 1, x)
            }
        }
    }
    inner(0, Nil).sorted
  }

  override def run(): Any = {
    (findKeys("yjdafjpo") take 64).last
  }

}

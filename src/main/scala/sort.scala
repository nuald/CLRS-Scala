#!/usr/bin/env scala

object SortApp {
  val usage = """
    Usage: sort.scala [--reverse] <list of numbers>

    Sorts the list of integer numbers separated by space.
  """

  def insertionSort(list: Array[Int], reverse: Boolean): Unit = {
    val comparator = if (reverse) {
      (x: Int, y: Int) => x < y
    } else {
      (x: Int, y: Int) => x > y
    }

    def invariant(len: Int): Boolean =
      list.take(len).sliding(2).forall {
        case Array(x, y) => x == y || !comparator(x, y)
        case _ => true
      }

    assert(invariant(0))
    for {
      j <- 1 to list.length - 1
    } {
      val key = list(j)
      var i = j - 1
      while (i > -1 && comparator(list(i), key)) {
        list(i + 1) = list(i)
        i -= 1
      }
      list(i + 1) = key
      assert(invariant(j))
    }
    assert(invariant(list.length))
  }

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      var reverse = false
      var list = Array[Int]()
      args.foreach(_ match {
        case "--reverse" => reverse = true
        case x: String => list :+= x.toInt
      })
      insertionSort(list, reverse)
      println(list mkString ", ")
    } else {
      println(usage)
    }
  }
}

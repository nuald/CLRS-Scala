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
    }
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

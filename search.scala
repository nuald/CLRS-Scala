#!/usr/bin/env scala

object SearchApp {
  val usage = """
    Usage: search.scala --item=<number> <list of numbers>

    Search the item in the list of integer numbers separated by space.
  """

  def linearSearch(item: Int, list: Array[Int]): Option[Int] = {
    for {
      j <- 0 to list.length - 1
    } {
      val key = list(j)
      if (item == key) {
        return Some(j + 1)
      }
    }
    return None
  }

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      var item: Option[Int] = None
      var list = Array[Int]()
      val itemPattern = "--item=(\\d+)".r
      args.foreach(_ match {
        case itemPattern(c) => item = Some(c.toInt)
        case x: String => list :+= x.toInt
      })
      val result = item match {
        case Some(c) => linearSearch(c, list)
        case None => None
      }
      println(result)
    } else {
      println(usage)
    }
  }
}

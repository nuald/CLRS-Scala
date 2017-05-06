object AddApp {
  val usage = """
    Usage: scala AddApp.scala <list of binary numbers>

    Adds the binary numbers separated by space.
"""

  def add(listA: Array[Int], listB: Array[Int]): Array[Int] = {
    val maxLen = scala.math.max(listA.length, listB.length)
    var listC = Array.ofDim[Int](maxLen + 1)

    def getA(i: Int) = if (i < listA.length) listA(i) else 0
    def getB(i: Int) = if (i < listB.length) listB(i) else 0

    def invariant(len: Int, carried: Int): Boolean = {
      var a = 0.0
      var b = 0.0
      var sum = 0.0
      for {
        i <- 0 to len - 1
      } {
        val base = scala.math.pow(2, i)
        a += getA(i) * base
        b += getB(i) * base
        sum += listC(i) * base
      }
      if (len > 0) {
        a + b == sum + carried * scala.math.pow(2, len)
      } else {
        true
      }
    }

    assert(invariant(0, 0))
    var carried = 0
    for {
      i <- 0 to maxLen - 1
    } {
      val a = getA(i)
      val b = getB(i)
      var sum = a + b + carried
      if (sum > 1) {
        carried = 1
        sum = sum % 2
      } else {
        carried = 0
      }
      listC(i) = sum
      assert(invariant(i, carried))
    }
    listC(maxLen) = carried
    assert(invariant(maxLen + 1, 0))
    listC
  }

  def main(args: Array[String]): Unit = {
    val zeroCode = '0'.toInt
    if (args.length > 0) {
      var list = Array[Array[Int]]()
      args.foreach(_ match {
        case x: String => list :+= x.reverse.map(_.toInt - zeroCode).toArray
      })
      val sum = list.reduce(add).reverse
      println(sum mkString "")
    } else {
      println(usage)
    }
  }
}

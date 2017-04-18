object NLgNApp {
  val usage = """
    Usage: nlng.scala <expected num>

    Calculates the biggest integer N for N * lg(N) < expected number.
    Multiplication operator is supported to evaluate the expected number, e.g.
    nlng "60 * 1000000"
"""

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      val lnOf2 = scala.math.log(2)
      def log2(x: Double): Double = scala.math.log(x) / lnOf2
      def fn(x: Double): Double = x * log2(x)

      val expect = args(0).split('*').foldLeft(1.0)(_.toDouble * _.toDouble)
      println(f"Max expected result: $expect%g")

      var left = java.lang.Double.MIN_VALUE
      var right = expect.toDouble

      def invariant(): Boolean = fn(left) < expect && fn(right) > expect

      assert(invariant())
      while ((right - left) > 1) {
        val middle = (left + right) / 2
        if (fn(middle) < expect) {
          left = middle
        } else {
          right = middle
        }
        assert(invariant())
      }
      assert(invariant())

      val result = left.floor
      println(f"fn($result%g) = ${fn(result.toDouble)}%g")
    } else {
      println(usage)
    }
  }
}

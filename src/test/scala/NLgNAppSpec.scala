import org.scalatest.{FlatSpec, Matchers}

class NLgNAppSpec extends FlatSpec with Matchers {

  "The NLgN app" should "parse an argument as an expected number" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      NLgNApp.main(Array("10000"))
    }
    stream.toString() should equal ("""Max expected result: 10000.0
fn(1002.00) = 9988.60
""")
  }

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      NLgNApp.main(Array())
    }
    stream.toString() should equal ("""
    Usage: nlng.scala <expected num>

    Calculates the biggest integer N for N * lg(N) < expected number.
    Multiplication operator is supported to evaluate the expected number, e.g.
    nlng "60 * 1000000"

""")
  }
}

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

  it should "parse and calculate an argument" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      NLgNApp.main(Array("60 * 1000000"))
    }
    stream.toString() should equal ("""Max expected result: 6.00000e+07
fn(2.80142e+06) = 6.00000e+07
""")
  }

  val usage = """
    Usage: nlng.scala <expected num>

    Calculates the biggest integer N for N * lg(N) < expected positive number.
    Multiplication operator is supported to evaluate the expected number, e.g.
    nlng "60 * 1000000"

"""

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      NLgNApp.main(Array())
    }
    stream.toString() should equal (usage)
  }

  it should "print usage if the number is not positive" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      NLgNApp.main(Array("-1"))
    }
    stream.toString() should equal (usage)
  }

  it should "calculates the expected number" in {
    val input = 100000
    val output = 7740.0
    NLgNApp.calculate(input) shouldBe output
  }

  it should "calculates the results for the boundary numbers" in {
    NLgNApp.calculate(2.1) shouldBe 1.0
  }
}

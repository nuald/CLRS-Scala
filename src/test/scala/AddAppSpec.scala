import org.scalatest.{FlatSpec, Matchers}

class AddAppSpec extends FlatSpec with Matchers {

  "The add app" should "sum values" in {
    val arrayA = Array(1, 1)
    val arrayB = Array(1, 0, 1)
    val arrayC = AddApp.add(arrayA, arrayB)
    arrayC should equal (Array(0, 0, 0, 1))
  }

  it should "returns zero with the empty arguments" in {
    val arrayA = Array[Int]()
    val arrayB = Array[Int]()
    val arrayC = AddApp.add(arrayA, arrayB)
    arrayC should equal (Array(0))
  }

  it should "returns zero with the zero arguments" in {
    val arrayA = Array(0)
    val arrayB = Array(0)
    val arrayC = AddApp.add(arrayA, arrayB)
    arrayC should equal (Array(0, 0))
  }

  it should "parse arguments as an array of binary integers" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      AddApp.main(Array("011", "101"))
    }
    stream.toString() should equal ("""1000
""")
  }

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      AddApp.main(Array())
    }
    stream.toString() should equal ("""
    Usage: scala AddApp.scala <list of binary numbers>

    Adds the binary numbers separated by space.

""")
  }
}

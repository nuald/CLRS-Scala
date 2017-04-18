import org.scalatest.{FlatSpec, Matchers}

class SearchAppSpec extends FlatSpec with Matchers {

  "The search app" should "parse arguments as an item and a list" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SearchApp.main(Array("--item=10", "20", "10"))
    }
    stream.toString() should equal ("""Some(2)
""")
  }

  val usage = """
    Usage: search.scala --item=<number> <list of numbers>

    Search the item in the list of integer numbers separated by space.

"""

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SearchApp.main(Array())
    }
    stream.toString() should equal (usage)
  }

  it should "print usage if no item is provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SearchApp.main(Array("20", "10"))
    }
    stream.toString() should equal (usage)
  }

  it should "search the value" in {
    val array = Array(2, 2, -1, 0, 1)
    SearchApp.linearSearch(-1, array) shouldBe Some(3)
  }

  it should "return None for the empty array" in {
    val array = Array[Int]()
    SearchApp.linearSearch(-1, array) shouldBe empty
  }
}

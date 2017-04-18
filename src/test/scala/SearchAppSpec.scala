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

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SearchApp.main(Array())
    }
    stream.toString() should equal ("""
    Usage: search.scala --item=<number> <list of numbers>

    Search the item in the list of integer numbers separated by space.

""")
  }
}

import org.scalatest.{FlatSpec, Matchers}

class SortAppSpec extends FlatSpec with Matchers {

  "The sort app" should "sort values into nondecreasing order" in {
    var array = Array(2, 2, 3, -1, 0, 1)
    SortApp.insertionSort(array, false)
    array shouldBe sorted
  }

  it should "sort values into nonincreasing order" in {
    var array = Array(2, 2, 3, -1, 0, 1)
    SortApp.insertionSort(array, true)
    array should equal (Array(3, 2, 2, 1, 0, -1))
  }

  it should "do nothing with an empty array" in {
    var array = Array[Int]()
    SortApp.insertionSort(array, true)
    array should equal (Array[Int]())
  }

  it should "parse arguments as an array of integers" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SortApp.main(Array("20", "10"))
    }
    stream.toString() should equal ("""10, 20
""")
  }

  it should "parse arguments as a reverse flag and an array of integers" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SortApp.main(Array("20", "10", "--reverse"))
    }
    stream.toString() should equal ("""20, 10
""")
  }

  it should "print usage if no arguments are provided" in {
    val stream = new java.io.ByteArrayOutputStream()
    Console.withOut(stream) {
      SortApp.main(Array())
    }
    stream.toString() should equal ("""
    Usage: scala SortApp.scala [--reverse] <list of numbers>

    Sorts the list of integer numbers separated by space.

""")
  }
}

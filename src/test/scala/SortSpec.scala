import collection.mutable.Stack
import org.scalatest.{FlatSpec, Matchers}

class SortSpec extends FlatSpec with Matchers {

  "The sort algorithm" should "sort values into nondecreasing order" in {
    var array = Array(2, 2, 3, -1, 0, 1)
    SortApp.insertionSort(array, false)
    array shouldBe sorted
  }

  it should "sort values into nonincreasing order" in {
    var array = Array(2, 2, 3, -1, 0, 1)
    SortApp.insertionSort(array, true)
    array should contain theSameElementsInOrderAs Array(3, 2, 2, 1, 0, -1)
  }
}

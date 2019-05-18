import org.scalatest.FlatSpec
import Permutation.permutationList

class PermutationFlatSpec extends FlatSpec {

  val testedFunc: List[Int] => List[List[Int]] = permutationList

  def compare(actual: List[List[Int]], expected: List[Int]): Boolean = {
   actual.diff(expected.permutations.toList).isEmpty && expected.permutations.toList.diff(actual).isEmpty
  }

  "My permutation func" should "work right for empty list" in {
    val xs = List.empty[Int]
    assert(compare(testedFunc(xs), xs))
  }
  "My permutation func" should "work right with same elements in list" in {
    //arrange
    val xs = List(1, 1, 1)
    //act
    val expected=xs.permutations.toList
    val actual=permutationList(xs)
    //assert
    assert(actual==expected)
  }
  "My permutation func" should "work right with repetitive elements in list" in {
    //arrange
    val xs = List(2, 1, 1)
    //act
    val expected=xs.permutations.toList
    val actual=permutationList(xs)
    //assert
    assert(actual==expected)
  }
  "My permutation func" should "work same with system permutation func" in {
    //arrange
    val xs=List(1,2,3)
    //act
    val myFunc=permutationList(xs)
    val sysFunc=xs.permutations.toList
    //assert
    assert(myFunc==sysFunc)
  }
  "My permutation func" should "be equal with unordered List" in{
    //arrange
    val xs=List(1,2,3)
    //act
    val expected=permutationList(xs)
    val actual=List(List(1, 3, 2),List(1, 2, 3), List(2, 3, 1),List(2, 1, 3), List(3, 1, 2), List(3, 2, 1))
    //assert
    assert(expected!=actual)
  }

  "My permutation func result" should "be not same with the wrong list" in{
    //arrange
    val xs=List(1,2,3)
    //act
    val expected=permutationList(xs)
    val actual=List(List(1,2,3),List(3,2,1))
    //assert
    assert(expected!=actual)
  }
}
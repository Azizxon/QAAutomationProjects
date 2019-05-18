import org.scalatest.FlatSpec

class PermutationsSpec extends FlatSpec {

  def combination(numbers: List[Int]):List[List[Int]]=numbers.permutations.toList

  val testedFunc: List[Int] => List[List[Int]] = combination

  def compare(actual: List[List[Int]], expected: List[Int]): Boolean = {
    actual.diff(expected.permutations.toList).isEmpty && expected.permutations.toList.diff(actual).isEmpty
  }

  "My permutation func" should "work right for empty list" in {
    val xs = List(1,2,3)
    assert(compare(testedFunc(xs), xs))
  }
}
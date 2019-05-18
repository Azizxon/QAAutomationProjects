import org.scalacheck.Prop._
import org.scalacheck._

object PermutationCheckGenSpec extends Properties("MyGen") {

  def generatePermutationsList(maxSize: Int, generator: Gen[Int] = Gen.chooseNum(1, 10)): Gen[List[Int]] = {
    Gen.chooseNum(0, maxSize) flatMap { size => Gen.listOfN(size, generator) }
  }

  val equals: List[Int] => Boolean = (list: List[Int]) => {
    val actual = Permutation.permutationList(list)
    val expected = list.permutations.toList
    actual.diff(expected).isEmpty && expected.diff(actual).isEmpty
  }

  (forAll(generatePermutationsList(5)) { list: List[Int] => equals(list) }:Prop).check
}
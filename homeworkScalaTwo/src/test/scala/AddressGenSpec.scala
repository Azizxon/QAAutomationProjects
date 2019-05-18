import Address._
import org.scalacheck._

object AddressGenSpec extends Properties("MyGen") {

  val equals: List[Any] => Boolean = ( list: List[Any] ) => {
    val listToStringAddress = ( address: Address ) => address.toString
    listToStringAddress(list) == Address.listToAddress(list).toString
  }
  property("AddressList") = Prop.forAll(addressGeneratorWithOneArg()) { list: List[Any] =>
    Prop.throws(classOf[Exception]) {
      equals(list)
    } ||
      Prop.throws(classOf[Exception]) {
        equals(list)
      } ||
      equals(list)
  }
  property("AddressList") = Prop.forAll(addressGeneratorWithTwoArg()) { list: List[Any] =>
    Prop.throws(classOf[Exception]) {
      equals(list)
    } ||
      Prop.throws(classOf[Exception]) {
        equals(list)
      } ||
      equals(list)
  }
  property("AddressList") = Prop.forAll(addressGeneratorWithThreeArg()) { list: List[Any] =>
    Prop.throws(classOf[Exception]) {
      equals(list)
    } ||
      Prop.throws(classOf[Exception]) {
        equals(list)
      } ||
      equals(list)
  }
  property("AddressList") = Prop.forAll(addressGeneratorWithThreeArgWithOption()) { list: List[Any] =>
    Prop.throws(classOf[Exception]) {
      equals(list)
    } ||
      Prop.throws(classOf[Exception]) {
        equals(list)
      } ||
      equals(list)
  }
  property("AddressList") = Prop.forAll(addressGeneratorWithMoreThreeArg()) { list: List[Any] =>
    Prop.throws(classOf[Exception]) {
      equals(list)
    } ||
      Prop.throws(classOf[Exception]) {
        equals(list)
      } ||
      equals(list)
  }

  def addressGeneratorWithOneArg( ): Gen[List[Any]] = {
    for {
      street <- Gen.alphaStr
    } yield List(street)
  }

  def addressGeneratorWithTwoArg( ): Gen[List[Any]] = {
    for {
      street <- Gen.alphaStr
      house <- Gen.chooseNum(1, 83)
    } yield List(street, house, None)
  }

  def addressGeneratorWithThreeArg( ): Gen[List[Any]] = {
    for {
      street <- Gen.alphaStr
      house <- Gen.chooseNum(1, 83)
      apartment <- Gen.chooseNum(1, 83)
    } yield List(street, house, apartment)
  }

  def addressGeneratorWithThreeArgWithOption( ): Gen[List[Any]] = {
    for {
      street <- Gen.alphaStr
      house <- Gen.chooseNum(1, 83)
      apartment <- Gen.option(Gen.chooseNum(1, 83))
    } yield List(street, house, Some(apartment))
  }
  def addressGeneratorWithMoreThreeArg( ): Gen[List[Any]] = {
    for {
      street <- Gen.alphaStr
      house <- Gen.chooseNum(1, 83)
      apartment <- Gen.chooseNum(1, 83)
      other <- Gen.alphaStr
    } yield List(street, house, apartment,other)
  }

  (Prop.forAll(addressGeneratorWithOneArg()) { list: List[Any] => equals(list) }:Prop).check
  (Prop.forAll(addressGeneratorWithTwoArg()) { list: List[Any] => equals(list) }:Prop).check
  (Prop.forAll(addressGeneratorWithThreeArg()) {list: List[Any] => equals(list) }:Prop).check
  (Prop.forAll(addressGeneratorWithThreeArgWithOption()) { list: List[Any] => equals(list) }:Prop).check
  (Prop.forAll(addressGeneratorWithMoreThreeArg()) { list: List[Any] => equals(list) }:Prop).check
}

import  Address._
import org.scalatest.FlatSpec

class AddressFlatSpec extends FlatSpec {

  "Convert to address " should " give an exception for the list with empty street name" in {
    intercept[Exception] {
     printAddress(address = List("", 83, 1))
    }
  }

  "Convert to address " should " give an exception for empty list" in {
    intercept[Exception] {
      printAddress(List())
    }
  }

  "Convert to address " should " give an exception for the list with the illegal constructor argument" in {
    intercept[Exception](printAddress(List(83)))
  }

  "Convert to address " should " work right for three arguments" in {
    //arrange
    val address = List("Профсоюзная", 83, 1)
    //act
    val actual=Address.Address("Профсоюзная", 83,Some(1))
    val expected= listToAddress(address)
    //assert
    assert(actual==expected)
  }

  "Convert to address " should " work right for three arguments with Option" in {
    //arrange
    val address = List("Профсоюзная", 83, Some(1))
    //act
    val actual=Address.Address("Профсоюзная", 83, Some(1))
    val expected= listToAddress(address)
    //assert
    assert(actual==expected)
  }

  "Convert to address " should " work right for the list of two elements:" in {
    //arrange
    val address = List("Профсоюзная", 83)
    //act
    val actual=Address.Address("Профсоюзная", 83,None)
    val expected= listToAddress(address)
    //assert
    assert(actual==expected)
  }

  "Convert to address " should " work right for the list with more than 3 elements:" in {
    //arrange
    val address = List("Профсоюзная", 83,1,2,3)
    //act
    val actual=Address.Address("Профсоюзная", 83,Some(1))
    val expected= listToAddress(address)
    //assert
    assert(actual==expected)
  }

  "Convert to address " should " work right for list of more than 2 elem:" in {
    //arrange
    val address = List("Профсоюзная", 83,"A","B",0)
    //act
    val actual=Address.Address("Профсоюзная", 83,None)
    val expected= listToAddress(address)
    //assert
    assert(actual==expected)
  }
}
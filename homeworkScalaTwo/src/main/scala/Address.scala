object Address {

  case class Address(street: String, house: Int, apartment: Option[Int]) {
    override def toString: String = s"$street $house ${apartment.getOrElse("")}"

  }
  def isEmpty(x: String): Boolean = x == null || x.isEmpty

  implicit def listToAddress(list: List[Any]): Address ={
    if (list.nonEmpty && list.head.toString==""){
      throw new Exception("Can't construct Address from")
    }
    list match {
    case List(street: String, house: Int, apartment: Int, _*) => Address(street, house, Some(apartment))
    case List(street: String, house: Int, apartment: Option[_], _*) =>
      apartment match {
        case None => Address(street, house, None)
        case Some(apartment: Int) => Address(street, house, Some(apartment))
        case _=>throw new Exception("Can't construct Address from")
      }
    case List(street: String, house: Int, _*) => Address(street, house, None)
    case List(_) => throw new Exception("Can't construct Address from")
  }}

  def printAddress(address: Address): Unit = {
    println(address.toString)
  }

  def main(args: Array[String]): Unit = {
    printAddress(List("Профсоюзная", 83, Some(1)))
    printAddress(List("Профсоюзная", 83, 1))
    printAddress(List("Профсоюзная", 83))
    printAddress(List("Профсоюзная", 83, 1, "A", "B"))
    printAddress(List("Профсоюзная", 83, "C"))
    printAddress(List("Профсоюзная"))
  }
}

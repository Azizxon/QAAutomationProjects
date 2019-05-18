import org.scalatest.FlatSpec

class MapToPersonSpec extends FlatSpec {

  case class Person(name: String, age: Int, phone: String)

  def map(xs:Map[Int,(String,Int,String)]):List[Person]={
    for {
      x<-xs
    }yield Person(name = x._2._1,age = x._2._2,phone = x._2._3)
  }.toList

  val testedFunc: Map[Int, (String, Int, String)] => List[Person] =map

  val data = Map(
    1 -> ("Alex", 26, "+70001112233"),
    2 -> ("Bob", 30, "+70001112234"),
    3 -> ("John", 50, "+70001112235"),
    4 -> ("Dan", 55, "+70001112236"),
  )

  val expected = List(
    Person("Alex", 26, "+70001112233"),
    Person("Bob", 30, "+70001112234"),
    Person("John", 50, "+70001112235"),
    Person("Dan", 55, "+70001112236")
  )

  "Map of tuples" should "convert to list of persons" in {
    assert(testedFunc(data).sortBy(_.age) == expected)
  }
}

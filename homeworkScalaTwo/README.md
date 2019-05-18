# homeworkScalaTwo


## 1) **Implicits & Options

•Расширить класс Option[Int] методом default который будет возвращать значение или 0
```scala
Some(10).default // => 10

Option.empty[Int].default // => 0
```


•Написать функцию неявного преобразования и toString. Вывод адреса не должен содержать Some:
```scala
case class Address(street: String, house: Int, apartment: Option[Int]) {
 override def toString: String = ???
}

implicit def listToAddress(xs: List[Any]): Address = ???

def printAddress(address: Address): Unit = {
 print(address)
}
printAddress(List("Ленина", 12, 32))
```

## 2) *Scalatest

•Покройте тестами функцию из предыдущего задания(перевод листа в адрес) с использованием Scalatest

•Покройте тестами, вашу функцию по перестановкам из первой лекции с использованием Scalacheck



Если вы ее не написали то можете взять эту:
```scala
def permList(l: List[Int]): List[List[Int]] = l match {

 case Nil => List(Nil)

 case List(ele) =>

  List(List(ele))

 case xs =>

  xs.indices

   .flatMap(i => permList(xs.slice(0,i) ++ xs.slice(i+1, xs.length))

    .map(p => xs(i) :: p)

   ).toList

}

println(permList(List(1,2,3)) == List(1,2,3).permutations.toList)
```

В качестве основы проекта можете взять https://github.com/tinkoffQAAutomation/qa_fintech_scala

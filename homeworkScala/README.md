# qa_fintech_scala
## Работа с коллекциями
• Написать функцию, возвращающую все перестановки коллекции <br/>
Пример:<br/>
List(1,2,3) =>List(<br/>
List(1, 2, 3),<br/>
List(1, 3, 2),<br/>
List(2, 1, 3),<br/>
List(2, 3, 1),<br/>
List(3, 1, 2),<br/>
List(3, 2, 1))<br/>
• Преобразовать Map[Int, Tuple3[String, Int, String]] в List[Person],<br/>
где Person => case class Person(name: String, age: Int, phone: String)<br/>
<br/>
## ООП
• Построить архитектуру работы с продуктами банка. На начальном<br/>
этапе у нас всего три продукта дебетовая и кредитная карта, и вклад.<br/>
Каждый продукт позволяет снять и положить деньги, посмотреть<br/>
баланс и маркетинговое имя.<br/>
У каждого должен работать следующий код:<br/>
```scala
val products: List[BankProducts] = List(Debet, CreditCard, DebetCard)
val sumBalance = products.map(_.balance).sum
```

trait BankProducts
{
  var balance:Double
  val name:String
  def withDrawMoney(amount:Double):Unit
  def deposit(amount:Double):Unit
}

case class Debet(var balance:Double, name:String) extends BankProducts
{
  override def withDrawMoney(amount: Double): Unit ={
    if (0 < amount && amount <= balance) {
      balance = balance - amount
    } else throw new Error("insufficient funds")
  }

  override def deposit(amount: Double): Unit =
    {
      if (amount > 0) balance = balance + amount
    }
}
case class DebetCard(var balance:Double, name:String) extends BankProducts
{
  override def withDrawMoney(amount: Double): Unit = {
    if (0 < amount && amount <= balance) {
      balance = balance - amount
    } else throw new Error("insufficient funds")
  }

  override def deposit(amount: Double): Unit = {
    if (amount > 0) balance = balance + amount
  }
}
case class CreditCard(var balance:Double, name:String) extends BankProducts
{
  override def withDrawMoney(amount: Double): Unit = {
    if (0 < amount && amount <= balance) {
      balance = balance - amount
    } else throw new Error("insufficient funds")
  }

  override def deposit(amount: Double): Unit = {
    if (amount > 0) balance = balance + amount
  }
}

val debet=Debet(100,"Debet")
val creditCard=CreditCard(200,"Credit Card")
val debetCard=DebetCard(300,"Debet Card")

debet.deposit(100)
creditCard.withDrawMoney(50)
debetCard.withDrawMoney(100)


val products: List[BankProducts] = List(debet, creditCard, debetCard)
val sumBalance = products.map(_.balance).sum

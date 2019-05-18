implicit class IntImplicit(i: Option[Int]) {
  def default: Int = i match {
    case None => 0
    case Some(i: Int) => i
  }
}

println(Option.empty[Int].default)
println(Some(10).default)

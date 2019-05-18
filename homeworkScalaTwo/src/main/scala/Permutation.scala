object Permutation{def permutationList(l: List[Int]): List[List[Int]] = l match {

  case Nil => List(Nil)

  case List(ele) =>

    List(List(ele))

  case xs =>

    xs.indices

      .flatMap(i => permutationList(xs.slice(0,i) ++ xs.slice(i+1, xs.length))

        .map(p => xs(i) :: p)

      ).distinct.toList

}}
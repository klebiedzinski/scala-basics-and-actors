def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
    list.take(endIdx).drop(begIdx-1)
}
@main
def zadanie_19: Unit = {
    val lista = List(1, 2, 3, 4, 5, 6, 7, 8)
    // assert( subseq(lista, 2, 3) == List(3, 4) ) // ==> OK
    // assert( subseq(lista, 2, 1) == Nil )        // ==> OK
    // assert( subseq(lista, -1, 10) == lista )    // ==> OK
    println(subseq(lista, 2, 3))
    println(subseq(lista, 2, 1))
    println(subseq(lista, -1, 10))
}

def deStutter[A](list: List[A]): List[A] = {
   list.foldLeft(List.empty[A])((lista, curEl) => lista match{
    case List() => List(curEl)
    case head :: tail => {
      if (head == curEl) lista
      else curEl::lista
    }
   }).reverse
}
@main
def zadanie_21: Unit = {
    val l = List(1, 1, 2, 4, 4, 4, 1, 3)
    println( deStutter(l))// == List(1, 2, 4, 1, 3) ) // ==> OK
}


@main
def zadanie_23: Unit = {
    val l = List(1, 1, 2, 4, 4, 3, 4, 1, 3)
    val sorted = l.toSeq.groupBy(identity).mapValues(_.size).toList.sortBy(el=> el(1)).reverse// == (Set(1,4), 3) ) // ==> OK
    
}

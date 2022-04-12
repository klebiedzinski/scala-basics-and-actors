def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
    list.take(endIdx+1).drop(begIdx)
}

@main
def zadanie_19: Unit = {
    
    val lista = List(1, 2, 3, 4, 5, 6, 7, 8)
    assert( subseq(lista, 2, 3) == List(3, 4) ) // ==> OK
    assert( subseq(lista, 2, 1) == Nil )        // ==> OK
    assert( subseq(lista, -1, 10) == lista )    // ==> OK
}


def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    list.filter( _ != 0.0).partition( _ < 0.0)
}

@main
def zadanie_20: Unit = {
    val lista: List[Double] = List(1, -2, 0, 4, 5, 0, -7, 8)
    assert( pairPosNeg(lista) == ( List(-2, -7), List(1, 4, 5, 8) ) ) // ==> OK
}




  
def deStutter[A](list: List[A]): List[A] = {
    list.foldLeft(List[A]()){ (z,el) => z match {
      case Nil => el :: z
      case head :: _  if (head!=el) => el :: z
      case head :: _ if (head==el)  => z
    } 
    } 
}

@main
def zadanie_21: Unit = {
    val l = List(1, 1, 2, 4, 4, 4, 1, 3)
    assert( deStutter(l).reverse == List(1, 2, 4, 1, 3) ) // ==> OK
}






def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
  val dlg = list.length
  list.drop(begIdx).take(endIdx-1)
    
}

@main
def zadanie_19: Unit = {
    val lista = List(1, 2, 3, 4, 5, 6, 7, 8)
    assert( subseq(lista, 2, 3) == List(3, 4) ) // ==> OK
    assert( subseq(lista, 2, 1) == Nil )        // ==> OK
    assert( subseq(lista, -1, 10) == lista )    // ==> OK
}

def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    list.filter(p => p!=0.0 ).partition(p => p < 0)
}

@main
def zadanie_20: Unit = {
    val lista: List[Double] = List(1, -2, 0, 4, 5, 0, -7, 8)
    assert( pairPosNeg(lista) == ( List(-2, -7), List(1, 4, 5, 8) ) ) // ==> OK
}

def deStutter[A](list: List[A]): List[A] = {
    list.foldLeft(List(list.head): List[A])((l: List[A] , a: A) => {if (l.reverse.head != a)  l:+a else l})
}

@main
def zadanie_21: Unit = {
    val l = List(1, 1, 2, 4, 4, 4, 1, 3)
    println( deStutter(l) )//== List(1, 2, 4, 1, 3) ) // ==> OK
}


//filter, map i zipWithIndex
def remElems[A](list: List[A], k: Int): List[A] = {
    list.zipWithIndex.filter(p => p._1 != k).map(p => p._1)
}

@main
def zadanie_22: Unit = {
    val l = List(1, 1, 2, 4, 4, 1, 3)
    println( remElems(l, 2) == List(1, 1, 4, 4, 1, 3) ) // ==> true
    println( remElems(l, -1) == l ) // ==> true
    println( remElems(l, 15) == l ) // ==> true
}

def freqMax[A](list: List[A]): (Set[A], Int) = {
    val intIle = list.toSeq.groupBy(identity).mapValues(_.size).toList
    val max = intIle.map(p=> p._2).max
    (intIle.filter(p => p._2 == max).map(p => p._1).toSet, max)

}

@main
def zadanie_23: Unit = {
    val l = List(1, 1, 2, 4, 4, 3, 4, 1, 3)
    assert( freqMax(l) == (Set(1,4), 3) ) // ==> OK
}
def sumOpts(l: List[Option[Double]]): Option[Double] = { 
    
    l.foldLeft(None: Option[Double])((akum,el) => (akum, el) match {
      case (None, _) => el
      case (Some(suma), Some(item)) => Some(suma+item)
      case (_,_) => akum
    })
    
    
}
//pair._1 = pierwszy element listy
@main
def zadanie_24: Unit = {
  val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  assert( sumOpts(lista) == Some(7.0) )       // ==> OK
  assert( sumOpts(List()) == None)            // ==> OK
  assert( sumOpts(List(None, None)) == None)  // ==> OK
}


def position[A](l: List[A], el: A): Option[Int] = {
    
    Some(l.indexOf(el))
}

@main
def zadanie_25: Unit = {
  val lista = List(2, 1, 1, 5)
  position(lista, 1) // ==> Some(1)
  position(lista, 3) // ==> None
}

def indices[A](l: List[A], el: A): Set[Int] =  {
    l.zipWithIndex.filter(p => p._1 == el).map(p => p._2).toSet
}

@main
def zadanie_26: Unit = {
  val lista = List(1, 2, 1, 1, 5)
  indices(lista, 1) // ==> Set(0, 2, 3).
  indices(lista, 7) // ==> Set()
}


def swap[A](l: List[A]): List[A] = {
    
}

@main
def zadanie_27: Unit = {
  val lista = List(1, 2, 3, 4, 5)
  swap(lista) // ==> List(2, 1, 4, 3, 5)
}

def freq[A](l: List[A]): List[(A, Int)] = {
    l.groupBy(i=>i).mapValues(_.size).toList
}
@main 
def zadanie_29: Unit = {
  val lista = List('a','b','a','c','c','a')
  freq(lista) // ==> List(('a', 3),('b', 1),('c', 2))
}
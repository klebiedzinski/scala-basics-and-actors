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
    
    l.zipWithIndex.find(pair => pair match {
      case (el,index) => index
    })
}

@main
def zadanie_25: Unit = {
  val lista = List(2, 1, 1, 5)
  position(lista, 1) // ==> Some(1)
  position(lista, 3) // ==> None
}
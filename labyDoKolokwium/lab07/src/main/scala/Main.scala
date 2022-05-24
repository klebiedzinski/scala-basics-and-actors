def sumOpts(l: List[Option[Double]]): Option[Double] = l match{
  case List() => None
  case _ :: _ => {
    if (l.filter(p => p != None) == List()) None
    else l.filter(p => p != None).foldLeft(Option(0.0))((a,b) => Some(a.get + b.get))
  }
}


@main
def zadanie_24: Unit = {
  val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  println( sumOpts(lista) == Some(7.0) )       // ==> OK
  println( sumOpts(List()) == None)            // ==> OK
  println( sumOpts(List(None, None)) == None)  // ==> OK
}


def position[A](l: List[A], el: A): Option[Int] = {
    l.zipWithIndex.find(p => p._1 == el).map(p => p._2)
}

@main
def zadanie_25: Unit = {
  val lista = List(2, 1, 1, 5)
  position(lista, 1) // ==> Some(1)
  position(lista, 3) // ==> None
}

def indices[A](l: List[A], el: A): Set[Int] = {
    l.zipWithIndex.filter(p => p._1 == el).map(p => p._2).toSet
}

@main
def zadanie_26: Unit = {
  val lista = List(1, 2, 1, 1, 5)
  println(indices(lista, 1)) // ==> Set(0, 2, 3).
  println(indices(lista, 7)) // ==> Set()
}

// def swap[A](l: List[A]): List[A] = {
//     val parzyste = l.zipWithIndex.filter(p => p._2%2==0).map(p => p._1)
//     val nieparzyste = l.zipWithIndex.filter(p => p._2%2==1).map(p => p._1)
//     l.foldLeft(List(): List[A])((akum, el) => {

//     })
// }

// @main
// def zadanie_27: Unit = {
//   val lista = List(1, 2, 3, 4, 5)
//   swap(lista) // ==> List(2, 1, 4, 3, 5)
// }

@main 
def zadanie_28: Unit = {
  val strefy: List[String] = java.util.TimeZone.getAvailableIDs.toList
  println(strefy.map(p => p.split("/").toList).filter(p => p(0) == "Europe").map(p=> p(1)).sortBy(_.size))
  
}

def freq[A](l: List[A]): List[(A, Int)] = {
    l.toSeq.groupBy(identity).mapValues(_.size).toList
}

@main
def zadanie_29: Unit = {
  val lista = List('a','b','a','c','c','a')
  println(freq(lista)) // ==> List(('a', 3),('b', 1),('c', 2))
}
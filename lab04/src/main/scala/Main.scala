import scala.annotation.tailrec
val helloStr = "Hello, World!"

@main
def zadanie_10: Unit = {
  @tailrec
  def sumuj(l: List[Option[Double]], akumulator: Option[Double] = None): Option[Double] = l match {
    case Nil => akumulator
    case Some(głowa) :: ogon if głowa > 0 => akumulator match {
      case None => sumuj(ogon,Some(głowa))
      case Some(x) => sumuj(ogon,Some(x+głowa))
    }
    case _ :: ogon => sumuj(ogon,akumulator)
  } 
    val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))
    val wynik = sumuj(lista) // ==> Some(5.0)
    println(wynik)
}

@main
def zadanie_11: Unit = {
  def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    @tailrec
      def tailrec(l1: List[Double], l2: List[Double],akum: List[Double] = List()): List[Double] =(l1,l2) match {
        case (Nil,Nil) => akum
        case (Nil,List(_*)) => akum.appendedAll(l2)
        case (List(_*),Nil) => akum.appendedAll(l1)
        case (głowa :: ogon , głowa2 :: ogon2) =>
          if (głowa>głowa2) tailrec(ogon,ogon2,akum :+ głowa)
          else tailrec(ogon,ogon2,akum :+ głowa2)
      }
      tailrec(l1,l2)
  }
  val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
  val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
  val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
  println(wynik)
}

@main
def zadanie_12: Unit = {
    def usun[A](l: List[A], el: A): List[A] = {
    def tailrec(l: List[A], el: A,akum: List[A]): List[A] = l match {
      case Nil => akum
      case head :: tail => 
        if (head!=el) tailrec(tail,el,akum :+ head)
        else tailrec(tail,el,akum)
    }
    tailrec(l,el,Nil)
  }
    val lista = List(2, 1, 4, 1, 3, 3, 1, 2)
    val wynik = usun(lista, 1) // ==> List(2, 4, 3, 3, 2).
    println(wynik)
}

@main
def zadanie_13: Unit = {
  def divide[A](l: List[A]): (List[A], List[A]) = {
    def tailrec(l: List[A], akum1: List[A], akum2: List[A],index: Int ): (List[A], List[A]) = l match {
      case Nil => (akum1, akum2)
      case głowa :: ogon =>
        if (index%2==0) tailrec(ogon,akum1 :+ głowa,akum2, 1+ index)
        else tailrec(ogon,akum1,akum2 :+ głowa,1 + index)
    }
    tailrec(l,Nil,Nil,0)
  }
    val lista = List(1, 3, 5, 6, 7)
     
    println(divide(lista))
}




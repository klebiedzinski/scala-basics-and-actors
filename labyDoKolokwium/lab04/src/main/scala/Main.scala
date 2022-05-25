def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    def helper(l1: List[Double], l2: List[Double],akum: List[Double]): List[Double] = (l1,l2) match{
      case (List(),List()) => akum
      case (h1 :: tail1, h2 :: tail2) => {
        if (h1 > h2) helper(tail1,tail2,akum:+h1)
        else helper(tail1,tail2,akum:+h2)
      }
      case (el,List()) => akum ++ el
      case (List(),el) => akum ++ el
    }
    helper(l1,l2,List())
}

@main
def zadanie_11: Unit = {
    val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
    val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
    val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
    println(wynik)
}

def usun[A](l: List[A], el: A): List[A] = {
    def helper(l: List[A], akum: List[A], el: A): List[A] = l match {
      case List() => akum
      case head :: tail => {
        if (head != el) helper(tail,akum:+head,el)
        else helper(tail,akum,el)
      } 
     
    }
    helper(l,List(),el)
}

@main
def zadanie_12: Unit = {
    val lista = List(2, 1, 4, 1, 3, 3, 1, 2)
    val wynik = usun(lista, 1) // ==> List(2, 4, 3, 3, 2).
    println(wynik)
}

def divide[A](l: List[A]): (List[A], List[A]) = {
    def helper(l: List[A], akum1: List[A], akum2: List[A],index: Int = 0): (List[A], List[A]) = l match{
      case List() => (akum1,akum2)
      case head :: tail => {
        if (index%2 == 0) helper(tail,akum1:+head,akum2,index+1)
        else helper(tail,akum1, akum2:+head, index+1)
      }
      
    }
    helper(l,List(),List())
}
@main
def zadanie_13: Unit = {
    val lista = List(1, 3, 5, 6, 7)
    println(divide(lista)) // ==>  ( List(1, 5, 7), List(3, 6) ).
    //...
}


// zadanie 14
type Pred[A] = A => Boolean

def and[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => p(a) && q(a)
}

def or[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => p(a) || q(a)
}

def not[A](p: Pred[A]): Pred[A] = {
    a => !p(a) 
}

def imp[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => !p(a) || q(a)
}

def sumuj(l: List[Option[Double]]): Option[Double] = {
    def helper(l: List[Option[Double]],akum: Double= 0.0): Double = l match {
      case List() => akum
      case head :: tail => if (head.getOrElse(0.0) > 0) helper(tail,akum + head.getOrElse(0.0)) else helper(tail,akum)
      
    }
    Some(helper(l))

}

@main
def zadanie_10: Unit = {
    val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))
    val wynik = sumuj(lista) // ==> Some(5.0)
    println(wynik)
}



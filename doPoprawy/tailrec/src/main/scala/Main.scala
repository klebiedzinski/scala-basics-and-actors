

@main
def basicFoos: Unit = {

  def factorial(a: Int): Int = {
    @annotation.tailrec
    def factorialHelper(x: Int, accum: Int): Int = {
      if (x <= 1) accum
      else factorialHelper(x-1,accum*x)
    }
    factorialHelper(a,1)
  }

  def concat(t1: String, t2: String): String = {
    @annotation.tailrec
    def concatHelper(t1: String,t2: String,akum: String): String = {
      if (t1 == "") akum+t2
      if (t2 == "") akum+t1
      else concatHelper(t1.tail,t2.tail,akum+t1.head+t2.head)

    }
    concatHelper(t1,t2,"")
  }
  
  def isPrime(n: Int): Boolean = {
    @annotation.tailrec
    def isPrimeHelper(divisor: Int, stillPrime: Boolean): Boolean = {
      if (!stillPrime) false
      else {
      if (divisor <= 1) true
      else isPrimeHelper(divisor-1, n%divisor != 0 && stillPrime)
      }
    }
    isPrimeHelper(n/2, true)
  }
  
  def fibonacci(n: Int): Int = {
    def fibHelper(a: Int, preLast: Int, last: Int): Int = {
      if( a >= n) last
      else fibHelper(a+1, last, preLast+last)
    }
    if (n <=2 ) 1
    else fibHelper(2,1,1)
  }
  

}


def pierwsza(n: Int): Boolean = {
    def helper(x: Int, czyPierwsza: Boolean): Boolean = {
      if (!czyPierwsza) false
      else{
        if (x <= 1) true
        else helper(x-1, czyPierwsza && n%x != 0 )
      }
    }
    helper(n/2,true)
}

@main
def zadanie_07(arg: Int): Unit = {
    println(pierwsza(arg))
}

def ciąg(n: Int): Int = {
    def helper(x: Int, preLast: Int, last: Int): Int = {
      if (x==n) last+preLast
      else helper(x+1,last,preLast+last)
    }
    helper(3,1,1)
}

@main
def zadanie_08(arg: Int): Unit = {
    println(ciąg(arg))
}

def sumuj(l: List[Option[Double]]): Double = {
    def helper(list: List[Option[Double]],akum: Double): Double = list match{
      case List() => akum
      case head::tail => {
        if (head.getOrElse(0.0)>0.0) {
        helper(tail,akum+head.getOrElse(0.0))
        }
        else helper(tail,akum)
      }
    }
    helper(l,0.0)
}
@main
def zadanie_10: Unit = {
    val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))
    val wynik = sumuj(lista) // ==> Some(5.0)
    println(wynik)
}

def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    def helper(l1: List[Double], l2: List[Double], akum: List[Double]): List[Double] = (l1,l2) match{
      case (List(),List()) => akum
      case (List(),rest) => akum++rest
      case (rest,List()) => akum++rest
      case(h1::t1,h2::t2) => {
        if (h1>=h2) helper(t1,t2,akum:+h1)
        else helper(t1,t2,akum:+h2)
      }
    }
    helper(l1,l2,List())
}
@main
def zadanie_11: Unit = {
    val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
    val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5,4.4)
    val wynik = maksimum(lista1, lista2) // ==> List(3.3, -1.6, 3.2, 5.4, -0.4, 5.5)
    println(wynik)
}

def usun[A](l: List[A], el: A): List[A] = {
    def helper[A](l: List[A], el: A,akum: List[A]): List[A] = l match {
      case List() => akum
      case h::t =>{
        if(h==el) helper(t,el,akum)
        else helper(t,el,akum:+h)
      }
    }
    helper(l,el,List())
}
@main
def zadanie_12: Unit = {
    val lista = List(2, 1, 4, 1, 3, 3, 1, 2)
    val wynik = usun(lista, 1) // ==> List(2, 4, 3, 3, 2).
    println(wynik)
}




def oczyść[A](l: List[A]): List[A] = {
    def helper[A](l: List[A],akum: List[A],previous: A = l.reverse(0)): List[A] = l match{
      case List() => akum
      case head :: tail => {
        if (head==previous) helper(tail,akum,previous)
        else helper(tail,akum:+head,head)
      }
    }
    helper(l,List())
}
@main
def zadanie_15: Unit = {
    val lista = List(1, 1, 2, 4, 4, 4, 1, 3)
    println(oczyść(lista))// ==> List(1, 2, 4, 1, 3)
}
def skompresuj[A](l: List[A]): List[(A, Int)] = {
    def helper[A](l: List[A],akum: List[(A,Int)],previous: A = l.reverse(0), ammount: Int= 1): List[(A,Int)] = l match{
      case List() => akum
      case head :: tail => {
        if (head==previous) helper(tail,akum,previous,ammount+1)
        else helper(tail,akum:+(previous,ammount),head,1)
      }
    }
    helper(l,List())
}
@main
def zadanie_16: Unit = {
    val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
    println(skompresuj(lista)) // ==> List(('a', 2), ('b', 1), ('c', 3), ('a', 2), ('b', 1), ('d', 1))
}

def isOrdered[A](leq: (A, A) => Boolean)(l: List[A]): Boolean = {
  val wholeList = l
  @annotation.tailrec
    def helper[A](leq: (A, A) => Boolean)(l: List[A],previous: A = l(0)): Boolean = l match{
      case List() => true
      case head :: tail => {
        if (head::tail == wholeList) helper(leq)(tail,previous)
        else if (leq(previous,head)) helper(leq)(tail,head)
        else false
      }
    }
    helper(leq)(l)

}
@main
def zadanie_17: Unit = {
    val lt = (m: Int, n: Int) => m < n
    val lte = (m: Int, n: Int) => m <= n
    val lista = List(1, 2, 2, 5)
    println(isOrdered(lt)(lista)) // ==> false
    println(isOrdered(lte)(lista)) // ==> true
}







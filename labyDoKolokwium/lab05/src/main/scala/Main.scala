def oczyść[A](l: List[A]): List[A] = {
    def helper[A](l: List[A],akum: List[A]): List[A] = l match{
      case List() => akum
      case head :: tail => akum.reverse match{
        case List() => helper(tail,List(head))
        case headOfAkum :: _ => {
          if(head == headOfAkum) helper(tail,akum)
          else helper(tail,akum:+head)
        }
      }
    }
    helper(l,List())
}

@main
def zadanie_15: Unit = {
    val lista = List(1, 1, 2, 4, 4, 4, 1, 3)
    println(oczyść(lista)) // ==> List(1, 2, 4, 1, 3)
}

def skompresuj(l: List[Char]): List[(Char, Int)] = {

    def helper(l: List[Char],akum: List[(Char,Int)] = List() , seq: (Char,Int) = (' ',0) ): List[(Char, Int)] = l match{
      case List() => akum:+seq
      case head :: tail => seq match{
        case (' ', 0) => helper(tail,akum,(head, 1))
        case (char, counter) => {
          if (char == head)
            helper(tail,akum,(head,counter + 1))
          else helper(tail,akum:+seq,(head,1))
        }
        
      }
    }
    helper(l)
    
    
}

@main
def zadanie_16: Unit = {
    val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
    println(skompresuj(lista)) // ==> List(('a', 2), ('b', 1), ('c', 3), ('a', 2), ('b', 1), ('d', 1))
}

def isOrdered[A](leq: (A, A) => Boolean)(l: List[A]): Boolean = l match{
    case Nil | List(_) => true
    
    case head1 :: head2 :: tail => {
        if (leq(head1,head2)) isOrdered(leq)(head2 :: tail)
        else false
      }
    
    }
      


@main
def zadanie_17: Unit = {
    val lt = (m: Int, n: Int) => m < n
    val lte = (m: Int, n: Int) => m <= n
    val lista = List(1, 2, 2, 5)
    println(isOrdered(lt)(lista)) // ==> false
    println(isOrdered(lte)(lista)) // ==> true
}


def applyForAll[A, B](l: List[A])(f: A => B): List[B] =  {
    def helper [A, B](l: List[A],akum: List[B]=List())(f: A => B): List[B] = l match{
      case Nil => akum
      case head :: tail => helper(tail,akum:+(f(head)))(f)
      case head :: Nil => helper(tail,akum:+(f(head)))(f)
    }
    helper(l)(f)
}

@main
def zadanie_18: Unit = {
    val lista = List(1, 3, 5)
    val f = (n: Int) => n + 3
    println(applyForAll(lista)(f)) // ==> List(4, 6, 8)
}



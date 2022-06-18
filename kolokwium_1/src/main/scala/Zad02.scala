/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  require(len > 0)
  require(shift > 0)
  @scala.annotation.tailrec
  def helper[A](list: List[A])(len: Int, shift: Int = 1, akum: List[List[A]], podlist: List[A], size: Int): List[List[A]] = list match{
    case List() => akum:+podlist
    case head :: tail => podlist match {
      case List() => helper(tail)(len,shift,akum,podlist:+head, size + 1)
      case h :: t => {
        if (size < len) helper(tail)(len,shift,akum,podlist:+head, size + 1)
        else helper(tail)(len,shift,akum:+podlist,List(head), 1)
        
        
      }
    }
    
    

  }
  helper(list)(len,shift,List(),List(),0)
}
@main
def zad2: Unit = {
  println(group(List(1,2,3,4,5))(1,1))
}

/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/

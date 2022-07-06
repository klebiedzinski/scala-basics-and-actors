/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  require(len > 0)
  require(shift > 0)
  // @scala.annotation.tailrec
  // def zipper[A](list: List[A],akum: List[(A,Int)],index: Int = 0): List[List[A]] = {
  //   case List() => akum
  //   case h::t => zipper(t,akum:+(h,index), index+1)
  // }

  @scala.annotation.tailrec
  def podLista[A](list: List[A],len: Int,akum: List[A]): List[List[A]] = list match{
    case List() => akum
    case head :: tail => {
      if (akum.size == len) akum
      else podLista(list,len,akum:+head)
    }
  }
  def foo[A](list: List[A])(len: Int, shift: Int ,akum: List[List[A]]): List[List[A]] = {
    list match{
      case List() => akum
      case podLista(list,len) ++ tail => {
        foo(list.drop(shift))(len,shift,akum:+podLista(list,len))
      }
      case podLista(list,len) => akum:+podLista(list,len)
    }
  }
  foo(list)(len,shift,List())
}
@main
def zad2: Unit = {
  println(group(List(1,2,3,4,5))(2,1))
}

/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/

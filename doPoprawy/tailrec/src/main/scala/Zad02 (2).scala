/*
  UWAGA! Uzupełnij kod funkcji „group” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/

def group[A](list: List[A])(len: Int, shift: Int = 1): List[List[A]] = {
  require(len > 0)
  require(shift > 0)
  @annotation.tailrec
  def tailGroup[A](list: List[A], akum: List[List[A]] = List(), sublist: List[A] = List(), counter: Int = 1)(len: Int, shift: Int = 1): List[List[A]] = (list, sublist, counter) match {
    case (Nil, _, _) => akum :+ sublist
    case (g::o, x, c) if x.length < len => tailGroup(o,akum, x :+ g, c)(len, shift)
    case (g::o, x, c) if x.length == len && counter == shift => tailGroup(o,akum :+ sublist, List(g), 1)(len, shift)
    case (g::o, x, c) if x.length == len && counter < shift => tailGroup(o, akum, List(), c+1)(len, shift)
  }
  tailGroup(list, List(), List(), 1)(len, shift)
}

@main
def zadanie_2: Unit = {
  val list = List(1,2,3,4,5,6,7)
  println(group(list)(2, 1))
}
//val ( list, len, shift ) = ( List(1,2,3,4,5), 2, 2 )

/*
  SUGESTIA. Być może ułatwisz sobie rozwiązanie zadania, jeśli „wewnątrz”
    funkcji „group” zdefiniujesz pewną liczbę funkcji pomocniczych. Pamiętaj,
    że jeśli będą one używały rekurencji to musi ona być „ogonowa“.
*/

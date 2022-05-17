// Definiujemy klasę reprezentuającą liczby wymierne

class Q(l: Int, m: Int) {
  // weryfikujemy sensowność mianownika
  require(m != 0)

  // definiujemy „znormalizowany” licznik i mianownik ułamka
  val licz = l / nwd(l,m)
  val mian = m / nwd(l,m)

  // println(s"to ja – $l/$m")
  override def toString(): String = {
    s"$licz/$mian"
  }

  // metoda „prywatna” nie jest dostępna „z zewnątrz” – jeszcze do niej wrócimy
  @annotation.tailrec
  private def nwd(a: Int, b: Int): Int = {
    if (a > b) nwd(a - b, b)
    else
      if (b > a) nwd(a, b - a)
      else a
  }

  // przykładowa operacja arytmetyczna na liczbach wymiernych
  def *(q: Q): Q = Q(licz * q.licz, mian * q.mian)
}

@main
def wymierne: Unit = {
  // Scala 3 standardowo pozwala tworzyć nowe obiekty danej klasy
  // bez użycia „new” – we wcześniejszych wersjach języka można
  // było również osiągnąć taki efekt, ale nie był on oferowany
  // „standardowo”. Teraz już jest.

  val q0 = new Q(1,2) // 1/2 „po staremu”
  val q1 = Q(1,2)     // 1/2 „po nowemu”
  println(s"q0 == $q0")
  println(s"q1 == $q1")

  // metoda „toString”
  println(q1.toString) // nie musimy używać „toString“ jawnie – wystarczy:
  println(q1)
  // println potrzebuje tekstowej/napisowej reprezentacji wartości q1 więc
  // automatycznie odwoła się do metody „toString” dla klasy Q.

  // problematyczne wartości wygenerują wyjątek „IllegalArgumentException”
  // println(Q(1,0))

  // przykład działa „normalizacji”
  val q2 = Q(2,4)
  println(q2)
  println(q1 * Q(4,6))

  // sensowne działanie równości wymaga kolejnego (po toString) „przesłonięcia”
  // standardowej definicji… O szczegółach – następnym razem
  println(Q(1,2) == Q(1,2)) // na razie (niestety) wynikiem jest „false”

}

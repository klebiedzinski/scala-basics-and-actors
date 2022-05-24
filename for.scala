/*
  „Wyliczenie” for i jego interpretacja „kolekcyjna”
*/

@main
def wyliczenieFor: Unit = {
  val lista = List(1,2,3,4,5)
  val zbiór = Set('a','b')

  // wyliczenie z jednym „generatorem”
  val wynikA = for {
    el <- lista
  } yield el * el

  println(wynikA)

  // for z jednym generatorem to faktycznie:
  val wynikB = lista.map(el => el * el)
  println(wynikB)

  // wyliczenie z dwoma generatorami
  val wynik2A = for {
    el1 <- lista
    el2 <- zbiór
  } yield (el1, el2)

  println(wynik2A)

  // powyższy „for” to uładniona forma:
  val wynik2B = lista flatMap { el1 =>
    zbiór map {
      el2 => (el1, el2)
    }
  }

  println(wynik2B)

  // wyliczenie z generatorami i filtrami
  val wynik3 = for {
    el1 <- lista
    if el1 % 2 == 0
    el2 <- zbiór
    if el2 != 'b'
  } yield (el1, el2)

  println(wynik3)
  // „Tłumaczenie” powyższego przykładu, obok
  // flatMap i map wykorzystuje jeszcze
  // metodę „withFilter”, a całość odbywa się
  // w ramach typu „WithFilter”, który także
  // oferuje metody „flatMap” i „map”.

  // Wyliczenie for i wartości typu Unit
  val dziwne1 = for {
    el <- lista
  } yield println(el)

  val dziwne2 = for {
    el <- lista
  } println(el)

  println("*" * 20)
  println(dziwne1)
  println("*" * 20)
  println(dziwne2)
  println("*" * 20)

  // „tłumaczenie” wartości dziwne2
  val dziwne3 = lista foreach {
    el => println(el)
  }
  println(dziwne3)
  println("*" * 20)

}

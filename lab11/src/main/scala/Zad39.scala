import akka.actor.{ActorSystem, Actor, ActorRef, Props}

class Szef extends Actor {
  var result: Int = 0
  def receive: Receive = {
     case Init(liczbaPracowników: Int) => {
       val listaPracowników: List[ActorRef] = List.range(1,liczbaPracowników)
       .map(el => context.actorOf(Props[Pracownik](), s"pracownik_$el"))
       context.become(zPracownikami(listaPracowników))
    }
    
  }
  

  def zPracownikami(pracownicy: List[ActorRef]): Receive = {
    case Zlecenie(lista) => {
      println("szef dostał zlecenie")
      context.become(odbieram(pracownicy,lista.size,1,0))
      lista.zipWithIndex
        .foreach(line => pracownicy(line(1)%pracownicy.size) ! Wykonaj(line(0)))
    }

  }
  def odbieram(pracownicy: List[ActorRef], iloscLinii: Int, ktoraLinia: Int, wynik: Int): Receive = {
    case Wynik(data) => {
      if (iloscLinii == ktoraLinia) println(wynik)
      
      context.become(odbieram(pracownicy,iloscLinii,ktoraLinia+1,wynik+data))
    }
  }
}
case class Init(liczbaPracowników: Int) 
case class Zlecenie(tekst: List[String])
case class Wykonaj(line: String)
case class Wynik(wynik: Int)

class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(line) => sender() ! Wynik(line.split(" ").size)
  }
}


@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
  // println(lista)
 val system = ActorSystem("zadanie39")
 val szef = system.actorOf(Props[Szef](), "szef")
 szef ! Init(5)
 szef ! Zlecenie(lista)
 

}



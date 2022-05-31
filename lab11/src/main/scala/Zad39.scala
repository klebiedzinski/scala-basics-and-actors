import akka.actor.{ActorSystem, Actor, ActorRef, Props}

class Szef extends Actor {
  def receive: Receive = {
     case Init(liczbaPracowników) => {
       val listaPracowników = List.range(1,liczbaPracowników)
       .map(el => ActorRef.actorOf(Props[Pracownik](), s"pracownik $el"))
    }
  }
  

  // def zPracownikami(pracownicy: List[ActorRef]): Receive = {
   
  // }
}
case class Init(liczbaPracowników: Int) 



class Pracownik extends Actor {
  def receive: Receive = {
    case msg => println(s"Odebrałem wiadomość: $msg")
  }
}


@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList

}

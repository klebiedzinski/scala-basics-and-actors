package jp1.akka.lab13.model

import akka.actor._

val akkaPathAllowedChars = ('a' to 'z').toSet union
  ('A' to 'Z').toSet union
  "-_.*$+:@&=,!~';.)".toSet

object Organizator {
  case object Start
  // rozpoczynamy zawody – losujemy 50 osób, tworzymy z nich zawodników
  // i grupę eliminacyjną
  case object Runda
  // polecenie rozegrania rundy (kwalifikacyjnej bądź finałowej) –  wysyłamy Grupa.Runda
  // do aktualnej grupy
  case object Wyniki
  // polecenie wyświetlenia klasyfikacji dla aktualnej grupy
  case class Wyniki(w: Map[ActorRef, Option[Ocena]])
  // wyniki zwracane przez Grupę
  case object Stop
  // kończymy działanie
}

class Organizator extends Actor {
  // importujemy komunikaty na które ma reagować Organizator
  import Organizator._

  def receive: Receive = {
    case Start =>
      // tworzenie 50. osób, odpowiadających im Zawodników
      // oraz Grupy eliminacyjnej
      val zawodnicy = List.fill(50) {
        val o = Utl.osoba()
        context.actorOf(Props(Zawodnik(o)), s"${o.imie}-${o.nazwisko}" filter akkaPathAllowedChars)
      }
      context.become(runda(zawodnicy))

    case Stop =>
      println("kończymy zawody...")
      context.system.terminate()
  }
  def runda(zawodnicy: List[ActorRef]): Receive = {
    case Runda => {
      val grupaEliminacyjna = context.actorOf(Props(Grupa(zawodnicy)), "grupaEliminacyjna")
      context.become(mogeProsicOWyniki(grupaEliminacyjna))
      grupaEliminacyjna ! Grupa.Runda
      
      
    }

  }
  def mogeProsicOWyniki(grupa: ActorRef): Receive = {
    case Wyniki =>{
      context.become(dostałemWyniki)
      grupa ! Grupa.Wyniki
    }
  }
 //(Actor[akka://system/user/organizator/Miquel-Corwin#-502203867], List(20, 14, 17), 51)
  def dostałemWyniki: Receive = {
      case Wyniki(w) => {
      sender() ! Grupa.Koniec
      // przetwarzanie wynikow i wylanianie 20 najlepszych
      val finalisci = w.toList
        .map(el => (el(0),List(el(1).get.nota1,el(1).get.nota2,el(1).get.nota3),(el(1).get.nota1+el(1).get.nota2+el(1).get.nota3).toInt))
        .sortBy(a => (a._3,a._2(0),a._2(1),a._2(2))).reverse.take(21)
      // println(finalisci) 
      println("przechodzimy do finalu")
      context.become(finał(finalisci.map(el => el._1), finalisci))  
  }
  }

  
  def finał(finalisci: List[ActorRef],wynikiEliminacji: List[(ActorRef,List[Int], Int)]): Receive = {
    case Runda => {
      val grupaFinalowa = context.actorOf(Props(Grupa(finalisci)), "grupaFinalowa")
      grupaFinalowa ! Grupa.Runda
      context.become(mogeProsicOWynikiFinalu(grupaFinalowa,wynikiEliminacji))
    }
  }
  def mogeProsicOWynikiFinalu(grupa: ActorRef,wynikiEliminacji: List[(ActorRef,List[Int], Int)]): Receive = {
    case Wyniki => {
      context.become(odbieramWynikiFinalu(wynikiEliminacji))
      grupa ! Grupa.Wyniki
    }
  }
  def odbieramWynikiFinalu(wynikiEliminacji: List[(ActorRef,List[Int], Int)]): Receive = {
    case Wyniki(w) => {
      sender() ! Grupa.Koniec
      val wynikFinalu = w.toList
        .map(el => (el(0),List(el(1).get.nota1,el(1).get.nota2,el(1).get.nota3),(el(1).get.nota1+el(1).get.nota2+el(1).get.nota3).toInt))
        .sortBy(a => (a._3,a._2(0),a._2(1),a._2(2))).reverse
      
        println(wynikFinalu)
    }
  }
}


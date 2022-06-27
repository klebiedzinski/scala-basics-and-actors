package jp1.akka.lab13.model

import akka.actor._

object Grupa {
  case object Runda
  // Zawodnicy mają wykonać swoje próby – Grupa
  // kolejno (sekwencyjnie) informuje zawodników
  // o konieczności wykonania próby i „oczekuje”
  // na ich wynik (typu Option[Ocena])
  case object Wyniki
  case object MamWyniki
  // Polecenie zwrócenia aktualnego rankingu Grupy
  // Oczywiście klasyfikowani są jedynie Zawodnicy,
  // którzy pomyślnie ukończyli swoją próbę
  case class Wynik(ocena: Option[Ocena])
  // Informacja o wyniku Zawodnika (wysyłana przez Zawodnika do Grupy)
  // np. Wynik(Some(Ocena(10, 15, 14)))
  // Jeśli zawodnik nie ukończy próby zwracana jest wartość Wynik(None)
  case object Koniec
  // Grupa kończy rywalizację
}
class Grupa(zawodnicy: List[ActorRef]) extends Actor {
  import Grupa._
  def receive: Receive = {
    case Runda => {
      context.become(zbieranieWynikow(zawodnicy, Map(), sender()))
      zawodnicy.foreach(zawodnik => zawodnik ! Zawodnik.Próba)
      
    }
  }
  def zbieranieWynikow(zawodnicy: List[ActorRef], wyniki: Map[ActorRef,Option[Ocena]], organizator: ActorRef): Receive = {
    case Wynik(ocena) => {
       val sender1 = sender()
       context.become(zbieranieWynikow(zawodnicy, wyniki+(sender1 -> ocena), organizator))
       println(wyniki.size+1 == zawodnicy.size)
       if (wyniki.size+1 == zawodnicy.size) {
        println("grupa moze dac wynik")
        context.become(mogeDacWynik(wyniki))
        
       }
    }
  }
  def mogeDacWynik(wyniki: Map[ActorRef,Option[Ocena]]): Receive = {
    case Wyniki => {
      sender() ! Organizator.Wyniki(wyniki.toList.filter(el => el(1) != None).toMap)
      // Map((aktor -> Ocena(Int,Int,Int)))
    }
    case Koniec => {
      println("koniec")
    }
  }
}



//grupa.scala
//po kolei (dla wszystkich zadownikow)
  // wysyla komunikat Prośba
  // oczekuje na wynik Zawodnika (zmieniając tożsamość?) - wynik to 
  // komunikat typu Grupa.Wynik(...)
// po otrzymaniu wyników od wszystkich zawodników odsyła komunikat Wyniki(...) do Organizatora
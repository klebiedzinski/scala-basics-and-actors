import akka.actor.*

object Organizator {
  case class UstawMaksCyk(maksCyk: Int) {
    assert(maksCyk > 0)
  }
  case object Cyk
  case class PrzejechanaTrasa(liczbaKm: Int)
  case class Init(liczbaKierowcow: Int)
}
class Organizator extends Actor with ActorLogging {
  import Organizator.*
  def receive = {
    case UstawMaksCyk(mc) =>
      log.info(s"liczba cyknięć do wykonania: $mc")
      context.become(ustawiamWarsztatIKierowcow(mc))
    
  }
  def ustawiamWarsztatIKierowcow(mc: Int ): Receive = {
    case Init(liczbaKierowcow) => {
      val dt = 60 //  !!!!!!! dt !!!!!!!!
      val warsztat = context.actorOf(Props[Warsztat](), "warsztat")
      warsztat ! Warsztat.Init
      val kierowcy = List.range(1,liczbaKierowcow+1).map(p => context.actorOf(Props[Kierowca](), s"kierowca_$p"))
      kierowcy.foreach(kierowca => kierowca ! Kierowca.PrzygotujAuto(warsztat,dt))
      context.become(poInicjalizacji(mc,0,kierowcy,warsztat))
      log.info("mamy kierowcow")    
    }

  }

  def poInicjalizacji(maksCyk: Int,iloscCykniec: Int, kierowcy: List[ActorRef], warsztat: ActorRef): Receive = {
    case Cyk =>
      log.info("Cyk")
      warsztat ! Warsztat.Cyk
      kierowcy.foreach(kierowca => kierowca ! Kierowca.Cyk)
      context.become(poInicjalizacji(maksCyk,iloscCykniec+1,kierowcy,warsztat))
      if (iloscCykniec==maksCyk) {
        kierowcy.foreach(kierowca => kierowca ! Kierowca.PodajTrasę)
        context.become(odbieramWyniki(kierowcy, Map()))
      }
  }
  def odbieramWyniki(kierowcy: List[ActorRef], wyniki: Map[ActorRef,Int]): Receive = {
    case PrzejechanaTrasa(liczbaKm) => {
      context.become(odbieramWyniki(kierowcy,wyniki + (sender() -> liczbaKm)))
      if (wyniki.size+1 == kierowcy.size) {
        println(wyniki.toList.sortBy(el => el._2).reverse.zipWithIndex.map(el => (el._2+1,el._1._1.path.name,el._1._2)))
        context.system.terminate()
      }
    }
  }
}


import akka.actor.*

object Kierowca {
  case object Cyk
  case class PrzygotujAuto(warsztat: ActorRef, dt: Int)
  case class ReakcjaAuta(ov: Option[Int])
  case object PodajTrasę
  case class WynikNaprawy(efekt: Option[ActorRef])
}
class Kierowca extends Actor with ActorLogging {
  import Kierowca._
  def receive: Receive = {
    case PrzygotujAuto(warsztat,dt) => {
      val moj_numer = self.path.name.split("_")(1)
      val mojeAuto = context.actorOf(Props[Samochód](), s"samochod_$moj_numer")
      
      log.info("moje auto jest gotowe")
      context.become(wyscig(mojeAuto,warsztat,dt))
    }
  }
  def wyscig(mojeAuto: ActorRef,warsztat: ActorRef, dt: Int,trasa: Int = 0): Receive = {
    case Cyk =>{
      log.info(s"${self.path.name}: gazu!")
      mojeAuto ! Samochód.Dalej
      
    }
    case ReakcjaAuta(None) => {
      log.info(s"${self.path.name}: rozwaliłem! idzie do mechanika")
      warsztat ! Warsztat.Awaria(mojeAuto)
      context.become(czekamNaAuto(mojeAuto,warsztat,dt,trasa))
    }
    case ReakcjaAuta(prędkość) => {
      val v = prędkość.get
      val s = dt*v
      context.become(wyscig(mojeAuto,warsztat,dt,s+trasa))

    }
    case PodajTrasę => {
      sender() ! Organizator.PrzejechanaTrasa(trasa)
    }
    
  }
  def czekamNaAuto(mojeAuto: ActorRef,warsztat: ActorRef, dt: Int,trasa: Int): Receive = {
    case WynikNaprawy(None) => {
      context.become(nieMogeKontynuowac(trasa))
    }
    case WynikNaprawy(Some(mojeAuto)) => {
      log.info(s"${self.path.name}: moje auto jest gotowe! wracamy!!!!!")
      context.become(wyscig(mojeAuto,warsztat,dt,trasa))
    }
    case Cyk => 
    case PodajTrasę => {
      sender() ! Organizator.PrzejechanaTrasa(trasa)
    }
  }
  def nieMogeKontynuowac(trasa: Int): Receive = {
    case PodajTrasę => {
      sender() ! Organizator.PrzejechanaTrasa(trasa)
    }
    case Cyk => 
  }

}

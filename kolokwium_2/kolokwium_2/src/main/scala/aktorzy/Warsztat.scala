import akka.actor.*
import scala.util.Random
object Warsztat {
  case object Cyk
  case class Awaria(auto: ActorRef)
  case object Init
}
class Warsztat extends Actor with ActorLogging {
  import Warsztat._
  def receive: Receive = {
    case Init => {
      context.become(warsztatReceiver(List()))
    }
  }
  def warsztatReceiver(listaAut: List[(Int,ActorRef,ActorRef)]): Receive = { // (czasNaprawy, auto, kierowca)
    case Awaria(auto) => {
      log.info(s"Warsztat: dostałem auto od ${sender().path.name}")
      val rand = new Random
      val próbaUdana = 0.80
    if (rand.nextDouble() > próbaUdana){
      log.info(s"Warsztat: auta ${auto.path.name.last} nie uda się naprawić")
      sender() ! Kierowca.WynikNaprawy(None)
    } 
    else {
      val czasNaprawy = rand.nextInt(6)
      log.info(s"Czas naprawy auta to $czasNaprawy")
      context.become(warsztatReceiver(listaAut:+(czasNaprawy,auto,sender())))
    }

    }
    case Cyk => {
        log.info(s"Auta do naprawy: $listaAut")
        if (listaAut.length == 0) log.info("Warsztat: nie mam co naprawiać")
        else {
          listaAut.foreach(el => {
            if (el._1-1==0){
              log.info(s"auto ${el._2.path.name.last} gotowe! wysyłam do kierowcy")
              el._3 ! Kierowca.WynikNaprawy(Some(el._2))
            } 
            else {
                log.info(s"czas naprawy auta ${el._2.path.name.last} wynosi ${el._1-1} ")
            }
            
          })
          context.become(warsztatReceiver(listaAut.map(el => (el._1-1,el._2,el._3)).filter(el => el._1 >= 1)))
        } 
    }
  }
}

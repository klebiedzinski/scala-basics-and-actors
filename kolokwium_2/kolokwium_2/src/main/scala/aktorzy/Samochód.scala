import akka.actor.*
object Samochód {
  case object Dalej
}
class Samochód extends Actor with ActorLogging {
  import Samochód._
  def receive: Receive = {
    case Dalej => {
    import scala.util.Random
    val rand = new Random
    val próbaNieudana = 0.15
    val reply = {
    if (rand.nextDouble() > próbaNieudana) {
      val v = rand.nextInt(200)
      Some(v)
    } else {
     None
    }
    }
    log.info(s"${self.path.name} jade $reply na godzine")
    sender() ! Kierowca.ReakcjaAuta(reply)
  }

      
    }
  }


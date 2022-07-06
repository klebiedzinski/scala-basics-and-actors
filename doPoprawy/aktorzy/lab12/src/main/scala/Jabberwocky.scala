import akka.actor._
import scala.concurrent.duration._
import javax.sound.midi.Receiver
import scala.util.Random
/*
  W konfiguracji projektu wykorzystana została wtyczka
  sbt-revolver. W związku z tym uruchamiamy program poleceniem

    reStart

  a zatrzymujemy pisząc (mimo przesuwających się komunikatów)

     reStop

  i naciskając klawisz ENTER. Jeśli czynności powyższe
  już wykonywaliśmy to możemy też przywołać poprzednie
  polecenia używając strzałek góra/dół na klawiaturze.
*/


object SiłaWyższa {
  case object Cyk
  case class Strzelać(przeciwnik: ActorRef)
  case object Init
}
class SiłaWyższa extends Actor {
  import SiłaWyższa._
  def receive = {
    case Init => {
      val zamek1 = context.actorOf(Props[Zamek](),"zamek1")
      val zamek2 = context.actorOf(Props[Zamek](),"zamek2")
      zamek1 ! Zamek.Init
      zamek2 ! Zamek.Init
      context.become(walczymy(zamek1,zamek2))
      
    }
  }
  def walczymy(zamek1: ActorRef, zamek2: ActorRef): Receive = {
    case Cyk => {
      zamek1 ! Strzelać(zamek2)
      zamek2 ! Strzelać(zamek1)
    }
  }
}
object Zamek {
  case object Init
  case class Strzelaj(przeciwnik: ActorRef)
  case class StrzałaDoMojegoObroncy(kompani: List[ActorRef])
}
class Zamek extends Actor {
  import Zamek._
  def receive: Receive = {
    case Init => {
      val obroncy = List.range(1,101).map(el => context.actorOf(Props[Obrońca](),s"obronca_$el"))
      obroncy.foreach(obronca => context.watch(obronca))
      context.become(walczymy(obroncy))
    }
  }
  def walczymy(obroncy: List[ActorRef]): Receive = {
    case Terminated(obronca) => {
      if (obroncy.filter(el => el != obronca).size == 0) {
        println(s"${self.path.name}: przegralismy :(")
        context.system.terminate()
      }
      else context.become(walczymy(obroncy.filter(el => el != obronca)))
    }
    case SiłaWyższa.Strzelać(przeciwnik) => {
      obroncy.foreach(el => el ! Strzelaj(przeciwnik))
    }
    case Obrońca.Strzała => {
      // println(s"${self.path.name}: dostaliśmy! losuje nieszczesnika i wysylam mu strzale")
      val rand = new Random
      val nieszczesnik = obroncy(rand.nextInt(obroncy.length))
      nieszczesnik ! StrzałaDoMojegoObroncy(obroncy.filter(el => el != nieszczesnik))
      
    }
  }
}
object Obrońca {
  case object Strzała
}
class Obrońca extends Actor {
  import Obrońca._
  def receive: Receive = {
    case Zamek.Strzelaj(przeciwnik) => przeciwnik ! Strzała
    case Zamek.StrzałaDoMojegoObroncy(kompani) => {
      // println(s"${self.path.name}: dostałem strzała:(, zobaczymy czy umre")
      val rand = new Random
      // println(kompani)
      val chanceOfDeath = (kompani.size/200.0)
      println(chanceOfDeath)
      if (rand.nextFloat>chanceOfDeath) self ! PoisonPill
      
    }
  }
}

@main
def bitwa: Unit = {
  val system = ActorSystem("Jabberwocky")
  import system.dispatcher

  // UWAGA: „nazwy”, tworzące ścieżkę do aktora muszą być zapisywane
  // z użyciem znaków znaków ASCII (a nie np. UTF8) – stąd „SilaWyzsza”
  val siłaWyższa = system.actorOf(Props[SiłaWyższa](), "SilaWyzsza")
  siłaWyższa ! SiłaWyższa.Init
  // Do „animacji” SiłyWyższej wykorzystamy „Planistę” (Scheduler)
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    1000.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    SiłaWyższa.Cyk     // komunikat
  )

  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  // Thread.sleep(3000)
  // val res = if pantaRhei.cancel()
  //   then "Udało się zakończyć „cykanie”"
  //   else "Coś poszło nie tak – dalej „cyka”"
  // println(res)
  // system.terminate()
}

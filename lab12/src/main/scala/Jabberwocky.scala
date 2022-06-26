import akka.actor._
import scala.concurrent.duration._
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
  case object Init
  case class Strzelać(przeciwnicy: ActorRef)
  
}
class SiłaWyższa extends Actor {
  import SiłaWyższa._
  def receive: Receive = {
    case Init => {
      val zamek1 = context.actorOf(Props[Zamek](), "zamek1")
      val zamek2 = context.actorOf(Props[Zamek](), "zamek2")
      zamek1 ! Zamek.Init
      zamek2 ! Zamek.Init
      context.become(walka(List(zamek1,zamek2)))
    }
  }
  def walka(zamki: List[ActorRef]): Receive = {
    case Cyk => {
      println("Cyk")
      // wysyłamy polacenie „Strzelać” do obu Zamków
      zamki(0) ! SiłaWyższa.Strzelać(zamki(1))
      zamki(1) ! SiłaWyższa.Strzelać (zamki(0))
      
    }

  

  }

  }
  


object Zamek {
  case object Init
  case object Strzelaj
  case object Strzała
}
class Zamek extends Actor {
  import Zamek._
  def receive: Receive = {
    case Init => {
      val obroncy = List.range(1,101).map(p => context.actorOf(Props[Obrońca](), s"obronca${self.path.name.toString.last}_$p"))
      obroncy.foreach(obronca => context.watch(obronca))
      context.become(walczymy(obroncy))
      println(s"${self.path.name}: mam swoich obroncow")
    }
  }
  def walczymy(obrońcy: List[ActorRef]): Receive = {
    case Terminated(poległy) => {
      // println(s"${self.path.name}: stracilismy obronce, zostalo ich nam ${obrońcy.size-1}")

      if (obrońcy.size-1 == 0) {
      println(s"${self.path.name}: Przegralismy")
      context.system.terminate()
      }

      println(obrońcy.filter(el => el == poległy))
      context.become(walczymy(obrońcy.filter(el => el != poległy)))
      
    }
    case SiłaWyższa.Strzelać(przeciwnicy) => {
      // println(s"${self.path.name}: STRZELAJCIE!!!")
      obrońcy.foreach(p => p ! Obrońca.Strzelaj(przeciwnicy))
    }
    case Strzała => {
      val random = scala.util.Random
      val nieszczęśnik = obrońcy(random.nextInt(obrońcy.length))
      nieszczęśnik ! Obrońca.Strzała(obrońcy)
    }

  }
}
object Obrońca {
  case class Strzelaj(przeciwnicy: ActorRef)
  case class Strzała(obrońcy: List[ActorRef])
  
}
class Obrońca extends Actor {
  import Obrońca._
  def receive: Receive = {
    case Strzelaj(przeciwnicy) => {
      przeciwnicy ! Zamek.Strzała
    }
    case Strzała(koledzy) => {
      // println(s"${self.path.name}: Leci na mnie strzała!")
      val chance = (koledzy.size/200)
      val r = scala.util.Random
      // println(s"${self.path.name}: trafiła mnie :(")
      val czyUmre = r.nextFloat>chance
      // println(czyUmre)
      if (czyUmre) self ! PoisonPill
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
  
  
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    1000.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    SiłaWyższa.Cyk    // komunikat
  )
  
  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  // Thread.sleep(3000)
  // val res = if pantaRhei.cancel()
  //   then "Udało się zakończyć „cykanie”"
  //   else "Coś poszło nie tak  dalej „cyka”"
  // println(res)
  // system.terminate()
  
  

}

object AkkaIntro {
  import akka.actor.{ActorSystem, Actor, ActorRef, Props}

 class Gracz35 extends Actor {
    def receive: Receive = {
      case Graj35(przeciwnik) =>{
         println(s"${self.path.name} - odebrałem piłeczke!")
         przeciwnik ! Graj35(self)
      }
      
      case msg => println(s"${self.path.name} - odebralem wiadomosc $msg")
      
    }
}
 case class Graj35 (przeciwnik: ActorRef)
 case object Piłeczka
  @main
  def main: Unit = {
   
    val system = ActorSystem("Zad35")
    val gracz1 = system.actorOf(Props[Gracz35](), "gracz1")
    val gracz2 = system.actorOf(Props[Gracz35](), "gracz2")
    gracz1 ! Graj35(gracz2)
    
  }
}

object AkkaIntro {
  import akka.actor.{ActorSystem, Actor, ActorRef, Props}

  class MyActor extends Actor {
    def receive: Receive = {
      case msg => println(s"Odebrałem wiadomość: ${msg}")
    }
  }

  @main
  def main: Unit = {
    // val system = ActorSystem("HaloAkka")
    // try {
    //   val leonardo = system.actorOf(Props[MyActor](), "leonardo")
    //   leonardo ! "Dostałeś Oskara!"
    //   println(">>> Naciśnij ENTER żeby zakończyć <<<")
    //   io.StdIn.readLine()
    // } finally {
    //   // system.terminate()
    // }
     val w = Map("John Williams" -> (18,29,27), "Bill Wayman" -> (17,26,21), "Rondald" -> (17,26,21), "Thomas" -> (16,26,22))
     val x = w.toList.sortBy(el => el(1)(2)).sortBy(el => el(1)(1)).sortBy(el => el(1)(0)).reverse.zipWithIndex.map(el => (el(1)+1,el(0)(0),el(0)(1)))
     .map(el => (el,el(2)(0)+el(2)(1)+el(2)(2))).map(el => (el(0)(0),el(0)(1),el(0)(2),el(1)))
     
     println(x)
   
     

  }
}

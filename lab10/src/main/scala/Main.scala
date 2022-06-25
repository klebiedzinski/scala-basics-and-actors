object AkkaIntro {
  import akka.actor.{ActorSystem, Actor, ActorRef, Props}
  case object piłka1
  case class Graj35(przeciwnik: ActorRef)
  class Gracz35 extends Actor {
    

    def receive: Receive = {
      case Graj35(przeciwnik) => przeciwnik ! piłka1
      case piłka1 => 
        println(s"${self.path.name}: odebrałem piłeczke!")
        sender() ! piłka1
    }

  }

  @main
  def zadanie35: Unit = {
    val system = ActorSystem("zadanie35")
    val gracz_1 = system.actorOf(Props[Gracz35](), "gracz_1")
    val gracz_2 = system.actorOf(Props[Gracz35](), "gracz_2")

    // gracz_2 ! Graj35(gracz_1)
  }

  case class piłka2(i: Int,max: Int)
  case class Graj36(przeciwnik: ActorRef, maks: Int)
  class Gracz36 extends Actor {
    def receive: Receive = {
      case Graj36(przeciwnik,maks) => przeciwnik ! piłka2(1,maks)
      case piłka2(i,max) => {
        println(s"${self.path.name}: odebrałem piłeczke!")
        if (max == i) context.system.terminate()
        else sender() ! piłka2(i+1,max)
      }

    }
  }
  @main
  def zadanie36: Unit = {
    val system = ActorSystem("zadanie36")
    val gracz_1 = system.actorOf(Props[Gracz36](), "gracz_1")
    val gracz_2 = system.actorOf(Props[Gracz36](), "gracz_2")

    gracz_2 ! Graj36(gracz_1,10)
  }

  case class Graj37(zawodnicy: List[ActorRef])
  case class piłka3(przeciwnik: ActorRef, ja: ActorRef)
  class Gracz37 extends Actor {
    def receive: Receive = {
      case Graj37(zawodnicy) =>  zawodnicy(0) ! piłka3(zawodnicy(1), self)
      case piłka3(przeciwnik1, przeciwnik2) => {
        przeciwnik1 ! piłka3(przeciwnik2,self)
        println(s"${self.path.name}: dostałem piłke")
      }
    }
  }



  @main
  def zadanie37: Unit = {
    val system = ActorSystem("zadanie37")
    val gracz_1 = system.actorOf(Props[Gracz37](), "gracz_1")
    val gracz_2 = system.actorOf(Props[Gracz37](), "gracz_2")
    val gracz_3 = system.actorOf(Props[Gracz37](), "gracz_3")

    gracz_1 ! Graj37(List(gracz_2,gracz_3))
  }
  case class Graj38(zawodnicy: List[ActorRef])
  case class piłka38(zawodnicy: List[ActorRef])
  class Gracz38 extends Actor {
    def receive: Receive = {
      case Graj38(zawodnicy) => {
        println("graj38 dziala")
        val myNumber = self.path.name.split("_")(1).toInt
       
        zawodnicy(myNumber+1) ! piłka38(zawodnicy)
      }
      case piłka38(players) => {
        println(s"${self.path.name}: dostałem piłke")
        val myNumber = self.path.name.split("_")(1).toInt
        if (myNumber == players.size - 1)  players(0) ! piłka38(players)
        else players(myNumber+1) ! piłka38(players)
      }
    }
  }
  @main
  def zadanie38: Unit = {
    var liczbagraczy = 10000
    val system = ActorSystem("zadanie37")
    val listagraczy: List[ActorRef] = List.range(0,liczbagraczy)
      .map(p => system.actorOf(Props[Gracz38](), s"gracz_${p}"))
    println(listagraczy.map(p => p.path.name.last))
    listagraczy(0) ! Graj38(listagraczy)
  }



  

}
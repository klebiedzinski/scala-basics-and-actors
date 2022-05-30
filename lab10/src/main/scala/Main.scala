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
  def zad1: Unit = {
   
    val system = ActorSystem("Zad35")
    val gracz1 = system.actorOf(Props[Gracz35](), "gracz1")
    val gracz2 = system.actorOf(Props[Gracz35](), "gracz2")
    gracz1 ! Graj35(gracz2)
    
  }




  
case class Ball2(max: Int, i: Int)
case class Play2(opponent: ActorRef, max: Int)

class Player2 extends Actor {
  def receive: Receive = {
    case Play2(actor, max) =>
      actor ! Ball2(max, 1)
    case Ball2(max, i) =>
      if (i > max) context.system.terminate()
      else {
        println(s"Piłeczka ${self.path.name}")
        sender() ! Ball2(max, i + 1)
      }
  }
}

@main
def zad2(): Unit = {
  val system = ActorSystem("tableTennis")

  val player1Ref = system.actorOf(Props[Player2](), "player1")
  val player2Ref = system.actorOf(Props[Player2](), "player2")

  player1Ref ! Play2(player2Ref, 6)
}





case class Ball3(nextPlayer1: ActorRef, nextPlayer2: ActorRef)
case class Play3(nextPlayer1: ActorRef, nextPlayer2: ActorRef)

class Player3 extends Actor {
  def receive: Receive = {
    case Play3(nextPlayer1, nextPlayer2) => nextPlayer1 ! Ball3(nextPlayer2, self)
    case Ball3(nextPlayer1, nextPlayer2) =>
      println(s"Piłeczka ${self.path.name}")

      nextPlayer1 ! Ball3(nextPlayer2, self)
  }
}

@main
def zad3(): Unit = {
  val system = ActorSystem("tableTennis")

  val player1 = system.actorOf(Props[Player3](), "player1")
  val player2 = system.actorOf(Props[Player3](), "player2")
  val player3 = system.actorOf(Props[Player3](), "player3")

  player1 ! Play3(player2, player3)
}




case class Ball4(players: List[ActorRef], playersNumber: Int)
case class Play4(players: List[ActorRef], playersNumber: Int)

class Player4 extends Actor {
  def receive: Receive = {
    case Play4(players, playersNumber) => players(playersNumber) ! Ball4(players, playersNumber)
    case Ball4(players, playersNumber) =>
      println(s"Piłeczka ${self.path.name}")

      if (playersNumber + 1 > players.length - 1) players.head ! Ball4(players, 0)
      else players(playersNumber + 1) ! Ball4(players, playersNumber + 1)
  }
}

@main
def zad4(): Unit = {
  val system = ActorSystem("tableTennis")

  println("Podaj liczbę graczy: ")
  val numberOfPlayers = io.StdIn.readLine().toInt

  val listOfPlayers = List.range(0, numberOfPlayers).map(playersNumber => {
    system.actorOf(Props[Player4](), s"player${playersNumber + 1}")
  })

  listOfPlayers.head ! Play4(listOfPlayers, 0)
}
}

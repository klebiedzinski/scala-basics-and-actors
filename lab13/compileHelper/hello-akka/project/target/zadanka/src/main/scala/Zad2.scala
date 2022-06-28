import akka.actor._

object Zad2 {

  case class Zacznij(maks: Int)
  case object Badanie
  case class Wynik(wyn: Int)
  case class Przyjmij(ob: ActorRef)
  case object Sprawdz
  case class StanZdrowia(stan: Int)
  case class Odpowiedź( /*wymyśl atrybuty*/ )
  case object StanSzpitala

  class Nadzorca extends Actor {
    def receive: Receive = {
      ???
    }
  }

  class Obywatel extends Actor {
    def receive: Receive = {
      ???
    }
  }

  class Szpital extends Actor {
    def receive: Receive = {
      ???
    }
  }

  def main(args: Array[String]): Unit = {
    val r = util.Random
    // r jest „generatorem”, którego należy użyć do generowania wartości
    // losowych różnych typów (i zakresów) np. r.nextInt, r.nextInt(100)

    ???
  }

}

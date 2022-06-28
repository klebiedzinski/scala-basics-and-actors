import akka.actor._

object Zad1 {

  case class Init(liczbaPracownikow: Int)
  class Manager extends Actor {
    def receive: Receive = {
      case Init(liczbaPracownikow) => {
        val r = util.Random
        val listaPracownikow = List.range(1,liczbaPracownikow+1).map({
          val x = r.nextFloat
          val y = r.nextFloat
          el => context.actorOf(Props(Pracownik(x,y)), s"pracownik_$el")
        })
        println(listaPracownikow)
      }
    }
  }

  class Pracownik(x: Double, y: Double) extends Actor {
    def receive: Receive = {
      ???
    }
  }

  class KumulatorWyniku extends Actor {
    def receive: Receive = {
      ???
    }
  }

  def main(args: Array[String]): Unit = {
    val r = util.Random
    val system = ActorSystem("system")
    val manager = system.actorOf(Props[Manager](), "manager")
    manager ! Init(5)
    // r jest „generatorem”, którego należy użyć do generowania wartości
    // losowych różnych typów (i zakresów) np.
    // r.nextInt (zwraca pseudolosową liczbę całkowitą z zakresu od 0 do 1),
    // r.nextInt(100) (zwraca pseudolosową liczbę całkowitą z zakresu od 0 do 99),
    // r.nextFloat (zwraca pseudolosową liczbę zmiennoprzecinkową z zakresu od 0 do 1).

    // wyliczenie pierwiastka z liczby 4: math.sqrt(4)
  }

}

import scala.annotation.tailrec
val helloStr = "Hello, World!"

@main
def zadanie_10: Unit = {
  @tailrec
  def sumuj(l: List[Option[Double]], akumulator: Option[Double] = None): Option[Double] = l match {
    case Nil => akumulator
    case Some(głowa) :: ogon if głowa > 0 => akumulator match {
      case None => sumuj(ogon,Some(głowa))
      case Some(x) => sumuj(ogon,Some(x+głowa))
    }
    case _ :: ogon => sumuj(ogon,akumulator)
  } 
    val lista = List(Some(4.0), Some(-3.0), None, Some(1.0), Some(0.0))
    val wynik = sumuj(lista) // ==> Some(5.0)
    println(wynik)
}



import scala.io.Source


@main 
def zadanie_33: Unit = {
  val dane = Source.fromFile("nazwiska.txt").getLines.toList
  .map(p => p.split(" ").toList)
  .map(p => (p(0).toLowerCase.distinct,p(1)).toList)
  .sortBy(p => p(0).size).reverse
  val max = dane(0)(0).size
  val almostResult = dane.filter(p => p(0).size == max).sortBy(p => p(1).size)
  val min = almostResult(0)(1).size
  val result = almostResult.filter(p => p(1).size == min).map(p => s"${p(0)} ${p(1)}")
  

  println(result)
  
}


def histogram(max: Int): Unit = {
    val dane = Source.fromFile("ogniem_i_mieczem.txt").toList.map(p => p.toLower).filter(p => p.isLetter == true)
    .toSeq.groupBy(identity).mapValues(_.size).toList
    .sortBy(p => p(1)).reverse
    .map(p => (p(0), "*" * p(1)))
    .map(p => s"${p(0)}: ${p(1)} \n").toString



    println(dane)

}

@main
def zadanie_34: Unit = {
  histogram(0)
}
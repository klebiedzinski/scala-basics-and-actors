/*
  UWAGA! Uzupełnij kod funkcji „ranking” zgodnie z treścią zadania.
         Z poziomu SBT wydaj polecenie „test” żeby sprawdzić, czy
         Twoje rozwiązanie przechodzi przygotowane testy jednostkowe.
         Możesz dzięki temu nie tworzyć „programu głównego”.
*/
//List[(Int, Int)]
// 25 pytan
def ranking():List[(Int, Int)] = {
  val input = io.Source
    .fromResource("test.txt")
    .getLines
    .toList
    .map(x => x.split(" ").toList)
    .tail
    .toList
    .map(x => x.tail.zipWithIndex)
    .flatten
    .map(x => (x(0).toInt,x(1).toInt+1))
    .groupBy(_(1)).toList
    .map(x => (x(0),x(1).map(el => el(0))))
    .map(x => (x(0), x(1).filter(el => el==1).length))
    println(input.filter(x => x._2> 49))
    println(input.filter(x => x._2> 51))
    val result = input
    .sortBy(_._2).reverse
    .zipWithIndex
    .map(el => (el(1)+1,el(0)(0) ))
    
  

    result
}

@main
def zadanie_1: Unit = {
   val input = io.Source
    .fromResource("test.txt")
    .getLines
    .toList
    
    println(ranking())
  
 
}

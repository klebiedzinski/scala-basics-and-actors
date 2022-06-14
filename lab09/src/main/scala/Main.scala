@main 
def zadanie_33: Unit = {
  val osoby =io.Source.fromFile("nazwiska.txt").getLines.toList
  val imieINazwisko = osoby.map((a) => a.split(" ").toList).toList
  val max = imieINazwisko // 11
  .map(list => list(0).toLowerCase.toSeq.distinct.unwrap.size).toList.max
  val imieINazwiskoSorted = imieINazwisko.filter((el) => el(0)
    .toLowerCase.toSeq.distinct.unwrap.size == 11)
  val result = imieINazwiskoSorted.minBy(n => n(1))
  println(result)

}

def histogram(max: Int): String = {
     val OIM = io.Source.fromFile("ogniem_i_mieczem.txt").toList.map(el => el.toLower).filter(el => el.isLetter)
     val O = OIM.toSeq.groupBy(identity).mapValues(_.size).toList.sortWith((p1, p2) => p1._2 > p2._2)
     val result = O.map((a,b) => s"${a}: ${List.fill(b)("*").mkString}\n")
     
     result.mkString
}
@main
def zadanie_34: Unit = {
  
  println(histogram(0))
  

  val l1 = List(1,2,3)
  val el = 2
  println(l1 :+ el)
}



// e._1 --------- pierwszy element z pary
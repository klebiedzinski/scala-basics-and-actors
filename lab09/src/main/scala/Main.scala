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

@main
def zadanie_34: Unit = {
  val OIM = io.Source.fromFile("ogniem_i_mieczem.txt").toList
  val slowa = OIM.map((a) => a.toLower).toList
  println(slowa)
}



// e._1 --------- pierwszy element z pary
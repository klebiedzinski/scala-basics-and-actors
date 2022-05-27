// ## ZADANIE 2
// Korzystając z metod oferowanych przez kolekcje iterowalne napisz funkcję
// ```scala
// def zliczPodane(tekst: String)(znaki: Set[Char]): Map[Char, Int]
// ```
// zliczającą podane znaki w podanym tekście.

// ```scala
// println(zliczPodane("spaghetti")(Set('a', 'b', 'u', 't', 'p'))) // Map(t -> 2, u -> 0, a -> 1, b -> 0, p -> 1)
// ```

// def zliczPodane(tekst: String)(znaki: Set[Char]): Map[Char, Int] = {

// }
@main 
def cw1: Unit = {
    val tekst = "spaghetti".split("").toList.toSeq.groupBy(identity).mapValues(_.size).toList.map(p => (p(0).head,p(1)))
    val znaki = Set('a', 'b', 'u', 't', 'p').toList.toSeq.groupBy(identity).mapValues(_.size).toList.map(p => (p(0),p(1)-1))

    val res = znaki.map(p => (p(0), (tekst.find(el => el(0) == p(0))).getOrElse((0,0))._2))
    println(res)
    println(tekst)
    println(znaki)

}
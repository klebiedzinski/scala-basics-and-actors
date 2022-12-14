/*
Plik temepratury.txt zawiera w pierwszej kolumnie rok oraz w kolejnych dwunastu kolumnach
średnią temperaturę za każdy miesiąc w danym roku (kolejno: styczeń, luty, marzec itd.).
Dane dotyczące każdego roku rozdzielone są pojedynczymi spacjami.

Przykładowo:

    1779 -4.9 2.2 3.8 9.5 15.4 16.4 17.9 19.5 14.7 9.3 4.1 1.4
    1780 -5.1 -4.3 4.4 5.9 14.2 17.2 19.4 17.9 13.1 9.4 2.8 -4.6
    1781 -4.0 -1.9 1.5 9.1 13.8 19.2 20.1 22.8 16.2 6.0 4.0 -3.6

Zdefiniuj funkcję:

    def maxAvgTemps(data: List[String]): Set[(Int, Double)]

która wyznaczy maksymalną średnią temperaturę dla każdego miesiąca, zwracając zbiór par w formacie (miesiąc, temperatura):

    Set((1,3.5), (2,5.1), (3,7.4), (4,13.2), (5,18.2), (6,22.4), (7,23.5), (8,23.8), (9,16.8), (10,12.6), (11,7.6), (12,3.9))

Rozwiąż to zadanie używając metod oferowanych przez kolekcje. Nie używaj zmiennych, kolekcji
mutowalnych, "pętli" (while, for bez yield, foreach) oraz nie definiuj żadnej funkcji rekurencyjnej.

*/





import scala.io.Source
def maxAvgTemps(data: List[String]): Set[(Int, Double)] = {
    data
    .map(p => p.split(" ").toList)
    .map(p => p.tail.zipWithIndex.map(el =>( el(0),el(1)+1)))
    .flatten
    .groupBy(p => p(1)).toList
    .sortBy(p => p(0))
    .map(p => (p(0), p(1).map(el => el._1.toDouble)))
    .map(p => (p(0),p(1).max)).sorted.toSet
    
}
@main
def zad3: Unit = {
    val dane = Source.fromFile("temperatury.txt")
    .getLines
    .toList
    
    // println(dane)
    println(maxAvgTemps(dane))
    
    
    
    
    
    




    
}































// def maxAvgTemps(data: List[String]): Set[(Int, Double)] = {
//     data.map(p => p.split(" ").toList)
// }
// @main
// def zad3: Unit = {
//     val data = io.Source
//         .fromFile("temperatury.txt")
//         .getLines
//         .toList
//         .map(p => p.split(" ").toList)
//         .map(p => List(p(1),p(2),p(3),p(4),p(5),p(6),p(7),p(8),p(9),p(10),p(11),p(12)).zipWithIndex)
//         .flatten
//         .groupBy(_(1)).toList
//         val m1 = data.filter(p => p(0) == 0).map(p => p(1)).flatten.map(p=> p(0))
//         val m2= data.filter(p => p(0) == 1).map(p => p(1)).flatten.map(p=> p(0))
//         val m3= data.filter(p => p(0) == 2).map(p => p(1)).flatten.map(p=> p(0))
//         val m4= data.filter(p => p(0) == 3).map(p => p(1)).flatten.map(p=> p(0))
//         val m5= data.filter(p => p(0) == 4).map(p => p(1)).flatten.map(p=> p(0))
//         val m6= data.filter(p => p(0) == 5).map(p => p(1)).flatten.map(p=> p(0))
//         val m7= data.filter(p => p(0) == 6).map(p => p(1)).flatten.map(p=> p(0))
//         val m8= data.filter(p => p(0) == 7).map(p => p(1)).flatten.map(p=> p(0))
//         val m9= data.filter(p => p(0) == 8).map(p => p(1)).flatten.map(p=> p(0))
//         val m10= data.filter(p => p(0) == 9).map(p => p(1)).flatten.map(p=> p(0))
//         val m11= data.filter(p => p(0) == 10).map(p => p(1)).flatten.map(p=> p(0))
//         val m12= data.filter(p => p(0) == 11).map(p => p(1)).flatten.map(p=> p(0))
//        println(((1,m1.max),(2,m2.max),(3,m3.max),(4,m4.max),(5,m5.max),(6,m6.max),(7,m7.max),(8,m8.max),(9,m9.max),(10,m10.max),(11,m11.max),(12,m12.max)))
    
        
        
        
    
// }


/*
    Wykorzystując rekurencję (wyłącznie ogonową) zdefiniuj funkcję:

        def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Set[(C, Int)]

    która zaaplikuje funkcję „f” do elementów l1(i), l2(i), gdzie 0 <= i < min(l1.length, l2.length)
    oraz zwróci zbiór składający się z par:

        (wynik funkcji f, liczba par dla których f zwróciła dany wynik).

    Przykładowo dla:

        countResults(List(1,2,3), List(4,5,4,6))(_+_) == Set((5,1), (7,2))

    ponieważ: 1+4 = 5, 2+5 = 7, 4+3 = 7, 6 pomijamy bo to „nadmiarowy” element w drugiej z list.

    W rozwiązaniu należy skorzystać z mechanizmu dopasowania do wzorca (pattern matching).
    Nie używaj zmiennych ani „pętli” (while, for bez yield, foreach).
*/



def countResults[A,B,C](l1: List[A], l2: List[B])(f: (A, B) => C): Set[(C,Int)] = {
    @annotation.tailrec
    def helper[A,B,C](l1: List[A], l2: List[B],akum: List[C] = List())(f: (A, B) => C): List[C] = (l1,l2) match{
        case (h1::t1,h2::t2) => helper(t1,t2,akum:+f(h1,h2))(f)
        case (List(),_) => akum
        case (_,List()) => akum
    }
   
    helper(l1,l2)(f).toSeq.groupBy(identity).mapValues(_.size).toSet
}
@main
def zad1: Unit = {
    println(countResults(List(1,2,3), List(4,5,4,6))(_+_))// == Set((5,1), (7,2))
}


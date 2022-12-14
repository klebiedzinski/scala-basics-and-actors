/*
    Korzystając wyłącznie z operacji na kolekcjach (w szczególności nie możesz uzyć rekurencji
    ani mechanizmów imperatywnych, takich jak zmienne i pętle) zdefiniuj funkcję:

        def findPairs(n: Int): Set[(Int,Int)]

    taką, że dowolnej liczby całkowitej N > 1

        findPairs(N)

    zawiera wszystkie pary postaci (p1, p2), gdzie p1 i p2 są liczbami
    pierwszymi takimi, że p1 + p2 = 2 * N oraz p1 <= p2.

*/

// def findPairs(n: Int): Set[(Int,Int)] = {

    
// }

def isPrime(i :Int) : Boolean = {
    if (i <= 1)
      false
    else if (i == 2)
      true
    else
      !(2 to (i-1)).exists(x => i % x == 0)
}
//Set[(Int,Int)]
def findPairs(n: Int):  Unit= {
  
  val result = for {
  
    p1 <- (2 to n*n*n)
    p2 <- (2 to n*2)
    if (isPrime(p1) && isPrime(p2) && p1 + p2 == 2*n && p1 <=p2)
  } yield (p1,p2)
  println(result.toSet)
}

@main
def zad5: Unit = {
println(findPairs(50))





}

















// @main
// def zad5 (n: Int): Unit = {
//     val table = List.range(2,n)filter(p => isPrime(p))
//     val result = for{
//       a <- 2 to 2*n
//       b <- 2 to 2*n
//       if isPrime(a) && isPrime(b) && a<b && a + b == 2*n
//     } yield (a,b)
//     result.toList
    
// }


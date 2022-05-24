import scala.annotation.tailrec

def reverse(str: String): String = {
    def helper(akum: String = "", str: String): String = str match{
        case "" => akum
        case _ => helper(s"${str.head}${akum}", str.tail)
    }
    helper("",str)
}
@main
def zadanie_06(arg: String): Unit = {
    println(reverse(arg))
}

def ciąg(n: Int): Int = {
    @tailrec
   def helper(x: Int, accum1: Int=2, accum2: Int=1): Int = x match{
       case 0 => accum1
       case 1 => accum2
       case _ => helper(x-1,accum2,accum2+accum1) 
   }
   helper(n)
}

@main
def zadanie_08(arg: Int): Unit = {
    println(ciąg(arg))
}

def tasuj(l1: List[Int], l2: List[Int]): List[Int] = {
    Nil
}

@main
def zadanie_09: Unit = {
    val lista1 = List(2, 4, 3, 5) // 2,3,4,5
    val lista2 = List(1, 2, 2, 3, 1, 5) //1,1,2,3,4,5

    tasuj(lista1, lista2) == List(1, 2, 3, 1, 4, 3, 5) // true
}
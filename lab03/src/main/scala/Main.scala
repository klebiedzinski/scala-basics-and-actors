
@main
def zad6: Unit = {
  
  def reverse(str: String): String = {
    
    val dlugosc = str.length()
    @scala.annotation.tailrec
    def tailrec(napis: String, akum: String): String = {
      if (akum.length() == dlugosc) akum
      else tailrec(napis.tail, napis.head + akum)
      
    }
    tailrec(str, "")

  }

  println(reverse("Karol"))
}

@main 
def zad7(arg: Int): Unit = {
   def IsPrime(x: Int): Boolean = {
   if (x==2) true
   else
     def helper(x: Int, akum: Int = 2): Boolean = {
      if (x % akum == 0) false
      if (akum * akum > x) true
      else helper(x,akum + 1)
     }
     helper(x)
    
  }
  println(IsPrime(arg))
}

@main
def zad8(arg: Int): Unit = {
  def ciag(n: Int): Int = {
    @scala.annotation.tailrec
    def tailrec(n: Int,akum1: Int,akum2: Int): Int = {
      if (n==0) akum1
      else tailrec(n-1,akum2,akum1+akum2)
    }
    tailrec(n,2,1)
  }
  println(ciag(arg))
  
}





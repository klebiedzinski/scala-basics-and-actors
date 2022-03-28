
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
    else {
      @scala.annotation.tailrec
      def tailrec(x: Int, akum: Int): Boolean = {
        if (x%akum == 0) false
        else if (akum*akum>x) true
        else tailrec(x,akum+1)
      }
      tailrec(x,2)
    }
    
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





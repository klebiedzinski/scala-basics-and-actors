
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
def zad8(arg: Int): Unit = {

}



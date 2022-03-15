val helloStr = "Hello, World!"

def parzysta(n: Int): Boolean = {
  return n%2==0
}
@main
def zadanie_02: Unit = {
  var a = 1
  var b = 2
  var c = 3
  if (parzysta(a)==true){
    println("Tak")
  } 
  else{
    println("Nie")
  }
  println(parzysta(b))
  println(parzysta(c))
  //println (s"parzysta(4) == ${parzysta(4)}") <-- 
}
def nwd(A:Int, B: Int): Int = {
  var a=A
  var b=B 
  while(a!=b){
    if(a>b){
      a-=b
    }
    else{
      b-=a
    } 
  }
  return a
}
@main
def zadanie_03: Unit = {
  println(nwd(10,2))
}



def pierwsza(n: Int): Boolean = {
 assert(n > 2)
 var dzielniki=1
 while (dzielniki==0){
   
 }
   
 
 

   
 
 

}
@main
def zadanie_04(liczba: Int): Unit = {
  
}



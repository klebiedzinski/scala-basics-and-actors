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
  
  var dzielniki=2
  var i=2
  while (dzielniki == 2 && i<n/2 ){
    if (n%i==0){
      dzielniki+=1
    }
    i+=1
  }
  dzielniki==2
}

@main 
def zadanie_04: Unit = {
  println(pierwsza(11))
}

def hipoteza(n: Int): Unit = {
    // znajdujemy i wypisujemy na konsoli wynik
   
   var help=0
   var i=2
   while (i<=n-i){
     if (pierwsza(i) && pierwsza(n-i)){
       println(s"$n==$i+${n-i}")
       
       i=n-i
       help+=1
     }
     i+=1
     
   }
   if (help==0) println("nie udalo sie znalesc")
   
   
  
}


@main
def zadanie_05(n: Int): Unit = {
    if (n>2 && parzysta(n)) hipoteza(n)
    else println("podano zla liczbe")
    //   TAK -> wywołujemy hipoteza(n)
    //   NIE -> kończymy z odpowiednim komunikatem.
}




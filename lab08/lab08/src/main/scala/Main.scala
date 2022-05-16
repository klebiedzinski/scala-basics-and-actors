

def MasterMind(secret: List[Int], guess: List[Int]): (Int, Int) = {
  (secret.zip(guess).count(p => p._1 == p._2),secret.intersect(guess).size - secret.zip(guess).count(p => p._1 == p._2))
}

@main
def zad_30: Unit ={
  val secret = List(1, 3, 2, 2, 4, 5)
  val guess  = List(2, 1, 2, 4, 7, 2)
  println(MasterMind(secret,guess))
 
}

def wynikZawodnika(wyniki: List[Any], imie: String, nazwisko: String): (Double, Double) = {
  println(wyniki.filter( list === (imie, nazwisko, _, _)))
  
}
@main
def zad_31: Unit = {
  val wyniki: List[List] = (
  ("Maciej","Maziuk",3,12),
  ("Maciej","Maziuk",6,6),
  ("Maksym","Malek",3,5),
  ("Maciej","Maziuk",1,20),
  ("Karol", "Lebiedzinski", 5,8),
  ("Karol", "Lebiedzinski", 5,20),
  )
  wynikZawodnika(wyniki,"Maksym","Malek")
}




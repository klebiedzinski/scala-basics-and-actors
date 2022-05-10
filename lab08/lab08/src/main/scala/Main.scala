

def MasterMind(secret: List[Int], guess: List[Int]): (Int, Int) = {
  (secret.zip(guess).count(p => p._1 == p._2),secret.intersect(guess).size - secret.zip(guess).count(p => p._1 == p._2))
}

@main
def zad_30: Unit ={
  val secret = List(1, 3, 2, 2, 4, 5)
  val guess  = List(2, 1, 2, 4, 7, 2)
  println(MasterMind(secret,guess))
 
}

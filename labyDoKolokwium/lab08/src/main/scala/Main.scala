
def FBlack(secret: List[Int], guess: List[Int]): Unit = {
  println(secret.zip(guess).filter(p => p._1 == p._2).size)
}
def FWhite(secret: List[Int], guess: List[Int]): Unit = {
  println(guess.distinct.count(p => (secret.find(el => el == p)) != None))
}
@main
def zad_30: Unit = {
  val secret = List(1, 3, 2, 2, 4, 5)
  val guess  = List(2, 1, 2, 4, 7, 2)
  FBlack(secret,guess)
  FWhite(secret,guess)

}


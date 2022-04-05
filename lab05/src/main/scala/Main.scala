import scala.annotation.tailrec
val helloStr = "Hello, World!"
// List .reverse
@main
def zadanie_15: Unit = {
  
  def oczyść[A](l: List[A]): List[A] = {
    @tailrec
    def tailrec[A](l: List[A],akum: List[A]): List[A] = l match{
      case Nil => akum
      case głowa1 :: ogon1 => ogon1 match {
        case Nil => akum :+ głowa1
        case głowa2 :: ogon2 if (głowa1==głowa2) => tailrec(ogon1, akum)
        case głowa2 :: ogon2 if (głowa1!=głowa2) => tailrec(ogon1, akum :+ głowa1)
      } 
    }
    tailrec(l,Nil)
}
    val lista = List(1, 1, 2, 4, 4, 4, 1, 3)
    oczyść(lista) // ==> List(1, 2, 4, 1, 3)
}

@main
def zadanie_16: Unit = {
  def skompresuj[A](l: List[A]): List[(A, Int)] = {
    def tailrec(l: List[A],akum: List[(A, Int)], licznik: Int): List[(A,Int)] = l match {
      case Nil => akum
      case głowa1 :: ogon1 => ogon1 match {
        case Nil => akum :+ (głowa1, licznik)
        case głowa2 :: ogon2 if (głowa1==głowa2) => tailrec(ogon1,akum,licznik + 1)
        case głowa2 :: ogon2 => tailrec(ogon1, akum :+ (głowa1,licznik), 1)
      }
    }
    tailrec(l,Nil,1)
  }
  val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
  println(skompresuj(lista)) // ==> List(('a', 2), ('b', 1), ('c', 3), ('a', 2), ('b', 1), ('d', 1))
}



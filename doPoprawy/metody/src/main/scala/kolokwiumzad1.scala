


@main
def zadanie_1_Kolokwium: Unit = {
   val input = io.Source
    .fromResource("test.txt")
    .getLines
    .toList
    .map(el => el.split(" ").toList)
    .drop(1)
    .map(el => el.drop(1).zipWithIndex)
    .map(el => el.map(p => (p._1,p._2+1)))
    .flatten
    .groupBy(el => el._2).toList
    .map(el => (el._1,el._2.map(p => p._1.toInt)))
    .map(el => (el._1,el._2.filter(p => p==1)))
    .filter(el => el._2.length>12)
    .map(el => (el._1,el._2.size))
    .sortBy(p => p._2).reverse
    .zipWithIndex
    .map(el =>(el._2+1,el._1._1,el._1._2))
    .foldLeft(List.empty[(Int,Int,Int)])((akum,curEl) => akum match{
        case List() => akum:+curEl
        case List(el:(Int,Int,Int)) => {
            if(el._3==curEl._3) akum:+(el._1,curEl._2,curEl._3)
            else akum:+curEl
        }
        case List(el1:(Int,Int,Int),el2:(Int,Int,Int)) => {
            if(el2._3==curEl._3) akum:+(el2._1,curEl._2,curEl._3)
            else akum:+curEl
        }
        case head :: (mid:+last) => {
            if (last._3==curEl._3) akum:+(last._1,curEl._2,curEl._3)
            else akum:+curEl
        }
    }).map(el => (el._1,el._2))
   
   
    
    println(input)
  
 
}
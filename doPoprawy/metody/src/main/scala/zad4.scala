/*
    Korzystając wyłącznie z mechanizmów kolekcji języka Scala znajdź kraj o najdłużej rosnącym wskaźniku LadderScore.
    Innymi słowy, korzystając z załączonych danych szukamy kraju, dla którego wskaźnik LadderScore najdłużej
    utrzymał „dobrą passę” (z roku na rok się zwiększał).

    Zwróć uwagę na to, że w danych mogą wystąpić „linie” z brakującymi danymi. Takie linie powinny zostać
    POMINIĘTE. Brakujące dane oznaczają, że w linii występują sekwencje postaci: ,,, przykładowo:

        Kosovo,2020,6.294,,0.792,,0.880,,0.910,0.726,0.201

    Linie takie, jako „niewiarygodne” należy pominąć (oczywiście nie zmieniając samego pliku danych)
    zanim program rozpocznie analizę.

    W razwiązaniu nie wolno uzywać zmiennych, ani konstrukcji imperatywnych, takich jak pętle
*/



    case class CountryData(
        CountryName : String,
        Year : String,
        LadderScore : String,
        LogGDPPerCapita : String,
        SocialSupport : String,
        HealthyLifeExpectancyAtBirth : String,
        FreedomToMakeLifeChoices : String,
        Generosity : String,
        PerceptionsOfCorruption : String,
        PositiveAffect : String,
        NegativeAffect : String
    )

@main
def zad4(): Unit = {
    val wyniki = io.Source
        .fromResource("world-happiness-report.csv")
        .getLines()
        .toList
        //...
    println(
    wyniki.map(el => el.split(",").toList)
        .filter(p => (p.exists(el => el=="")==false))
        .map(el => (el(0),el(2),el(1)))
        .groupBy(el => el._1)
        .toList
        .map(el => (el._1,el._2.map(p => (p._2,p._3))))
        .map(el => (el._1,el._2.sortBy(p => p(1))))
        .map(el => (el._1,el._2.map(p => p._1.toDouble)))
        //////////////////////////////bestPassa,curPassa,prevElement//////
        .map(el => (el._1,el._2.foldLeft((0,0,0.0))((akum,curEl) => (akum,curEl) match{
            case ((0,0,0.0),curElement) => (0,1,curElement)
            case ((bestPassa,curPassa,prevElement),curElement) => {
            if (prevElement<curElement) (bestPassa,curPassa+1,curElement)
            else {
                if (bestPassa<=curPassa) (curPassa,1,curElement)
                else (bestPassa,0,curElement)
            }
        }
        })
        ._1))
        .sortBy( el => el._2).reverse.head
        
    )
}



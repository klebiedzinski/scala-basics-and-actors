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





@main
def zad4: Unit = {
    val wyniki = io.Source
        .fromFile("world-happiness-report.csv")
        .getLines
        .toList
        .map(p => p.split(",").toList)
        .filter(!_.exists(p => p == ""))
        .map(p => (p(0),p(1),p(2)))
        .groupBy(_(0))
        .toList
        .map(p => ((p._1),(p._2.map(el => (el(1),el(2))))))
        .map(p => (p._1,p._2.sortWith((a,b) => a(0)<b(0))))
        .map( p => (p._1,p._2.map(el => el(1))))
        
        
        
    println(wyniki)
    // println(LadderScoreCount(List((2006,3.851), (2007,4.350), (2008,4.292), (2009,4.741), (2010,4.554), (2011,4.434), (2012,4.245), (2013,4.271), (2014,4.240), (2015,5.038), (2016,4.816), (2017,5.074), (2018,5.251), (2019,4.937), (2020,5.241))))
        
}


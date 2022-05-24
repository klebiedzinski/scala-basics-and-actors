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


   class CountryData(
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

// @main
//     def zad4: Unit = {
//         val wyniki = io.Source
//             .fromFile("world-happiness-report.csv")
//             .getLines
//             .toList
//             println(wyniki)

//             wyniki.map(p )
//     }


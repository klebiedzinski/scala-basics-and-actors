## Zadanie 1.

Liczenie wartości liczby __&pi;__ metodą Monte Carlo – wizualizacja:

![Pi metodą Monte Carlo](https://upload.wikimedia.org/wikipedia/commons/8/84/Pi_30K.gif)



Stwórz program który liczy wartość liczby __&pi;__ metodą Monte Carlo przy pomocy aktorów.
Aktor __manager__ tworzy _N_ aktorów __Pracowników__, którzy inicjalizują się z losowymi współrzędnymi _(x,y)_ o wartościach z kwadratu _[0,1] x [0,1]_.

Po zainicjalizowaniu wszystkich __Pracowników__ program wyznacza stosunek liczby tych, których współrzędne znajdują się wewnątrz okręgu o promieniu _1_ do liczby wszystkich __Pracowników__, pomnożony przez _4_. (pole całego koła o promieniu _1_ to __&pi;__, my operujemy na ćwiartce – stąd mnożenie przez 4).

Aby ustalić, czy dane współrzędne _(x,y)_ znajdują się wewnątrz koła o środku w punkcie _[0,0]_ i promieniu _1_ program powinien skorzystać z twierdzenia Pitagorasa.
Po zebraniu wyników __manager__ wylicza wartość, wysyła ją aktorowi __KumulatorWyniku__, a __Pracownicy__ są usuwani w celu przygotowania systemu do kolejnej „iteracji”
(tzn. __manager__ powinien ponownie wykonać powyższe działania).

Po wykonaniu _M_ iteracji, __manager__ wysyła komunikat __KumulatorowiWyniku__ aby ten podał średnią z wyników (wszystkie wyniki dodane, podzielone przez liczbę iteracji)

Parametry jakie powinien przyjmować __manager__ przy inicjalizacji to: liczba __Pracowników__ _N_, liczba iteracji _M_ jakie ma wykonać oraz referencja do aktora __KumulatorWyniku__.

## Zadanie 2.

Korzystając z aktorów zaimplementuj „symulator obsługi chorych”. Jego infrastruktura powinna zawierać aktora głównego, typu __Nadzorca__:

```scala
class Nadzorca extends Actor { ... }
```

__Nadzorca__ powinien być w stanie przyjąć komunikat

```scala
case class Zacznij(maks: Int)
```

gdzie _maks_ określa maksymalną liczbę możliwych do utworzenia __Szpitali__. Pacjentami w szpitalach będą aktorzy typu

```scala
class Obywatel extends Actor { ... }
```

Po otrzymaniu komunikatu _Zacznij_ aktor główny przechodzi do stanu, w którym gotowy jest na przyjmowanie kolejnych __Obywateli__ (przesyłanych mu przez program główny). Do każdego z nich wysyła komunikat

```scala
case object Badanie
```

na co __Obywatel__ odpowiada komunikatem

```scala
case class Wynik(wyn: Int)
```

gdzie parametr _wyn_ jest (pseudo)losowo wygenerowaną liczbą o wartości:

 - 0 – oznaczającą, że __Obywatel__ jest zdrowy
 - 1 – oznacza, że jest chory.

 Zdrowi __Obywatele__ powinni zakończyć swoją pracę (korzystając z komunikatu `PoinsonPill`), a __Nadzorca__ notuje sobie w swoim stanie ich liczbę.

__Obywateli__, którzy okażą się chorzy __Nadzorca__ przydziela do odpowiedniego aktora typu:

```scala
class Szpital extends Actor { ... }
```

używając komunikatu

```scala
case class Przyjmij(ob: ActorRef)
```

Nadzorca tworzy nowego aktora typu __Szpital__ jeżeli:

 - nie został utworzony wcześniej żaden __Szpital__;
 - żaden z szpitali nie zgodził się „przyjąć” obywatela;
 - liczba szpitali nie przekroczyła liczby „maks” (przesłanej do __Nadzorcy__ na początku w komunikacie _Zacznij_).

Szpital nie przyjmuje __Obywatela__ jeżeli liczba aktualnie hospitalizowanych wynosi _10_.

Jeżeli żaden __Szpital__ nie jest w stanie „przyjąć obywatela” oraz liczba utworzonych __Szpitali__ osiągnęła wartość _maks_, __Nadzorca__ powinien cyklicznie wysyłać komunikat `Przyjmij(ob: ActorRef)` do momentu aż któryś ze __Szpitali__ przyjmie „chorego”.

Szpital po otrzymaniu komunikatu `Przyjmij(ob: ActorRef)` sprawdza „stan zdrowia” wszystkich obywateli, których przyjął wcześniej. Poprzez komunikat:

```scala
case object Sprawdz
```

W odpowiedzi __Obywatel__ powinien wysłać do __Szpitala__ komunikat typu:

```scala
case class StanZdrowia(stan: Int)
```

gdzie _stan_ jest (pseudo)losowo wygenerowaną liczbą o wartości:

 - 0 – nadal chory
 - 1 – wyzdrowiał
 - 2 - zmarł

__Obywatele__, którzy odesłali _0_ nadal pozostają w szpitalu.  Ci, którzy odesłali wartość _1_ są odsyłani do __Nadzorcy__ jako wyleczeni (a tym samym usuwani ze stanu __Szpitala__), gdzie traktowani są identycznie jak ci, którzy rozpoznani zostali jako zdrowi na wstępie działania systemu, z wyjątkiem tego, że __Nadzorca__  dodaje ich liczbę do sumy wyleczonych.

__Obywatele__, którzy odesłali informację o swojej śmierci (wartość _2_) są usuwani ze szpitala i powinni zakończyć swoją pracę (z użyciem komunikatu `PoisonPill`).

Szpital odsyła komunikat

```scala
case class Odpowiedź(/*wymyśl atrybuty*/)
```

informujący __Nadzorcę__ czy przyjmuje nowego pacjenta
oraz przekazujący listę __Obywateli__ wyleczonych i liczbę tych, którzy zmarli.

Kiedy __Nadzorca__ skończy obsługiwać wszystkich obywateli, powinien wysyłać do wszystkich szpitali komunikat typu

```scala
case object StanSzpitala
```

w efekcie, którego __Szpital__ odsyła komunikat `Odpowiedź`. Sytuacja powinna się powtarzać dopóki __Nadzroca__ nie otrzyma informacji odnośnie wszystkich otrzymanych
__Obywateli__ przesłanych mu przez program główny.

Na koniec __Nadzorca__ powinien wyświetlić informację odnośnie liczby
zdrowych/ozdrowieńców oraz zmarłych i zakończyć pracę systemu aktorów.

### UWAGA!

W swoim rozwiązaniach nie używaj zmiennych, kolekcji mutowalnych, ani konstrukcji/pętli `while`.


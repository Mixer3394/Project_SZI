#Sztuczna Inteligencja
######Uniwersytet im. Adama Mickiewicza w Poznaniu

######Wydział Matematyki i Informatyki

##Inteligentny wózek widłowy

**Skład grupy:**
* [Kamil Legierski](https://github.com/Mixer3394) 
* [Mariusz Czeszejko-Sochacki](https://github.com/MariuszCz)
* [Jakub Janicki](https://github.com/Uncpy)
* [Rafał Pydyniak](https://github.com/RafalPydyniak)

**Podział prac:**
* Mariusz Czeszejko-Sochacki - Algorytmy genetyczne
* Jakub Janicki - Sieci neuronowe
* Rafał Pydyniak - Algorytmy uczenia symbolicznego
* Kamil Legierski - Uczenie drzew decyzyjnych

**Technologie:**
* JavaFX
* Java

**Strategia rozwiązań:**
* A*

**Krótki opis:**
   Program prezentuje podstawową funkcjonalność inteligentnego wózka widłowego. Wózek porusza się płynnie po mapie, wykorzystując w pełni algorytm A* do planowania swoich podróży. Algorytm działa bardzo oszczędnie, dzięki czemu mamy gwarancję przemieszczania się między punktami z możliwymi najniższymi kosztami. Mapa prezentuję magazyn, w którym znajduje się sześć sektorów różnej charakterystyki. Dodatkowo mapa została wyposażona w taśmę, która losowo przewija paczki z różnymi towarami, inteligentny wózek widłowy może pobrać wybraną paczkę, a następnie przetransportować ją w miejsce na nią przeznaczone.  
   
***Aktualne postępy:***

**21.03.2016:**
* Mapa
* UI
* Ruch po mapie
* Wstępna reprezentacja wiedzy
* Losowe generowanie skrzynek(paczek)
* Umiejętność transportu paczek


**25.04.2016:**
*	Gotowy algorytm A*, dzięki któremu wózek odnajduje najkrótszą drogę do paczki
*	Płynne działanie wózka – płynne poruszanie się po mapie 
*	Reprezentacja wiedzy dotycząca paczek
*	Nowy schemat mapy – przystosowany do algorytmu A*
*  Mapa 16x16

***Modyfikacje do 2 etapu:***
*  Obrót wózka
*  Plamy oleju
*  Inny sposób wypisywania ścieżki - akcje 

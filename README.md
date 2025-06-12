# Projekt-Zaliczenie-Aleksander-Maźnio
# System Zarządzania Flotą Pojazdów

## Opis
Program konsolowy pozwalający na zarządzanie flotą pojazdów.

## Funkcje
-Dodawanie nowych pojazdów
-Edycja istniejących pojazdów
-Usuwanie pojazdów z listy
-Wyświetlanie listy pojazdów

## Jak używać
1. Uruchom klasę `main` i wybierz opcje z menu:  
    - Dodaj pojazd  
    - Usuń pojazd 
    - Wypisz listę pojazdów  
    - Edytuj pojazd 
    - Zakończ
2. Przy wykonywaniu funkcji postępuj zgodnie z poleceniami wyświetlanymi w konsoli.
3. Program sam pilnuje unikalności ID pojazdów.

## Struktura
- `OdczytZapis` – klasa wykonuje odczyt i zapis pojazdów do pliku tekstowego 
- `DodajPojazd` – Klasa zapisująca pojazdy do odpowiednich list
- `Osobowka`, `Dostawczak` – rodzaje pojazdow 
- `UsunPojazd` – Klasa usuwająca z pliku pojazdy 


## Wymagania
- Java 8 lub wyższa  
- Konsola do obsługi interaktywnego menu

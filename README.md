# students-database
Projekt 2 – baza danych studentów

## 1 Podstawowe założenia
1. Projekt maven’owy z obługą minimalnej liczby zależności zewnętrznych
2. Paczka wynikowa: jar.
3. Zgodność źródeł oraz klas wynikowych z JAVA 11.
4. Aplikacja z interfejsem okienkowym SWING, wielowątkowa z obsługą puli wątków
5. Obsługa parametrów w wydzielonej klasie: liczba wątków w puli,
6. Kod obłożony testami jednostkowymi dostarczonymi w projekcie
7. Kod ma zawierać komentarze do klas, metod i zmiennych składowych
8. Do projektu należy dołączyć wygenerowany poprawny javadoc


## 2 Opis merytoryczny zadania
Aplikacja okienkowa napisana w SWING do przetwarzania danych o studentach należących do danej grupy i uczestniczących na zajęcia z danego przedmiotu.
Program ma umożliwiać:

1. Ewidencja studentów (lista) i wprowadzanie nowego studenta (imię, nazwisko, nr albumu), edycja, usuwanie.
2. Ewidencja grup (lista) i wprowadzanie nowej grupy (Kod grupy, specjalizacja, opis), edycja, usuwanie.
3. Przypisywanie studenta do grupy, usuwanie przypisania.
4. Ewidencja przedmiotów (np. Jezyk Java) oraz definiowanie nowego przedmiotu. Do przedmiotu przypisane kryteria oceny z maksymalną punktacją w ramach kryterium. Edycja i usuwanie.
5. Wprowadzanie punktów dla konkretnego studenta w ramach przedmiotu i kryterium z zachowaniem walidacji: punkty>=0 oraz punkty<=maks w danym kryterium, edycja, usuwanie.
6. Zapis do pliku całej zawartości wprowadzonych danych z użyciem DataOutputStream
7. Odczyt danych z pliku z użyciem DataInputStream
8. Wyszukiwanie i wyświetlanie danych o studentach, przedmiotach i zdobytych punktach.

Do programu należy dostarczyć testy sprawdzające poprawność działania poszczególnych funkcji programu.
Zakończony projekt ma składać się z:

1. Kod źródłowy projektu wraz z testami w strukturze projektu maven
2. Poprawnej dokumentacji javadoc
3. Dokumentu opisu ról w projekcie realizowanych przez poszczególnych członków zespołu

Ocena projektu zostanie dokonana na ostatnim laboratorium w ramach prezentacji
projektu wykonanej przez zespół projektowy.


Yuliia Loianich

	Stworzenie wstępnej architektury dla projektu i gita:
	https://github.com/ylwit/students-database
	
	Główna klasa:
		/students-database/src/main/java/pl/wit/studentsdatabase/MainApp.java

	Klasa zawierająca liczbę wątków:
		/students-database/src/main/java/pl/wit/studentsdatabase/ThreadsConfiguration.java
	
	Modele:
		/students-database/src/main/java/pl/wit/studentsdatabase/model/Group.java
		/students-database/src/main/java/pl/wit/studentsdatabase/model/Score.java
		/students-database/src/main/java/pl/wit/studentsdatabase/model/Student.java
		/students-database/src/main/java/pl/wit/studentsdatabase/model/Subject.java
	
	Klasa pilnująca indeksów modeli:
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/ObjectsIds.java	
	
	Repozytorium dotyczące Studenta i metody je obsługujące:
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/StudentDataAccess.java
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/StudentRepository.java
	
	Główny jframe + podział na zakładki dotyczące funkcjonalności (tu Emil też dodał jpanele do zakładek pozostałych niż Studebt):
		/students-database/src/main/java/pl/wit/studentsdatabase/view/MainView.java
	
	Panel do dodawania i wyszukiwania studentów:
		/students-database/src/main/java/pl/wit/studentsdatabase/view/StudentsPanelCreator.java

Tomasz Pogorzelski
	Wielowątkowość dla wielu metod w:
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/StudentRepository.java

Bartłomiej Perkowski
	Testy dla modeli:
		/students-database/src/test/java/pl/wit/studentsdatabase/model/GroupTest.java
		/students-database/src/test/java/pl/wit/studentsdatabase/model/ScoreTest.java
		/students-database/src/test/java/pl/wit/studentsdatabase/model/StudentTest.java
		/students-database/src/test/java/pl/wit/studentsdatabase/model/SubjectTest.java

	Testy klas repozytorium:
		/students-database/src/test/java/pl/wit/studentsdatabase/repository/GroupRepositoryTest.java
		/students-database/src/test/java/pl/wit/studentsdatabase/repository/StudentRepositoryTest.java
		
	Repozytorium dotyczące grup i metody je obsługujące
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/GroupDataAccess.java
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/GroupRepository.java
		
		
Emil Sell

	Plik readme
	
	Obługa pliku dotyczącego repozytorium:
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/FileHandler.java
	
	Repozytoria dla Score i Subject i metody je obsługujące:
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/ScoreDataAccess.java
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/ScoreRepository.java
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/SubjectDataAccess.java
		/students-database/src/main/java/pl/wit/studentsdatabase/repository/SubjectRepository.java
		
	Dodanie zakładek do MainView.java
		src/main/java/pl/wit/studentsdatabase/view/StudentsPanelCreatorV2.java
		src/main/java/pl/wit/studentsdatabase/view/SubjectsPanelCreator.java

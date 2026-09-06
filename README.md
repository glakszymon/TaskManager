# Task Manager CLI

Prosta, lekka aplikacja konsolowa (CLI) w języku Java przeznaczona do zarządzania listą zadań. Pozwala na dodawanie, edycję, usuwanie, zmianę statusu oraz filtrowanie zadań, które są automatycznie zapisywane w lokalnym pliku JSON.

---

## Funkcje

* **Dodawanie zadań** – szybkie dodawanie wpisów z domyślnym statusem `todo`.
* **Zmiana statusu** – płynne przełączanie stanu zadań na `in-progress` lub `done`.
* **Edycja nazwy** – aktualizacja treści istniejących zadań po ich identyfikatorze (`ID`).
* **Usuwanie** – kasowanie zadań wraz z automatycznym przeliczaniem indeksem ID.
* **Filtrowanie** – wyświetlanie pełnej listy zadań lub filtrowanie według statusu (`todo`, `in-progress`, `done`).
* **Brak zewnętrznych zależności** – własna implementacja parsera JSON bez użycia bibliotek zewnętrznych.

---

## Wymagania

* **Java 21** lub nowsza (projekt korzysta z nowszych funkcji języka, takich jak `SequencedCollection.getLast()` oraz uproszczonego wypisywania `IO.println`).

---

## Instalacja i uruchomienie

### Szybkie uruchomienie

Jeśli posiadasz już zbudowany plik `TaskManager.jar`, uruchom go w terminalu:

```bash
java -jar TaskManager.jar <komenda> [argumenty]

```

---

## Dostępne komendy

| Komenda | Argumenty | Opis | Przykład |
| --- | --- | --- | --- |
| `add` | `"nazwa zadania"` | Dodaje nowe zadanie do listy | `java -jar TaskManager.jar add "Kupić mleko"` |
| `update` | `[id]` `"nowa nazwa"` | Aktualizuje nazwę zadania o podanym ID | `java -jar TaskManager.jar update 0 "Kupić mleko i chleb"` |
| `delete` | `[id]` | Usuwa zadanie o podanym ID | `java -jar TaskManager.jar delete 1` |
| `mark-in-progress` | `[id]` | Zmienia status zadania na `in-progress` | `java -jar TaskManager.jar mark-in-progress 0` |
| `mark-done` | `[id]` | Zmienia status zadania na `done` | `java -jar TaskManager.jar mark-done 0` |
| `list` | *brak* | Wypisuje wszystkie zadania | `java -jar TaskManager.jar list` |
| `list` | `[status]` | Wypisuje zadania o podanym statusie | `java -jar TaskManager.jar list in-progress` |
| `--help` | *brak* | Wyświetla instrukcję pomocy | `java -jar TaskManager.jar --help` |

---

## Przykładowy przepływ pracy

```bash
# Dodanie zadań
java -jar TaskManager.jar add "Zaplanować tydzień"
java -jar TaskManager.jar add "Zrobić zakupy"

# Rozpoczęcie pracy nad zadaniem (ID: 0)
java -jar TaskManager.jar mark-in-progress 0

# Wyświetlenie zadań w trakcie realizacji
java -jar TaskManager.jar list in-progress

# Oznaczenie zadania jako zakończone
java -jar TaskManager.jar mark-done 0

# Wyświetlenie pełnej listy
java -jar TaskManager.jar list

```

---

## Kompilacja i budowanie

Jeśli chcesz samodzielnie skompilować projekt i zbudować plik `.jar`:

### Metoda 1: Użycie IntelliJ IDEA (Zalecane)

1. Otwórz projekt w **IntelliJ IDEA**.
2. Wejdź w **File** $\rightarrow$ **Project Structure...** (`Ctrl + Alt + Shift + S`).
3. Wybierz zakładkę **Artifacts** $\rightarrow$ kliknij **`+`** $\rightarrow$ **JAR** $\rightarrow$ **From modules with dependencies...**.
4. W polu **Main Class** wybierz klasę `Main` i zatwierdź przyciskiem **OK**.
5. W menu głównym wybierz **Build** $\rightarrow$ **Build Artifacts...** $\rightarrow$ **Build**.
6. Plik `.jar` pojawi się w katalogu `out/artifacts/TaskManager_jar/`.

### Metoda 2: Kompilacja z poziomu terminala (CLI)

```bash
# 1. Kompilacja klas do folderu out
javac -d out Main.java Models/*.java

# 2. Stworzenie pliku manifestu
echo "Main-Class: Main" > manifest.txt

# 3. Spakowanie do pliku JAR
jar cvfm TaskManager.jar manifest.txt -C out .

```

---

## Struktura projektu

* `Main.java` – główny punkt wejścia aplikacji, obsługa komend i argumentów konsoli.
* `Models/Task.java` – klasa reprezentująca model zadania (`Id`, `Name`, `Status`).
* `Models/Converter.java` – autorski konwerter danych zmieniający obiekty Java na format JSON i z powrotem.
* `Models/DataComunicator.java` – obsługa operacji wejścia/wyjścia na pliku danych.

---

## Licencja

Ten projekt jest udostępniany na licencji [MIT](https://www.google.com/search?q=LICENSE).

```

```
# RestApi
Jest to aplikacja spring bootowa. Aby uruchomic projekt, nalezy zaciagnac mavenem wszystkie zaleznosci i uruchomić klasę ApplicationStart. Po uruchomieniu aplikacji mozemy z linii komend wywołać curl localhost:8080/users/octocat aby odpytać serwis o danego użytkownika, wynik zwracany jest w postaci JSONa, lub jeżeli wystąpi błąd, to otrzymujemy odpowiedni komunikat. Baza danych to h2, aby się do niej dostać, wchodzimy na http://localhost:8080/console/ i jako jdbc url wpisujemy "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", nazwa użytkownika: sa, hasło puste. Aplikacja zapisuje dane w tabeli API_COUNT_FOR_LOGIN.

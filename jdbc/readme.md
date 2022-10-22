# Projekt ćwiczeniowy dla bloku JDBC

## Warsztaty 1-4 (baza danych Sakila)

Aby skonfigurować połączenie z bazą danych, utwórz plik `src/main/resources/application.properties` o treści:

```properties
jdbc.url=jdbc:mysql://root:example@localhost:6306/sakila
```

Pamiętaj o ustawieniu poprawnej nazwy użytkownika (jeśli jest inny niż `root`), portu (jeśli jest inny niż `6306`;
domyślny dla MySQL port to `3306`) oraz hasła (w powyższym przykładzie: `example`).

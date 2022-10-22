package pl.sdacademy.java.jdbc.workshop3;

import pl.sdacademy.java.jdbc.model.Film;
import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
    * Użyj przygotowanej klasy `Film`.
    * Zaimplementuj metodę ładującą listę filmów według fragmentu tytułu.
    * Wielkość znaków nie powinna mieć znaczenia.
    * W przypadku podania blanka, czyli ciągu znaków, który jest pusty, złożony
      z samych białych znaków (spacje) lub null, metoda ta powinna zwracać pustą listę.
      Lista powinna być posortowana według tytułu.
 */
public class Workshop3 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj fragment tytułu filmu:");
        final var query = new Scanner(System.in).nextLine();

        final var filmStrings = getFilms(jdbcUrl, query).stream()
                .map(Film::toString)
                .collect(Collectors.toList());

        System.out.printf("Filmy pasujące do frazy '%s':\n\n%s", query, String.join("\n", filmStrings));
    }

    public static List<Film> getFilms(String jdbcUrl, String query) {
        throw new UnsupportedOperationException("TODO");
    }
}

package pl.sdacademy.java.jdbc.workshop1;

import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.util.List;

/*
    * Zaimportuj bazę danych Sakila (https://dev.mysql.com/doc/sakila/en/)
    * Korzystając z tabel `city` oraz `country` przygotuj zapytanie pobierające listę nazw
      miast znajdujących się w Polsce.
    * Zaimplementuj metodę `getPolishCities(String)`.
 */
public class Workshop1 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        final var polishCities = getPolishCities(jdbcUrl);
        System.out.printf("Miasta Polski:\n\n%s", String.join("\n", polishCities));
    }

    public static List<String> getPolishCities(String jdbcUrl) {
        throw new UnsupportedOperationException("TODO");
    }
}

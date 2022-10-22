package pl.sdacademy.java.jdbc.workshop2;

import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.util.List;
import java.util.Scanner;

/*
    * Zmodyfikuj poprzedni program tak, aby ładować miasta według podanego przez użytkownika kraju.
    * Co się stanie, jeśli użytkownik poda tekst `Narnia ' OR 'x' = 'x`?
    * Jak można temu zapobiec?
 */
public class Workshop2 {

    public static void main(String[] args) {
        final String jdbcUrl = ApplicationPropertiesProvider.getApplicationProperties().getProperty("jdbc.url");

        System.out.println("Podaj nazwę kraju z którego załadować miasta:");
        final var countryName = new Scanner(System.in).nextLine();

        final var cities = getCities(jdbcUrl, countryName);
        System.out.printf("Miasta w '%s':\n\n%s", countryName, String.join("\n", cities));
    }

    public static List<String> getCities(String jdbcUrl, String countryName) {
        throw new UnsupportedOperationException("TODO");
    }
}

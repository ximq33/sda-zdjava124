package pl.sdacademy.java.jdbc.workshop1;

import pl.sdacademy.java.jdbc.utils.ApplicationPropertiesProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
       final List <String> cities = new LinkedList<>();
        try (final Connection connection = DriverManager.getConnection(jdbcUrl)) {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT city FROM city JOIN country ON country.country_id = city.country_id WHERE country = 'Poland'");
            while (resultSet.next()) {
                cities.add(resultSet.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}

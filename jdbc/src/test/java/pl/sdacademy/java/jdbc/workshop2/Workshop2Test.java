package pl.sdacademy.java.jdbc.workshop2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.jdbc.tests.TestDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop2Test {

    @BeforeAll
    static void setUp() throws Exception {
        TestDbHelper.importSakilaDatabase();
    }

    @AfterAll
    static void tearDown() {
        TestDbHelper.cleanUp();
    }

    @Test
    void shouldReturnPolishCities() {
        //WHEN
        final var cities = Workshop2.getCities(TestDbHelper.JDBC_URL, "Poland");

        //THEN
        assertThat(cities).containsExactly(
                "Bydgoszcz",
                "Czestochowa",
                "Jastrzebie-Zdrj",
                "Kalisz",
                "Lublin",
                "Plock",
                "Tychy",
                "Wroclaw"
        );
    }

    @Test
    void shouldReturnGermanCities() {
        //WHEN
        final var cities = Workshop2.getCities(TestDbHelper.JDBC_URL, "Germany");

        //THEN
        assertThat(cities).containsExactly(
                "Duisburg",
                "Erlangen",
                "Halle/Saale",
                "Mannheim",
                "Saarbrcken",
                "Siegen",
                "Witten"
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Narnia", "Narnia' or 'x'='x"})
    @NullAndEmptySource
    void shouldReturnEmptyList(String countryName) {
        //WHEN
        final var cities = Workshop2.getCities(TestDbHelper.JDBC_URL, countryName);

        //THEN
        assertThat(cities).isEmpty();
    }
}

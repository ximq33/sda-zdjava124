package pl.sdacademy.java.jdbc.workshop3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.jdbc.model.Film;
import pl.sdacademy.java.jdbc.tests.TestDbHelper;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop3Test {

    @BeforeAll
    static void setUp() throws Exception {
        TestDbHelper.importSakilaDatabase();
    }

    @AfterAll
    static void tearDown() {
        TestDbHelper.cleanUp();
    }

    @Test
    void shouldReturnAlienFilms() {
        //WHEN
        final var films = Workshop3.getFilms(TestDbHelper.JDBC_URL, "alien");

        //THEN
        assertThat(films).extracting(Film::getTitle).containsExactly(
            "ALIEN CENTER",
            "DESIRE ALIEN",
            "HOBBIT ALIEN"
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Narnia", "Narnia' or 'x'='x", " "})
    @NullAndEmptySource
    void shouldReturnEmptyList(String query) {
        //WHEN
        final var films = Workshop3.getFilms(TestDbHelper.JDBC_URL, query);

        //THEN
        assertThat(films).isEmpty();
    }

}

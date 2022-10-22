package pl.sdacademy.java.jdbc.workshop1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sdacademy.java.jdbc.tests.TestDbHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Workshop1Test {

    @BeforeEach
    void setUp() throws Exception {
        TestDbHelper.importSakilaDatabase();
    }

    @AfterEach
    void tearDown() {
        TestDbHelper.cleanUp();
    }

    @Test
    void shouldReturnPolishCities() {
        //WHEN
        final List<String> polishCities = Workshop1.getPolishCities(TestDbHelper.JDBC_URL);

        //THEN
        assertThat(polishCities).containsExactly(
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
}

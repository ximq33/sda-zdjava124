package pl.sdacademy.java.jdbc.homework1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.sdacademy.java.jdbc.model.Actor;
import pl.sdacademy.java.jdbc.tests.TestDbHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class Homework1Test {

    @BeforeAll
    static void setUp() throws Exception {
        TestDbHelper.importSakilaDatabase();
    }

    @AfterAll
    static void tearDown() {
        TestDbHelper.cleanUp();
    }

    @Test
    void shouldReturnActorsByBerFragment() {
        //WHEN
        final var actors = Homework1.getActors(TestDbHelper.JDBC_URL, "berr");

        //THEN
        assertThat(actors).extracting(Actor::getFirstName, Actor::getLastName).containsExactly(
            tuple("CHRISTOPHER", "BERRY"),
            tuple("HENRY", "BERRY"),
            tuple("KARL", "BERRY")
        );
    }

    @Test
    void shouldReturnActorsByCamerFragment() {
        //WHEN
        final var actors = Homework1.getActors(TestDbHelper.JDBC_URL, "camer");

        //THEN
        assertThat(actors).extracting(Actor::getFirstName, Actor::getLastName).containsExactly(
            tuple("CAMERON", "STREEP"),
            tuple("CAMERON", "WRAY"),
            tuple("CAMERON", "ZELLWEGER")
        );
    }

    @Test
    void shouldReturnActorsFromAlienFilms() {
        //WHEN
        final var actors = Homework1.getActors(TestDbHelper.JDBC_URL, "alien");

        //THEN
        assertThat(actors).extracting(Actor::getFirstName, Actor::getLastName).containsExactly(
                tuple("ANGELINA", "ASTAIRE"),
                tuple("VIVIEN", "BERGEN"),
                tuple("LAURA", "BRODY"),
                tuple("JOHNNY", "CAGE"),
                tuple("DARYL", "CRAWFORD"),
                tuple("SIDNEY", "CROWE"),
                tuple("JULIANNE", "DENCH"),
                tuple("BURT", "DUKAKIS"),
                tuple("ROCK", "DUKAKIS"),
                tuple("CATE", "HARRIS"),
                tuple("MENA", "HOPPER"),
                tuple("WARREN", "JACKMAN"),
                tuple("ELVIS", "MARX"),
                tuple("TOM", "MCKELLEN"),
                tuple("KENNETH", "PALTROW"),
                tuple("DUSTIN", "TAUTOU"),
                tuple("WALTER", "TORN"),
                tuple("RENEE", "TRACY"),
                tuple("REESE", "WEST"),
                tuple("HUMPHREY", "WILLIS")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"Narnia", "Narnia' or 'x'='x", " ", "a", "al"})
    @NullAndEmptySource
    void shouldReturnEmptyList(String query) {
        //WHEN
        final var films = Homework1.getActors(TestDbHelper.JDBC_URL, query);

        //THEN
        assertThat(films).isEmpty();
    }
}

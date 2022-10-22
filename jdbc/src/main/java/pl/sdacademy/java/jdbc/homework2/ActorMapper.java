package pl.sdacademy.java.jdbc.homework2;

import pl.sdacademy.java.jdbc.model.Actor;

import java.util.List;

public interface ActorMapper {

    //TODO dodaj brakujące adnotacje MyBatis
    List<Actor> getActors(String query);
}

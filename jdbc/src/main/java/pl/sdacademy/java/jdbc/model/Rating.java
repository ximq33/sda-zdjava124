package pl.sdacademy.java.jdbc.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum Rating {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String code;

    Rating(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Optional<Rating> getByCode(String code) {
        return Stream.of(Rating.values()).filter(v -> v.code.equals(code)).findAny();
    }
}

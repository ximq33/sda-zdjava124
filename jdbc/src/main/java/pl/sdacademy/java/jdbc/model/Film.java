package pl.sdacademy.java.jdbc.model;

public final class Film {
    private final String title;
    private final String description;
    private final int releaseYear;
    private final Rating rating;

    public Film(String title, String description, int releaseYear, Rating rating) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", rating=" + rating +
                '}';
    }
}

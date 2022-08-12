package models.infrastructure.headers;

public enum HeaderNames {
    ACCEPT("Accept"),
    CONTENT_TYPE("Content-Type");

    private String name;

    HeaderNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

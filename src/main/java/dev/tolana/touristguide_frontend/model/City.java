package dev.tolana.touristguide_frontend.model;

public enum City {
    COPENHAGEN("Copenhagen"),
    VANLØSE("Vanløse"),
    ÅRHUS("Århus");

    private final String name;

    City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package dev.tolana.touristguide_frontend.model;

public enum Tag {
    MUSEUM("Museum"),
    FREE("Free"),
    PAID("Paid"),
    FAMILY_FRIENDLY("Family Friendly"),
    HISTORICAL("Historical"),
    RELIGION("Religion"),
    AMUSEMENT_PARK("Amusement Park"),
    FOOD("Food"),
    FINE_DINING("Fine Dining"),
    NATURE("Nature"),
    ACTIVE("Active");


    private final String name;

    Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

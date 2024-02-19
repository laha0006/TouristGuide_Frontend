package dev.tolana.touristguide_frontend.model;

public enum Tag {
    MUSEUM("Museum"),
    FREE("Free"),
    PAID("Paid"),
    FAMILY_FRIENDLY("Family Friendly"),
    HISTORICAL("Historical"),
    RELIGION("Religion"),
    AMUSEMENT_PARK("Amusement Park");



    private final String name;

    Tag(String name) {
        this.name = name;
    }
}

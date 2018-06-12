package com.example.demo.Model.BuildingType;

public enum BuildingTypes {
    CASTLE("castle", "Castle"),
    ARCHERY("archery", "Archery Range"),
    BARRACKS("barracks", "Barracks"),
    STABLES("stables", "Stables"),
    HOUSE("house", "House");

    public final String name;
    public final String displayName;

    BuildingTypes(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}

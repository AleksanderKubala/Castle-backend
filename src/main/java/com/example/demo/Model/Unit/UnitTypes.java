package com.example.demo.Model.Unit;

public enum UnitTypes {
    ARCHER("archer", "Archer"),
    PIKEMAN("pikeman", "Pikeman"),
    CAVALRY("cavalry", "Cavalry");

    public final String name;
    public final String displayName;

    UnitTypes(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}

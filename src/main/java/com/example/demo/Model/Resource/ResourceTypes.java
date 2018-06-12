package com.example.demo.Model.Resource;

public enum ResourceTypes {
    WOOD("wood"),
    STONE("stone"),
    GOLD("gold"),
    FOOD("food"),
    CAPACITY("capacity"),
    HAPPINESS("happiness");

    public final String name;

    ResourceTypes(String name) {
        this.name = name;
    }
}

package com.example.demo.Model.Resource;

public enum ResourceTypes {
    WOOD("wood", 100, false),
    STONE("stone", 100, false),
    GOLD("gold", 100, false),
    FOOD("food", 100, false),
    CAPACITY("capacity", 0, true),
    HAPPINESS("happiness", 50, false);

    public final String name;
    public final Integer startingVolume;
    public final Boolean poolResource;

    ResourceTypes(String name, Integer startingVolume, Boolean poolResource) {
        this.name = name;
        this.startingVolume = startingVolume;
        this.poolResource = poolResource;
    }
}

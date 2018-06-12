package com.example.demo.Model.WorldStructureType;

public enum WorldStructureTypes {
    CITY("city", "City"),
    TREE1("tree1", "Tree"),
    TREE2("tree2", "Tree"),
    TREE3("tree3", "Tree");

    public final String name;
    public final String displayName;

    WorldStructureTypes(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }
}

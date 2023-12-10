package com.labwork01.app.flower;

public enum FlowerType {
    Trees("Trees"),
    Aquatic("Aquatic"),
    Grasses("Grasses"),
    Bulbous("Bulbous"),
    Wildflowers("Wildflowers");
    private final String name;

    FlowerType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static FlowerType fromString(String name) {
        for (FlowerType flowerType : FlowerType.values()) {
            if (flowerType.name.equalsIgnoreCase(name)) {
                return flowerType;
            }
        }
        return null;
    }
}

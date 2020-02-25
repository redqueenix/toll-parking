package com.assignment.model.enums;

/**
 * Enum for car types
 */
public enum CarType {
    GASOLINE("Gasoline"),
    ELECTRIC_20("Electric 20KW"),
    ELECTRIC_50("Electric 50KW");

    String description;

    CarType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

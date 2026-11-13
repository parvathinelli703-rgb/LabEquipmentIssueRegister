package com.lab.model;

public class Equipment {
    private int equipmentId;
    private String name;
    private int totalQuantity;
    private int availableQuantity;
    private String description;

    public Equipment() {}

    // Getters and Setters
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
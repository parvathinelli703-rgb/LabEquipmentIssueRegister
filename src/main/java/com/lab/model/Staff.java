package com.lab.model;

public class Staff {
    private String staffId;
    private String name;
    private String password;
    private String role;

    public Staff() {}

    public Staff(String staffId, String name, String password, String role) {
        this.staffId = staffId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
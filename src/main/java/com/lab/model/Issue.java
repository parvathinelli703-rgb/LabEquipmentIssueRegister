package com.lab.model;

import java.sql.Date;

public class Issue {
    private int issueId;
    private String studentRoll;
    private String studentName;
    private int equipmentId;
    private String equipmentName;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private String conditionOnReturn;
    private String status;
    private String issuedBy;

    public Issue() {}

    // Getters and Setters
    public int getIssueId() { return issueId; }
    public void setIssueId(int issueId) { this.issueId = issueId; }
    public String getStudentRoll() { return studentRoll; }
    public void setStudentRoll(String studentRoll) { this.studentRoll = studentRoll; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
    public Date getIssueDate() { return issueDate; }
    public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public String getConditionOnReturn() { return conditionOnReturn; }
    public void setConditionOnReturn(String conditionOnReturn) { this.conditionOnReturn = conditionOnReturn; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getIssuedBy() { return issuedBy; }
    public void setIssuedBy(String issuedBy) { this.issuedBy = issuedBy; }
}
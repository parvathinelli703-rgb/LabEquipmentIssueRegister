package com.lab.dao;

import com.lab.model.Issue;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {

    public boolean issueEquipment(Issue issue) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Check available quantity
            EquipmentDAO eqDao = new EquipmentDAO();
            var eq = eqDao.getEquipmentById(issue.getEquipmentId());
            if (eq == null || eq.getAvailableQuantity() <= 0) {
                return false;
            }

            // Insert issue record
            String sql = "INSERT INTO issue (student_roll, student_name, equipment_id, issue_date, due_date, status, issued_by) VALUES (?, ?, ?, ?, ?, 'Issued', ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, issue.getStudentRoll());
            ps.setString(2, issue.getStudentName());
            ps.setInt(3, issue.getEquipmentId());
            ps.setDate(4, issue.getIssueDate());
            ps.setDate(5, issue.getDueDate());
            ps.setString(6, issue.getIssuedBy());
            ps.executeUpdate();

            // Decrease available quantity
            eqDao.updateAvailableQuantity(issue.getEquipmentId(), -1);

            con.commit();
            return true;
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (Exception e) {}
        }
        return false;
    }

    public boolean recordReturn(int issueId, Date returnDate, String condition) {
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            // Get issue details
            String getSql = "SELECT equipment_id, status FROM issue WHERE issue_id = ?";
            PreparedStatement getPs = con.prepareStatement(getSql);
            getPs.setInt(1, issueId);
            ResultSet rs = getPs.executeQuery();
            if (!rs.next() || !"Issued".equals(rs.getString("status"))) {
                return false;
            }
            int equipmentId = rs.getInt("equipment_id");

            // Update issue
            String updateSql = "UPDATE issue SET return_date = ?, condition_on_return = ?, status = ? WHERE issue_id = ?";
            PreparedStatement ps = con.prepareStatement(updateSql);
            ps.setDate(1, returnDate);
            ps.setString(2, condition);
            String status = "Damaged".equalsIgnoreCase(condition) ? "Damaged" : "Returned";
            ps.setString(3, status);
            ps.setInt(4, issueId);
            ps.executeUpdate();

            // Increase available quantity only if not damaged (or still increase, depending on policy)
            // Here we increase even for damaged (you can change this logic)
            new EquipmentDAO().updateAvailableQuantity(equipmentId, 1);

            con.commit();
            return true;
        } catch (Exception e) {
            try { if (con != null) con.rollback(); } catch (Exception ex) {}
            e.printStackTrace();
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (Exception e) {}
        }
        return false;
    }

    public List<Issue> getAllIssued() {
        return getIssuesByStatus("Issued");
    }

    public List<Issue> getOverdue() {
        List<Issue> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT i.*, e.name AS equipment_name FROM issue i JOIN equipment e ON i.equipment_id = e.equipment_id " +
                    "WHERE i.status = 'Issued' AND i.due_date < CURDATE() ORDER BY i.due_date";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapIssue(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Issue> getDamaged() {
        return getIssuesByStatus("Damaged");
    }

    public List<Issue> getIssuesByStatus(String status) {
        List<Issue> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT i.*, e.name AS equipment_name FROM issue i JOIN equipment e ON i.equipment_id = e.equipment_id " +
                    "WHERE i.status = ? ORDER BY i.issue_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapIssue(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Issue> getAllIssues() {
        List<Issue> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT i.*, e.name AS equipment_name FROM issue i JOIN equipment e ON i.equipment_id = e.equipment_id ORDER BY i.issue_date DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapIssue(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Issue mapIssue(ResultSet rs) throws SQLException {
        Issue issue = new Issue();
        issue.setIssueId(rs.getInt("issue_id"));
        issue.setStudentRoll(rs.getString("student_roll"));
        issue.setStudentName(rs.getString("student_name"));
        issue.setEquipmentId(rs.getInt("equipment_id"));
        issue.setEquipmentName(rs.getString("equipment_name"));
        issue.setIssueDate(rs.getDate("issue_date"));
        issue.setDueDate(rs.getDate("due_date"));
        issue.setReturnDate(rs.getDate("return_date"));
        issue.setConditionOnReturn(rs.getString("condition_on_return"));
        issue.setStatus(rs.getString("status"));
        issue.setIssuedBy(rs.getString("issued_by"));
        return issue;
    }
}
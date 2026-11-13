package com.lab.dao;

import com.lab.model.Staff;
import java.sql.*;

public class StaffDAO {
    public boolean registerStaff(Staff staff) {
        try (Connection con = DBConnection.getConnection()) {
            // Check if staff_id already exists
            String checkSql = "SELECT staff_id FROM staff WHERE staff_id = ?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, staff.getStaffId());
            ResultSet rs = checkPs.executeQuery();
            if (rs.next()) {
                return false; // Already exists
            }

            // Insert new staff
            String sql = "INSERT INTO staff (staff_id, name, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, staff.getStaffId());
            ps.setString(2, staff.getName());
            ps.setString(3, staff.getPassword());
            ps.setString(4, staff.getRole() != null ? staff.getRole() : "LabAssistant");

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public Staff authenticate(String staffId, String password) {
        Staff staff = null;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM staff WHERE staff_id = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, staffId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                staff = new Staff();
                staff.setStaffId(rs.getString("staff_id"));
                staff.setName(rs.getString("name"));
                staff.setPassword(rs.getString("password"));
                staff.setRole(rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }
}
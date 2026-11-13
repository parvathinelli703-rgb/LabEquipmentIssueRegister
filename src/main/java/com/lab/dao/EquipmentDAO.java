package com.lab.dao;

import com.lab.model.Equipment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO {

    public List<Equipment> getAllEquipment() {
        List<Equipment> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM equipment ORDER BY name";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipment eq = new Equipment();
                eq.setEquipmentId(rs.getInt("equipment_id"));
                eq.setName(rs.getString("name"));
                eq.setTotalQuantity(rs.getInt("total_quantity"));
                eq.setAvailableQuantity(rs.getInt("available_quantity"));
                eq.setDescription(rs.getString("description"));
                list.add(eq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Equipment getEquipmentById(int id) {
        Equipment eq = null;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM equipment WHERE equipment_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                eq = new Equipment();
                eq.setEquipmentId(rs.getInt("equipment_id"));
                eq.setName(rs.getString("name"));
                eq.setTotalQuantity(rs.getInt("total_quantity"));
                eq.setAvailableQuantity(rs.getInt("available_quantity"));
                eq.setDescription(rs.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eq;
    }

    public boolean updateAvailableQuantity(int equipmentId, int change) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE equipment SET available_quantity = available_quantity + ? WHERE equipment_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, change);
            ps.setInt(2, equipmentId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Equipment> searchEquipment(String keyword) {
        List<Equipment> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM equipment WHERE name LIKE ? OR description LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Equipment eq = new Equipment();
                eq.setEquipmentId(rs.getInt("equipment_id"));
                eq.setName(rs.getString("name"));
                eq.setTotalQuantity(rs.getInt("total_quantity"));
                eq.setAvailableQuantity(rs.getInt("available_quantity"));
                eq.setDescription(rs.getString("description"));
                list.add(eq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
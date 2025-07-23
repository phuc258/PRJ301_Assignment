/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.InventoryLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author toila
 */
public class InventoryLogDAO {
    // Không cần constructor có conn
    public InventoryLogDAO() {}

    public void insertLog(InventoryLog log) throws Exception {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "INSERT INTO inventory_logs (book_id, action,quantity, note,admin_id) VALUES (?, ?, ?, ?,1)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, log.getBookId());
            ps.setString(2, log.getAction());
            ps.setInt(3, log.getQuantity());
            ps.setString(4, log.getNote());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi ghi log tồn kho: " + e.getMessage());
        } finally {
            if (cn != null) cn.close();
        }
    }

    public ArrayList<InventoryLog> getLogsByBookId(int bookId) throws Exception {
        ArrayList<InventoryLog> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            String sql = "SELECT il.* " +
                         "FROM inventory_logs il " +
                         " " +
                         "WHERE il.book_id = ? ORDER BY il.timestamp DESC";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                InventoryLog log = new InventoryLog();
                log.setId(rs.getInt("id"));
                log.setBookId(rs.getInt("book_id"));
                log.setAction(rs.getString("action"));
                log.setTimestamp(rs.getString("timestamp"));
                log.setNote(rs.getString("note"));
                log.setQuantity(rs.getInt("quantity"));
                list.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi lấy lịch sử tồn kho: " + e.getMessage());
        } finally {
            if (cn != null) cn.close();
        }

        return list;
    }
}

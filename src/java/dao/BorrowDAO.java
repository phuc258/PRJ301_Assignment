/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BorrowRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author SE190585
 */
public class BorrowDAO {

    public void insertBookRequest(int bookId, int userId) throws SQLException, ClassNotFoundException {
        Connection cn = null;
        PreparedStatement st = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO book_requests (book_id, user_id, request_date, status) VALUES (?, ?, GETDATE(), 'Pending')";
                st = cn.prepareStatement(sql);
                st.setInt(1, bookId);
                st.setInt(2, userId);
                st.executeUpdate();
            }
        } finally {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
    }

    public List<BorrowRequest> getRequestsByUser(int userId) throws SQLException, ClassNotFoundException {
        List<BorrowRequest> list = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT br.id, u.name, b.title, br.status, br.request_date "
                        + "FROM borrow_records br "
                        + "JOIN users u ON br.user_id = u.id "
                        + "JOIN books b ON br.book_id = b.id "
                        + "WHERE br.user_id = ? "
                        + "ORDER BY br.request_date DESC";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    BorrowRequest req = new BorrowRequest();
                    req.setId(rs.getInt("id"));
                    req.setUserName(rs.getString("name"));
                    req.setBookTitle(rs.getString("title"));
                    req.setStatus(rs.getString("status"));
                    req.setRequestDate(rs.getString("request_date"));
                    list.add(req);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public boolean isBookAlreadyBorrowed(int userId, int bookId) throws SQLException, ClassNotFoundException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) FROM book_requests WHERE user_id = ? AND book_id = ? AND status IN ('pending', 'approved')";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, bookId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = rs.getInt(1) > 0;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        }

        return result;
    }

}

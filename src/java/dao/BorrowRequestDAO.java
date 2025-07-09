/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BorrowRequest;
import mylib.DBUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toila
 */
public class BorrowRequestDAO {

    private Connection conn;

    public BorrowRequestDAO(Connection conn) {
        this.conn = conn;
    }

   

    public ArrayList<BorrowRequest> getAllRequests() throws Exception {
        ArrayList<BorrowRequest> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT br.id, u.name AS userName, b.title AS bookTitle, br.status, br.request_date "
                + "FROM book_requests br "
                + "JOIN users u ON br.user_id = u.id "
                + "JOIN books b ON br.book_id = b.id";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {                    
                     BorrowRequest r = new BorrowRequest();
                r.setId(rs.getInt("id"));
                r.setUserName(rs.getString("userName"));
                r.setBookTitle(rs.getString("bookTitle"));
                r.setStatus(rs.getString("status"));
                r.setRequestDate(rs.getString("request_date"));
                result.add(r);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateRequestStatus(int requestId, String action) throws Exception {
        try ( Connection conn = DBUtils.getConnection()) {
            conn.setAutoCommit(false);

            // Cập nhật trạng thái book_request
            String updateRequestSQL = "UPDATE book_requests SET status = ? WHERE id = ?";
            try ( PreparedStatement ps = conn.prepareStatement(updateRequestSQL)) {
                ps.setString(1, action);
                ps.setInt(2, requestId);
                ps.executeUpdate();
            }

            if (action.equals("approve")) {
                // Lấy user_id, book_id từ book_requests
                int userId = -1, bookId = -1;
                String fetchInfoSQL = "SELECT user_id, book_id FROM book_requests WHERE id = ?";
                try ( PreparedStatement ps = conn.prepareStatement(fetchInfoSQL)) {
                    ps.setInt(1, requestId);
                    try ( ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            userId = rs.getInt("user_id");
                            bookId = rs.getInt("book_id");
                        }
                    }
                }

                if (userId > 0 && bookId > 0) {
                    // Lấy thời hạn mượn sách từ cấu hình
                    int duration = 14;
                    String configSQL = "SELECT config_value FROM system_config WHERE config_key = 'default_borrow_duration_days'";
                    try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(configSQL)) {
                        if (rs.next()) {
                            duration = Integer.parseInt(rs.getString("config_value"));
                        }
                    }

                    // Tạo bản ghi mới trong borrow_records
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(duration);

                    String insertBorrowSQL = "INSERT INTO borrow_records (user_id, book_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, 'borrowed')";
                    try ( PreparedStatement ps = conn.prepareStatement(insertBorrowSQL)) {
                        ps.setInt(1, userId);
                        ps.setInt(2, bookId);
                        ps.setDate(3, Date.valueOf(borrowDate));
                        ps.setDate(4, Date.valueOf(dueDate));
                        ps.executeUpdate();
                    }

                    // Giảm available_copies trong books
                    String updateBookSQL = "UPDATE books SET available_copies = available_copies - 1 WHERE id = ? AND available_copies > 0";
                    try ( PreparedStatement ps = conn.prepareStatement(updateBookSQL)) {
                        ps.setInt(1, bookId);
                        ps.executeUpdate();
                    }
                }
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Lỗi khi xử lý yêu cầu mượn/trả: " + e.getMessage());
        }
    }
}

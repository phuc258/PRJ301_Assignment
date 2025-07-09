/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OverdueRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author toila
 */
public class BorrowRecordDAO {

    public BorrowRecordDAO() {
    }
//--------------------------------------------------------------------------------------------------------------------------------------

    private double layTienPhat() {
        double tienphat = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT config_value FROM system_config WHERE config_key = 'overdue_fine_per_day'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                 if (rs.next()) {
            tienphat = Double.parseDouble(rs.getString("config_value"));
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
        return tienphat;
    }

    public ArrayList<OverdueRecord> getOverdueRecords() throws SQLException {
        ArrayList<OverdueRecord> result = new ArrayList<>();
        double finePerDay = layTienPhat(); // mặc định

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT u.name AS user_name, b.title AS book_title, br.borrow_date, br.due_date " +
                 "FROM borrow_records br " +
                 "JOIN users u ON br.user_id = u.id " +
                 "JOIN books b ON br.book_id = b.id " +
                 "WHERE br.status = 'overdue'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                  while (rs.next()) {
            String user = rs.getString("user_name");
            String title = rs.getString("book_title");
            String borrowDate = rs.getString("borrow_date");
            String dueDate = rs.getString("due_date");

            LocalDate due = LocalDate.parse(dueDate);
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(due, LocalDate.now());
            double fine = daysOverdue * finePerDay;

            result.add(new OverdueRecord(user, title, borrowDate, dueDate, daysOverdue, fine));
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

    //----------------------------------------------------------------------------------------------------------
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Book;
import dto.BorrowRecord;
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
                String sql = "SELECT u.name AS user_name, b.title AS book_title, br.borrow_date, br.due_date "
                        + "FROM borrow_records br "
                        + "JOIN users u ON br.user_id = u.id "
                        + "JOIN books b ON br.book_id = b.id "
                        + "WHERE br.status = 'overdue'";
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
    private int laySoNgayChoThue() {
        int result = 0;

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select config_value from system_config where config_key = 'default_borrow_duration_days'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = Integer.parseInt(rs.getString("config_value"));
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

    public int setStatus(String status, int id) {
        int result = 0;

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update borrow_records set status = ? where id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setInt(2, id);
                result = ps.executeUpdate();

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

    public int updateStatus() {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [id],[status],return_date\n"
                        + " ,\n"
                        + "  DATEDIFF(\n"
                        + "    DAY,          \n"
                        + "    borrow_records.borrow_date,  \n"
                        + "    GETDATE()      \n"
                        + "  ) AS days_elapsed\n"
                        + "FROM borrow_records";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int days_elapsed = rs.getInt("days_elapsed");
                    String status = rs.getString("status");
                    String return_date = rs.getString("return_date");
                    if (return_date == null) {
                        if (days_elapsed > laySoNgayChoThue()) {
                            setStatus("overdue", id);
                        } else {
                            setStatus("borrowed", id);
                        }
                        result++;
                    }
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

    public ArrayList<BorrowRecord> getBorrowHistoryByUserId(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<BorrowRecord> result = new ArrayList<>();

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT br.id, br.book_id, b.title, br.borrow_date, br.due_date, br.return_date, br.status\n"
                        + "FROM borrow_records br\n"
                        + "JOIN books b ON br.book_id = b.id\n"
                        + "WHERE br.user_id = 7\n"
                        + "ORDER BY br.borrow_date DESC";

                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1,userId );
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int book_id=  rs.getInt("book_id");
                    String title=  rs.getString("title");
                    String borrow_date = rs.getString("borrow_date");
                    String due_date = rs.getString("due_date");
                    String return_date = rs.getString("return_date");
                    String status = rs.getString("status");
                    BorrowRecord brd = new BorrowRecord(id, userId, book_id, due_date, title, borrow_date, due_date, return_date, status);
                    result.add(brd);
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
}

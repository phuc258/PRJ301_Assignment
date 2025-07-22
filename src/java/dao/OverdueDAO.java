/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OverdueBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author toila
 */
public class OverdueDAO {

    public OverdueDAO() {
    }

    public ArrayList<OverdueBook> getOverdueBooks() throws SQLException, ClassNotFoundException {
        ArrayList<OverdueBook> list = new ArrayList<>();

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select borrow_records.id,books.title,users.name userName,borrow_date,due_date,DATEDIFF(\n"
                        + "    DAY,          \n"
                        + "    borrow_records.due_date,  \n"
                        + "    GETDATE()      \n"
                        + "  ) AS daysOverdue ,borrow_records.status  from borrow_records join users on borrow_records.user_id = users.id\n"
                        + "join books on borrow_records.book_id = books.id order by  CASE borrow_records.status\n"
                        + "    WHEN 'overdue'  THEN 1   \n"
                        + "    WHEN 'borrowed' THEN 2   \n"
                        + "    WHEN 'returned' THEN 3  \n"
                        + "    ELSE 4                  \n"
                        + "  END,\n"
                        + "  borrow_records.borrow_date DESC;  ";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String userName = rs.getString("userName");
                    String borrow_date = rs.getString("borrow_date");
                    String due_date = rs.getString("due_date");
                    int daysOverdue = rs.getInt("daysOverdue");
                    String status = rs.getString("status");
                    OverdueBook newdue = new OverdueBook(id, title, userName, borrow_date, due_date, daysOverdue, status);
                    list.add(newdue);
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

        return list;
    }
}

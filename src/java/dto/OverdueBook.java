/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import dao.SystemConfigDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mylib.DBUtils;

/**
 *
 * @author toila
 */
public class OverdueBook {

    private int recordId;
    private String title;
    private String userName;
    private String borrowDate;
    private String dueDate;
    private int daysOverdue;
    private String status;
    private double tienPhat = 0;

    public OverdueBook(int recordId, String title, String userName, String borrowDate, String dueDate, int daysOverdue,String status) {
        this.recordId = recordId;
        this.title = title;
        this.userName = userName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.daysOverdue = daysOverdue;
        this.status = status;
       if(this.daysOverdue > 0 && status.equals("overdue")){
           setTienPhat();
       }
    }

    public double getTienPhat() {
        return tienPhat;
    }
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
    public void setTienPhat() {
                
        this.tienPhat = layTienPhat() * this.daysOverdue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OverdueBook() {
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }
    
    
}

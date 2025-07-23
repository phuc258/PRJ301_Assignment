/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;

/**
 *
 * @author SE190585
 */
public class BorrowRecord {
    private int Id;
    private int user_id;
    private int book_id;
    private String UserName;
    private String BookTitle;
    private String BorrowDate;
    private String DueDate;
    private String ReturnDate;
    private String Status;

    public BorrowRecord() {
    }

    public BorrowRecord(int Id, int user_id, int book_id, String UserName, String BookTitle, String BorrowDate, String DueDate, String ReturnDate, String Status) {
        this.Id = Id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.UserName = UserName;
        this.BookTitle = BookTitle;
        this.BorrowDate = BorrowDate;
        this.DueDate = DueDate;
        this.ReturnDate = ReturnDate;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String BorrowDate) {
        this.BorrowDate = BorrowDate;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
}

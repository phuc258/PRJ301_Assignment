/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author toila
 */
public class BorrowRequest {

    private int id;
    private String userName;
    private String bookTitle;
    private String status;
    private String requestDate;

    public BorrowRequest(int id, String userName, String bookTitle, String status, String requestDate) {
        this.id = id;
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.status = status;
        this.requestDate = requestDate;
    }

    public BorrowRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Book;
import dto.User;
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
public class BookDAO {

    public BookDAO() {
    }

    public ArrayList<Book> getListBookByName(String name) {
        ArrayList<Book> result = new ArrayList<>();
       
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select id,[title],[author],[category],[isbn],[published_year],[total_copies],[available_copies],[status]from [dbo].[books] where title like '%" + name + "%'";

                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    result.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn"), rs.getString("category"), rs.getInt("published_year"), rs.getInt("total_copies"), rs.getInt("available_copies"), rs.getString("status")));
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

    public int deleteBookByTitle(String title, int year) {

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update [dbo].[books] set status = 'block' where title = '" + title + "'" + " AND published_year = " + year;
                PreparedStatement ps = cn.prepareStatement(sql);
                int rs = ps.executeUpdate();

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

        return 1;
    }

    public int unlockBookByTitle(String title, int year) {
        int result = 0;

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update [dbo].[books] set status = 'active' where title = '" + title + "'" + " AND published_year = " + year;
                PreparedStatement ps = cn.prepareStatement(sql);
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

    public int addNewBook(Book b) {
        int result = 0;

        Connection cn = null;
        PreparedStatement ps = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[books] "
                        + "(title, author, category, isbn, published_year, total_copies, available_copies, status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                ps = cn.prepareStatement(sql);
                ps.setString(1, b.getTitle());
                ps.setString(2, b.getAuthor());
                ps.setString(3, b.getCategory());
                ps.setString(4, b.getIsbn());
                ps.setInt(5, b.getPublished_year());
                ps.setInt(6, b.getTotal_copies());
                ps.setInt(7, b.getAvailable_copies());
                ps.setString(8, b.getStatus());

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

    public boolean checkBook(String title, int year) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT title FROM books WHERE title = ? AND published_year = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, title);
                ps.setInt(2, year);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
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

    public Book getBookById(int idBook) {
        Book result = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select id,title,author,isbn,category,published_year,total_copies,available_copies,status from books where id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idBook);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                            rs.getString("isbn"), rs.getString("category"), rs.getInt("published_year"),
                            rs.getInt("total_copies"), rs.getInt("available_copies"), rs.getString("status"));
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

    public int UpdateNewData(Book b) {
        int result = 0;

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update books set title = ?,\n"
                        + "author = ?,\n"
                        + "isbn = ?,\n"
                        + "category = ?,\n"
                        + "published_year = ?,\n"
                        + "total_copies = ?,\n"
                        + "available_copies =?,\n"
                        + "status = ?\n"
                        + "where  id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, b.getTitle());
                ps.setString(2, b.getAuthor());
                ps.setString(3, b.getIsbn());
                ps.setString(4, b.getCategory());
                ps.setInt(5, b.getPublished_year());
                ps.setInt(6, b.getTotal_copies());
                ps.setInt(7, b.getAvailable_copies());
                ps.setString(8, b.getStatus());
                ps.setInt(9, b.getId());
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

    //-----------------------------------------------------------------------------------
    public void updateBookTotal(int bookId, int quantity) throws Exception {

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update books set total_copies = (select total_copies from books where id = ?) + ? where id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, bookId);
                ps.setInt(2, quantity);
                ps.setInt(3, bookId);
                int rs = ps.executeUpdate();

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

    }

    //-----------------------------------------------------------------------------------
    /*   public ArrayList<OverdueBook> getOverdueBooks() throws SQLException {
    ArrayList<OverdueBook> list = new ArrayList<>();
    
   
                Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT b.id AS book_id, b.title, br.user_id, br.due_date " +
                 "FROM borrow_records br " +
                 "JOIN books b ON br.book_id = b.id " +
                 "WHERE br.return_date IS NULL AND br.due_date < GETDATE()";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                 while (rs.next()) {
            OverdueBook ob = new OverdueBook();
            ob.setBookId(rs.getInt("book_id"));
            ob.setTitle(rs.getString("title"));
            ob.setUserId(rs.getInt("user_id"));
            ob.setDueDate(rs.getDate("due_date"));
            list.add(ob);
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
     */
}

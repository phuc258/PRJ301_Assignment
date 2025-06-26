/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author SE190585
 */
public class UserDAO {

     public User getUserByEmail(String email) {
        ArrayList<User> list = new ArrayList<>();
        User result = null;
        Connection cn = null;
        try {
            //bc1: ket noi app voi sqlserver
            cn = DBUtils.getConnection();
            if (cn != null) {
                //bc2: viet query va execute
                String sql = "select id,name,[email],[password],[role],[status] from [dbo].[users]"
                        + "where email ='" + email + "'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("status")));
                }
                for (User u : list) {
                    if (u.getEmail().equals(email)) {
                        result = u;
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

    //ham nay de insert a new user vao bang User
    //input: name,email,password => vi id la identity, role='user',status
    public int insertNewUser(String name, String email, String password) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert dbo.users values(?,?,?,'user','active')";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, name);// 1 la vi tri cua dau ? thu nhat
                st.setString(2, email);
                st.setString(3, password);
                result = st.executeUpdate();
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

    //ham nay de login
    public User getUser(String email, String password) {
        User result = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Select id,name,[email],[password],[role],[status] from [dbo].[users] "
                        + "where email = ? and password = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    result = new User(rs.getInt("id"), rs.getString("name"),
                            rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("status"));
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

    public ArrayList<User> getFullUser() {
        ArrayList<User> result = new ArrayList<>();
        Connection cn = null;
        try {
            //bc1: ket noi app voi sqlserver
            cn = DBUtils.getConnection();
            if (cn != null) {
                //bc2: viet query va execute
                String sql = "select[id], [name],[email],[password],[role],[status] from users";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                            rs.getString("password"), rs.getString("role"), rs.getString("status"));
                    result.add(u);
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

    public int deleteUserByEmail(String email) {
        int result = 0;

        Connection cn = null;
        try {
            //bc1: ket noi app voi sqlserver
            cn = DBUtils.getConnection();
            if (cn != null) {
                //bc2: viet query va execute
                String sql = "delete from users where email = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, email);
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

    public int banAccByEmail(String email, int check) {
        int result = 0;

        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "";
                if (check == 1) {
                    sql = "Update users set status = 'blocked' where email = ?";
                } else {
                    sql = "Update users set status = 'active' where email = ?";
                }
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, email);
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

    public ArrayList<User> findUsersByEmail(String email) {
        ArrayList<User> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select id,name,email,password,role,status from users where email like '%"+email.trim()+ "%'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User u = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                            rs.getString("password"), rs.getString("role"), rs.getString("status"));
                    result.add(u);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author toila
 */
public class SystemConfigDAO {

    public ArrayList<Config> getAllConfig() {
        ArrayList<Config> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select [id],[config_key],[config_value],[description] from system_config";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                int id = rs.getInt("id");
                String config_key = rs.getString("config_key");
                String config_value = rs.getString("config_value");
                String description = "";
                if (rs.getString("description") != null) {
                    description = rs.getString("description");
                }
                Config c = new Config(id, config_key, config_value, description);
                result.add(c);
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

    public void updateConfig(String key, String value) throws Exception {
        String sql = "UPDATE system_config SET config_value = ? WHERE config_key = ?";
        try ( Connection con = DBUtils.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, value);
            ps.setString(2, key);
            ps.executeUpdate();
        }
    }
}

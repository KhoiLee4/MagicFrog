package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.JDBCUtil;
import Model.SkinsOfUser;

public class SkinsOfUserDAO implements DAOInterface<SkinsOfUser> {

    public static SkinsOfUserDAO getInstance() {
        return new SkinsOfUserDAO();
    }

    @Override
    public int insert(SkinsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO skinsOfUser(username, skin, state) values (?, ?, ?);";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getUsername());
            st.setString(2, t.getSkin());
            st.setInt(3, t.getState());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int insert(String username) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String[] skins = {"Skin1", "Skin2"};
            int count = 0; // Biến đếm

            for(int i = 0; i < 2; i++) {
                String sql = "INSERT INTO skinsOfUser(username, skin, state) values (?, ?, ?);";

                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, skins[i]);
                st.setInt(3, 0);
                
                result += st.executeUpdate(); // Cộng vào biến result
                count++; // Tăng biến đếm
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; // Trả về tổng số hàng đã chèn
    }


    @Override
    public int update(SkinsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE skinsOfUser SET state = ? WHERE username = ? AND skin = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, t.getState());
            st.setString(2, t.getUsername());
            st.setString(3, t.getSkin());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(SkinsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM skinsOfUser WHERE username = ? AND skin = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getSkin());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[] selectStatesByUsername(String username) {
        ArrayList<Integer> states = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT state FROM skinsOfUser WHERE username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                states.add(rs.getInt("state"));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return states.stream().mapToInt(i -> i).toArray();
    }
    
    public ArrayList<SkinsOfUser> selectAll() {
        ArrayList<SkinsOfUser> skins = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM skinsOfUser";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SkinsOfUser skin = new SkinsOfUser();
                skin.setUsername(rs.getString("username"));
                skin.setSkin(rs.getString("skin"));
                skin.setState(rs.getInt("state"));
                skins.add(skin);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skins;
    }

    @Override
    public SkinsOfUser selectByUsername(SkinsOfUser t) {
        SkinsOfUser skin = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM skinsOfUser WHERE username = ? AND skin = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getSkin());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                skin = new SkinsOfUser();
                skin.setUsername(rs.getString("username"));
                skin.setSkin(rs.getString("skin"));
                skin.setState(rs.getInt("state"));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skin;
    }

    @Override
    public ArrayList<SkinsOfUser> selectByCondition(String condition) {
        ArrayList<SkinsOfUser> skins = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM skinsOfUser WHERE " + condition;
            st = con.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                SkinsOfUser skin = new SkinsOfUser();
                skin.setUsername(rs.getString("username"));
                skin.setSkin(rs.getString("skin"));
                skin.setState(rs.getInt("state"));
                skins.add(skin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are closed in the finally block to avoid memory leaks
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (con != null) JDBCUtil.closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return skins;
    }
}

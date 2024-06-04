package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.JDBCUtil;
import Model.Skin;

public class SkinDAO implements DAOInterface<Skin> {

    public static SkinDAO getInstance() {
        return new SkinDAO();
    }

    @Override
    public int insert(Skin skin) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO skins(skin, url) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, skin.getSkin());
            st.setString(2, skin.getUrl());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Skin skin) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE skins SET url = ? WHERE skin = ?;";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, skin.getUrl());
            st.setString(2, skin.getSkin());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Skin skin) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM skins WHERE skin = ?;";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, skin.getSkin());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Skin> selectAll() {
        ArrayList<Skin> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM skins;";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String skin = rs.getString("skin");
                String url = rs.getString("url");

                Skin skinObj = new Skin(skin, url);
                result.add(skinObj);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Skin selectByUsername(Skin skin) {
        // Method not implemented as per your request
        return null;
    }

    @Override
    public ArrayList<Skin> selectByCondition(String condition) {
        ArrayList<Skin> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM skins " + condition + ";";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String skin = rs.getString("skin");
                String url = rs.getString("url");

                Skin skinObj = new Skin(skin, url);
                result.add(skinObj);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

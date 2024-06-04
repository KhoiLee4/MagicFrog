package GameData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO implements DAOInterface<Item> {

    public static ItemDAO getInstance() {
        return new ItemDAO();
    }

    @Override
    public int insert(Item item) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO items(item, url) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, item.getItem());
            st.setString(2, item.getUrl());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Item item) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "UPDATE items SET url = ? WHERE item = ?;";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, item.getUrl());
            st.setString(2, item.getItem());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Item item) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "DELETE FROM items WHERE item = ?;";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, item.getItem());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Item> selectAll() {
        ArrayList<Item> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM items;";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String item = rs.getString("item");
                String url = rs.getString("url");

                Item itemObj = new Item(item, url);
                result.add(itemObj);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Item selectByUsername(Item item) {
        // Method not implemented as per your request
        return null;
    }

    @Override
    public ArrayList<Item> selectByCondition(String condition) {
        ArrayList<Item> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM items " + condition + ";";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String item = rs.getString("item");
                String url = rs.getString("url");

                Item itemObj = new Item(item, url);
                result.add(itemObj);
            }

            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

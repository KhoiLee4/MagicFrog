package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.JDBCUtil;
import Model.ItemsOfUser;

public class ItemsOfUserDAO implements DAOInterface<ItemsOfUser> {

    public static ItemsOfUserDAO getInstance() {
        return new ItemsOfUserDAO();
    }

    @Override
    public int insert(ItemsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "INSERT INTO itemsOfUser(username, item, quantity) values (?, ?, ?);";

            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, t.getUsername());
            st.setString(2, t.getItem());
            st.setInt(3, t.getQuantity());

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

            String[] items = {"Item1", "Item2", "Item3", "Item4"};
            int count = 0; // Biến đếm

            for(int i = 0; i < 4; i++) {
                String sql = "INSERT INTO itemsOfUser(username, item, quantity) values (?, ?, ?);";

                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, username);
                st.setString(2, items[i]);
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
    public int update(ItemsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE itemsOfUser SET quantity = ? WHERE username = ? AND item = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, t.getQuantity());
            st.setString(2, t.getUsername());
            st.setString(3, t.getItem());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(ItemsOfUser t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM itemsOfUser WHERE username = ? AND item = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getItem());

            result = st.executeUpdate();

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int[] selectQuantitiesByUsername(String username) {
        ArrayList<Integer> quantities = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT quantity FROM itemsOfUser WHERE username = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                quantities.add(rs.getInt("quantity"));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantities.stream().mapToInt(i -> i).toArray();
    }
    
    public ArrayList<ItemsOfUser> selectAll() {
        ArrayList<ItemsOfUser> items = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM itemsOfUser";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ItemsOfUser item = new ItemsOfUser();
                item.setUsername(rs.getString("username"));
                item.setItem(rs.getString("item"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public ItemsOfUser selectByUsername(ItemsOfUser t) {
        ItemsOfUser item = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM itemsOfUser WHERE username = ? AND item = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getItem());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                item = new ItemsOfUser();
                item.setUsername(rs.getString("username"));
                item.setItem(rs.getString("item"));
                item.setQuantity(rs.getInt("quantity"));
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public ArrayList<ItemsOfUser> selectByCondition(String condition) {
        ArrayList<ItemsOfUser> items = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM itemsOfUser WHERE " + condition;
            st = con.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                ItemsOfUser item = new ItemsOfUser();
                item.setUsername(rs.getString("username"));
                item.setItem(rs.getString("item"));
                item.setQuantity(rs.getInt("quantity"));
                items.add(item);
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
        return items;
    }
}


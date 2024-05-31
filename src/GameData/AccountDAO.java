package GameData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO implements DAOInterface<Account> {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    @Override
    public int insert(Account t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO account(username, password) values (?, ?);";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());
            
            result = st.executeUpdate();
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Account t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "UPDATE account SET password = ? WHERE username = ?;";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getPassword());
            st.setString(2, t.getUsername());
            
            result = st.executeUpdate();
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Account t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "DELETE FROM account WHERE username = ?;";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getUsername());
            
            result = st.executeUpdate();
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Account> selectAll() {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM account;";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(username + " " + password);
                
                Account account = new Account(username, password);
                result.add(account);
            }
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Account selectByUsername(Account t) {
        Account result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM account WHERE username = ?;";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getUsername());
            
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(username + " " + password);
                
                result = new Account(username, password);
            }
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Account> selectByCondition(String condition) {
        ArrayList<Account> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM account " + condition + ";";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(username + " " + password);
                
                Account account = new Account(username, password);
                result.add(account);
            }
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}


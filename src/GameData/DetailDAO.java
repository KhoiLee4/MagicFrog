package GameData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DetailDAO implements DAOInterface<Detail>{
	
	public static DetailDAO getInstance() {
		return new DetailDAO();
	}

	@Override
	public int insert(Detail t) {
		int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            
            String sql = "INSERT INTO detail(username, money, max_score, skins, items, game_music, sound_effect)"
            +" values (?, ?, ?, ?, ?, ?, ?);";
            
            PreparedStatement st = con.prepareStatement(sql);
            
            st.setString(1, t.getUsername());
            st.setInt(2, t.getMoney());
            st.setInt(3, t.getMaxScore());
            st.setString(4, t.getSkins());
            st.setString(5, t.getItems());
            st.setBoolean(6, t.isGameMusic());
            st.setBoolean(7, t.isSoundEffect());
            
            result = st.executeUpdate();
            
            JDBCUtil.closeConnection(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
	}

	@Override
	public int update(Detail t) {
		int result = 0;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "UPDATE detail SET money = ?, max_score = ?, skins = ?, items = ?, game_music = ?, sound_effect = ? WHERE username = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        
	        st.setInt(1, t.getMoney());
	        st.setInt(2, t.getMaxScore());
	        st.setString(3, t.getSkins());
	        st.setString(4, t.getItems());
	        st.setBoolean(5, t.isGameMusic());
	        st.setBoolean(6, t.isSoundEffect());
	        st.setString(7, t.getUsername());
	        
	        result = st.executeUpdate();
	        
	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	@Override
	public int delete(Detail t) {
		int result = 0;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "DELETE FROM detail WHERE username = ?";
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
	public ArrayList<Detail> selectAll() {
		ArrayList<Detail> details = new ArrayList<>();
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM detail ORDER BY max_score DESC";
	        PreparedStatement st = con.prepareStatement(sql);
	        
	        ResultSet rs = st.executeQuery();
	        while (rs.next()) {
	            Detail detail = new Detail();
	            detail.setUsername(rs.getString("username"));
	            detail.setMoney(rs.getInt("money"));
	            detail.setMaxScore(rs.getInt("max_score"));
	            detail.setSkins(rs.getString("skins"));
	            detail.setItems(rs.getString("items"));
	            detail.setGameMusic(rs.getBoolean("game_music"));
	            detail.setSoundEffect(rs.getBoolean("sound_effect"));
	            details.add(detail);
	        }
	        
	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return details;
	}

	@Override
	public Detail selectByUsername(Detail t) {
		Detail detail = null;
	    try {
	        Connection con = JDBCUtil.getConnection();
	        String sql = "SELECT * FROM detail WHERE username = ?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, t.getUsername());
	        
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            detail = new Detail();
	            detail.setUsername(rs.getString("username"));
	            detail.setMoney(rs.getInt("money"));
	            detail.setMaxScore(rs.getInt("max_score"));
	            detail.setSkins(rs.getString("skins"));
	            detail.setItems(rs.getString("items"));
	            detail.setGameMusic(rs.getBoolean("game_music"));
	            detail.setSoundEffect(rs.getBoolean("sound_effect"));
	        }
	        
	        JDBCUtil.closeConnection(con);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return detail;
	}

	@Override
	public ArrayList<Detail> selectByCondition(String condition) {
		ArrayList<Detail> details = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    
	    try {
	        con = JDBCUtil.getConnection();
	        // The SQL statement is built by concatenating a safe, predefined query with the condition
	        String sql = "SELECT * FROM detail WHERE " + condition;
	        st = con.prepareStatement(sql);

	        rs = st.executeQuery();

	        while (rs.next()) {
	            Detail detail = new Detail();
	            detail.setUsername(rs.getString("username"));
	            detail.setMoney(rs.getInt("money"));
	            detail.setMaxScore(rs.getInt("max_score"));
	            detail.setSkins(rs.getString("skins"));
	            detail.setItems(rs.getString("items"));
	            detail.setGameMusic(rs.getBoolean("game_music"));
	            detail.setSoundEffect(rs.getBoolean("sound_effect"));
	            details.add(detail);
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
	    return details;
	}

}

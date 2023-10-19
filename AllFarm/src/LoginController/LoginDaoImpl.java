package LoginController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import logModel.LoginInfo;
import oracle.jdbc.OracleDriver;
import static farmModel.Crop.Entity.*;
import static logModel.LoginInfo.Entity.*;
import static ojdbc.OracleConnect.*;

public class LoginDaoImpl implements LoginDao{
	
	private static LoginDaoImpl instance = null;
	private LoginDaoImpl() {}
	public static LoginDaoImpl getinstance() {
		if (instance == null) {
			instance = new LoginDaoImpl();
			return instance;
		}
		return instance;
	}
	
	
	private Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}
	
	private void closeResources(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}
	
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}
	
	private static final String SQL_JOININ = String.format("insert into %s (%s, %s) values (?, ?)", LOGIN_TBL_NAME, LOGIN_COL_ID, LOGIN_COL_PW);
	
	@Override
	public int joinin(LoginInfo logininfo) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		int result = duplicateIDCheck(logininfo.getId());
		if (result == 2) {
			return result;
		}
		
		try {
			conn = getConnection();
			System.out.println(SQL_JOININ);
			stmt = conn.prepareStatement(SQL_JOININ);
			stmt.setString(1, logininfo.getId());
			stmt.setString(2, logininfo.getPw());
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	private static final String SQL_SELECT = String.format("select * from %s where %s = ?", LOGIN_TBL_NAME, LOGIN_COL_ID); 
	
	@Override
	public int loginCheck(LoginInfo logininfo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT);
			stmt = conn.prepareStatement(SQL_SELECT);
			stmt.setString(1, logininfo.getId());
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				String id = rs.getString(LOGIN_COL_ID);
				String pw = rs.getString(LOGIN_COL_PW);
				
				if (logininfo.getId().equals(id)&&
						logininfo.getPw().equals(pw)) {
					result = 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	@Override
	public int duplicateIDCheck(String id) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_SELECT);
			stmt = conn.prepareStatement(SQL_SELECT);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			
			while(rs.next()) {
				String idFromDB = rs.getString(LOGIN_COL_ID);
				
				if (id.equals(idFromDB)) {
					result = 2;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	private static final String SQL_CHECK_ID = String.format("select * from %s where %s = ?", CROP_TBL_NAME, CROP_COL_ID);
	
	@Override
	public int checkRecorde(LoginInfo loginInfo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			System.out.println(SQL_CHECK_ID);
			stmt = conn.prepareStatement(SQL_CHECK_ID);
			stmt.setString(1, loginInfo.getId());
			rs = stmt.executeQuery();
			
			if (rs.next() == true) {
				result = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
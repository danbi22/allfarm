package ojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ojdbc.OracleConnect.*;

import oracle.jdbc.OracleDriver;

public class OracleUtil {
	
	public static final Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return conn;
	}
	
	public static final void closeResources(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}
	
	public static final void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		stmt.close();
		conn.close();
	}

}
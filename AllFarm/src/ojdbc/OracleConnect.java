package ojdbc;

public interface OracleConnect {
	// 오라클 데이터베이스 서버 접속 주소(IP, port, SID)
	String URL = "jdbc:oracle:thin:@Localhost:1521:xe";
	
	// 오라클 데이터베이스 서버 계정
	String USER = "scott";
	
	// 오라클 데이터베이스 서버 비밀번호
	String PASSWORD = "tiger";
}
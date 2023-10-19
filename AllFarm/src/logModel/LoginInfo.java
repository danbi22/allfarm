package logModel;

public class LoginInfo {
	public interface Entity{
		String LOGIN_TBL_NAME = "login";
		String LOGIN_COL_ID = "id";
		String LOGIN_COL_PW = "pw";
	}
	private String id;
	private String pw;
	
	public LoginInfo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	

}
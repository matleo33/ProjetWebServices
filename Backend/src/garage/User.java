package garage;

public class User {
	
	private static int m_lastId=0;
	private int m_id;
	private String m_username;
	private String m_password;
	
	public User(String username, String password) {
		m_username = username;
		m_password = password;
		++m_lastId;
		m_id = m_lastId;
	}
	
	public User() {
		
	}
	
	public void setPassword(String newPassword) {
		m_password = newPassword;
	}
	
	
	public boolean connect(String username, String password) {
		return (m_username == username && m_password == password);
	}
}

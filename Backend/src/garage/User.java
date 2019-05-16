package garage;

public class User {
	public enum Grade{Admin, Customer, Provider};
	
	private static int m_lastId=0;
	private int m_id;
	private String m_username;
	private String m_password;
	private Grade m_grade;
	
	public User(String username, String password, Grade grade) {
		m_username = username;
		m_password = password;
		m_grade = grade;
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

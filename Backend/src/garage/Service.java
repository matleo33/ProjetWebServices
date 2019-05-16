package garage;

public class Service {
	private String m_name;
	private int m_price;
	
	public Service() {
		
	}
	
	public Service(String name, int price) {
		m_name=name;
		m_price=price;
	}
	
	public String getName() {
		return m_name;
	}
	
	public void setName(String newName) {
		m_name = newName;
	}
	
	public int getPrice() {
		return m_price;
	}
	
	public void setPrice(int newPrice) {
		m_price = newPrice;
	}
}

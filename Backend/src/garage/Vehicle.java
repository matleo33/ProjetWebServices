package garage;

public class Vehicle {
	private String m_brand;
	private String m_model;
	private int m_price;
	
	public Vehicle(String brand, String model, int price) {
		m_brand = brand;
		m_model = model;
		m_price = price;
	}
	
	public void setBrand(String newBrand) {
		m_brand = newBrand;
	}
	
	public String getBrand() {
		return m_brand;
	}
	
	public void setModel(String newModel) {
		m_model = newModel;
	}
	
	public String getModel() {
		return m_model;
	}
	
	public void setPrice(int newPrice) {
		m_price = newPrice;
	}
	
	public int getPrice() {
		return m_price;
	}
	
	public String toString () {
		String tmp="";
		tmp += m_brand + ", " + m_model;
		return tmp;
	}
}

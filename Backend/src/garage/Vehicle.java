package garage;

public class Vehicle {
	private String m_brand;
	private String m_model;
	private int m_price;
	int m_kilometers;
	
	public Vehicle(String brand, String model, int price, int kilometers) {
		m_brand = brand;
		m_model = model;
		m_price = price;
		m_kilometers = kilometers;
	}
	
	public Vehicle() {
		
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
	
	public int getKilometers() {
		return m_kilometers;
	}
	
	public void setKilometers(int newKilometers) {
		m_kilometers = newKilometers;
	}
}

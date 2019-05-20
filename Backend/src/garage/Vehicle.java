package garage;

public class Vehicle {
	protected String m_brand;
	protected String m_model;
	
	public Vehicle(String brand, String model) {
		m_brand = brand;
		m_model = model;
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
	
	
	public String toString () {
		String tmp="";
		tmp += m_brand + ", " + m_model;
		return tmp;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vehicle)) {
			return false;
		}
		
		Vehicle v = (Vehicle) obj;
		return (this.m_brand == v.getBrand() && this.m_model == v.getModel());
	}
}
package garage;

public class CarPart {
	private String m_name;
	private Vehicle m_vehicle;
	private int m_quantity;
	private int m_price;
	
	public CarPart(String name, Vehicle v, int quantity, int price) {
		m_name = name;
		m_vehicle = v;
		m_quantity = quantity;
		m_price = price;
	}
	
	public CarPart() {
		
	}
	
	public void setName(String newName) {
		m_name = newName;
	}
	
	public String getName() {
		return m_name;
	}
	
	public void setVehicle(Vehicle v) {
		m_vehicle = v;
	}
	
	public Vehicle getVehicle() {
		return m_vehicle;
	}
	
	public void setQuantity(int newQuantity) {
		m_quantity = newQuantity;
	}
	
	public int getQuantity() {
		return m_quantity;
	}
	
	public void setPrice(int newPrice) {
		m_price = newPrice;
	}
	
	public int getPrice() {
		return m_price;
	}
	
	public void addOne() {
		m_quantity++;
	}
	
	public String toString() {
		String tmp = "This part is a " + m_name + " adapted to the vehicle : " + m_vehicle;
		tmp += "\n";
		tmp += "There are " + m_quantity + " left";
		return tmp;
	}
	
	public boolean removeOne() {
		m_quantity--;
		return (m_quantity==0);
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CarPart)) {
			return false;
		}
		
		CarPart c = (CarPart) obj;
		return (this.m_name == c.getName() && this.m_price == c.getPrice() && this.m_vehicle == c.getVehicle());
	}
}

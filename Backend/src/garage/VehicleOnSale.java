package garage;

public class VehicleOnSale extends Vehicle{

	private int m_price;
	private int m_kilometers;
	
	public VehicleOnSale(String brand, String model, int price, int kilometers) {
		super(brand,model);
		m_price = price;
		m_kilometers = kilometers;
	}
	
	public VehicleOnSale() {
		
	}
	
	public void setPrice(int newPrice) {
		m_price = newPrice;
	}
	
	public int getPrice() {
		return m_price;
	}
	
	public int getKilometers() {
		return m_kilometers;
	}
	
	public void setKilometers(int newKilometers) {
		m_kilometers = newKilometers;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof VehicleOnSale)) {
			return false;
		}
		
		VehicleOnSale v = (VehicleOnSale) obj;
		return (this.m_brand.equals(v.getBrand()) && this.m_model.equals(v.getModel()) && this.m_price == v.getPrice() && this.m_kilometers == v.getKilometers());
	}
	
	@Override
	public int hashCode() {
		return m_brand.hashCode()+m_model.hashCode()+m_price+m_kilometers;
		
	}
	
	@Override
	public String toString() {
		return super.toString()+", Price : " + m_price + ", Kilometers : " + m_kilometers;
}
}
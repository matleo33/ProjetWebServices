package garage;

import java.util.ArrayList;

import garage.User.Grade;

public class GarageManager {
	private ArrayList<Vehicle> m_vehicle;
	private ArrayList<CarPart> m_carParts;
	private ArrayList<User> m_users;
	private boolean m_connected;
	
	public GarageManager() {
		m_connected = false;
	}
	
	public void addCarPart(CarPart c) {
		if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}
	}
	
	public void addCartPart(String name, Vehicle v, int quantity, int price) {
		CarPart c = new CarPart(name, v, quantity, price);
		if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}
	}
	
	public void addVehicle(Vehicle v) {
		m_vehicle.add(v);
	}
	
	public void addVehicle(String brand, String model, int price) {
		Vehicle v = new Vehicle(brand, model, price);
		m_vehicle.add(v);
	}
	
	public boolean connect(String username, String password) {
		for (User u : m_users) {
			if (u.connect(username, password)) {
				m_connected = true;
				return true;
			}
		}
		return false;
	}
	
	public boolean register(String username, String password) {
		for (User u : m_users) {
			if (u.connect(username, password)) {
				return false;
			}
		}
		User u = new User(username, password, Grade.Customer);
		m_connected = true;
		return true;
	}
}

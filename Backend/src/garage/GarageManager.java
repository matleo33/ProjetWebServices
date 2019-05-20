package garage;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class GarageManager {
	private ArrayList<Vehicle> m_vehicles;
	private ArrayList<CarPart> m_carParts;
	private ArrayList<Service> m_services;
	private ArrayList<User> m_users;
	
	private String m_dbVehicleLocation;
	private String m_dbCarPartsLocation;
	private String m_dbServicesLocation;
	private String m_dbUsersLocation;
	
	
	public GarageManager() {
		m_vehicles = new ArrayList<Vehicle>();
		m_carParts = new ArrayList<CarPart>();
		m_services = new ArrayList<Service>();
		m_users = new ArrayList<User>();
		
		m_dbVehicleLocation = "/home/mathieu/MIAGE/M1MIAGE/S8/webservice/projet/ProjetWebServices/Backend/WebContent/WEB-INF/dbVehicle.xml";
		m_dbCarPartsLocation = "/home/mathieu/MIAGE/M1MIAGE/S8/webservice/projet/ProjetWebServices/Backend/WebContent/WEB-INF/dbCarParts.xml";
		m_dbServicesLocation = "/home/mathieu/MIAGE/M1MIAGE/S8/webservice/projet/ProjetWebServices/Backend/WebContent/WEB-INF/dbServices.xml";
		m_dbUsersLocation = "/home/mathieu/MIAGE/M1MIAGE/S8/webservice/projet/ProjetWebServices/Backend/WebContent/WEB-INF/dbUsers.xml";
		
		this.load();
	}
	
	private void saveVehicles() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(m_dbVehicleLocation)));
		} catch (FileNotFoundException fileNotFound){
			System.out.println("NO FILE HERE");
		}
		encoder.writeObject(m_vehicles);
		encoder.close();
	}
	
	private void saveCarParts() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(m_dbCarPartsLocation)));
		} catch (FileNotFoundException fileNotFound){
			System.out.println("NO FILE HERE");
		}
		encoder.writeObject(m_carParts);
		encoder.close();
	}
	
	private void saveServices() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(m_dbServicesLocation)));
		} catch (FileNotFoundException fileNotFound){
			System.out.println("NO FILE HERE");
		}
		encoder.writeObject(m_services);
		encoder.close();
	}
	
	private void saveUsers() {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(m_dbUsersLocation)));
		} catch (FileNotFoundException fileNotFound){
			System.out.println("NO FILE HERE");
		}
		encoder.writeObject(m_users);
		encoder.close();
	}
	
	private void load() {
		//Vehicle
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(m_dbVehicleLocation)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			m_vehicles = (ArrayList<Vehicle>)decoder.readObject();
		} catch (NullPointerException e) {
			
		}
		
		//Service
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(m_dbServicesLocation)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			m_services = (ArrayList<Service>)decoder.readObject();
		} catch (NullPointerException e) {
			
		}
		
		//CarParts
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(m_dbCarPartsLocation)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			m_carParts = (ArrayList<CarPart>)decoder.readObject();
		} catch (NullPointerException e) {
			
		}
		
		//Users
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(m_dbUsersLocation)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			m_users = (ArrayList<User>)decoder.readObject();
		} catch (NullPointerException e) {
			
		}
	}
	
	@WebMethod
	public boolean addCarPart(CarPart c) {
		if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}
		this.saveCarParts();
		return true;
	}
	
	/*@WebMethod
	public void addCarPart(String name, Vehicle v, int quantity, int price) {
		CarPart c = new CarPart(name, v, quantity, price);
		if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}
	}*/
	
	@WebMethod
	public boolean addVehicle(Vehicle v) {
		m_vehicles.add(v);
		saveVehicles();
		return true;
	}
	
	/*@WebMethod
	public void addVehicle(String brand, String model, int price) {
		Vehicle v = new Vehicle(brand, model, price);
		m_vehicle.add(v);
	}*/
	
	@WebMethod
	public boolean connect(String username, String password) {
		for (User u : m_users) {
			if (u.connect(username, password)) {
				return true;
			}
		}
		return false;
	}
	
	@WebMethod
	public boolean register(String username, String password, int grade) {
		for (User u : m_users) {
			if (u.connect(username, password)) {
				return false;
			}
		}
		switch (grade) {
			case 1 : {
				Administrator a = new Administrator(username,password);
				m_users.add(a);
				break;
			}
			case 2 : {
				Customer c  = new Customer(username,password);
				m_users.add(c);
				break;
			}
			case 3 : {
				Provider p = new Provider(username, password);
				break;
			}
		}
		saveUsers();
		return true;
	}
	
	@WebMethod
	public boolean sellVehicle(Vehicle v) {
		if (m_vehicles.contains(v)) {
			m_vehicles.remove(v);
			saveVehicles();
			return true;
		}
		return false;
	}
	
	@WebMethod
	public boolean sellCarPart(CarPart c) {
		if (m_carParts.contains(c)) {
			c.removeOne();
			saveCarParts();
			return true;
		}
		return false;
	}
	
	@WebMethod
	public boolean addService(Service s) {
		m_services.add(s);
		saveServices();
		return true;
	}
	
	@WebMethod
	public ArrayList<Vehicle> getVehicles() {
		return m_vehicles;
	}
	
	public ArrayList<CarPart> getCarParts() {
		return m_carParts;
	}
	
	public ArrayList<Service> getServices() {
		return m_services;
	}
	public ArrayList<User> getUsers() {
		return m_users;
	}
	
	/*public boolean addService(String name, int price) {
		Service s = new Service(name, price);
		m_services.add(s);
		return true;
	}*/
}

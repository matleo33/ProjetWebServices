package garage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import garage.User.Grade;

public class GarageManager {
	private ArrayList<Vehicle> m_vehicle;
	private ArrayList<CarPart> m_carParts;
	private ArrayList<User> m_users;
	private boolean m_connected;
	private final String m_bddLocation;
	
	public GarageManager() {
		m_connected = false;
		m_bddLocation = "/WEB-INF/bdd.xml";
		try {
			this.load();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		Element root = new Element("garage");
		Document document = new Document(root);
		
		//Save the Vehicles
		//TODO
		
		//Save the car parts
		//TODO
		
		//Save the Users
		//TODO
		
		
		 try
		   {
		      XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
		      output.output(document, new FileOutputStream(m_bddLocation));
		   }
		   catch (java.io.IOException e){}
	}
	
	private void load() throws JDOMException, IOException {
		Element root = new Element("garage");
		Document document = new Document(root);
		SAXBuilder sxb = new SAXBuilder();
		document = sxb.build(new File(m_bddLocation));
		
		root = document.getRootElement();
		
		//Get all the elements in XML
		List vehicles = root.getChild("vehicles").getChildren("vehicle");
		List carParts = root.getChild("carParts").getChildren("carPart");
		List users = root.getChild("users").getChildren("user");
		
		//Add cars as data
		Iterator i = vehicles.iterator();
		while (i.hasNext()) {
			Element tmp = (Element)i.next();
			Vehicle v = new Vehicle(tmp.getChild("brand").getText(),
									tmp.getChild("model").getText(),
									Integer.parseInt(tmp.getChild("price").getText()));
			addVehicle(v);
		}
		
		Iterator i2 = carParts.iterator();
		while (i2.hasNext()) {
			Element tmp = (Element)i2.next();
			Vehicle v = new Vehicle(tmp.getChild("brand").getText(),
									tmp.getChild("model").getText(),
									Integer.parseInt(tmp.getChild("priceV").getText()));
			CarPart c = new CarPart(tmp.getChild("name").getText(),
									v,
									Integer.parseInt(tmp.getChild("quantity").getText()),
									Integer.parseInt(tmp.getChild("price").getText()));
			addCarPart(c);
		}
		
		Iterator i3 = users.iterator();
		while (i3.hasNext()) {
			Element tmp = (Element)i3.next();
			register(tmp.getChild("username").getText(),
					  tmp.getChild("password").getText(),
					  Grade.valueOf(tmp.getChild("grade").getText()));
		}
	}
	
	public void addCarPart(CarPart c) {
		if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}
	}
	
	public void addCarPart(String name, Vehicle v, int quantity, int price) {
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
	
	public boolean register(String username, String password, Grade g) {
		for (User u : m_users) {
			if (u.connect(username, password)) {
				return false;
			}
		}
		User u = new User(username, password, g);
		m_users.add(u);
		return true;
	}
}

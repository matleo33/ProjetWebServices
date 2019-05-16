package garage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import garage.User.Grade;

@WebService
public class GarageManager {
	private Vehicle[] m_vehicles;
	private int m_nbVehicles;
	
	private CarPart[] m_carParts;
	private int m_nbCarParts;
	
	private Service[] m_services;
	private int m_nbServices; 
	
	private User[] m_users;
	private int m_nbUsers;
	
	private String m_bddLocation;
	
	public GarageManager() {
		m_vehicles = new Vehicle[Integer.MAX_VALUE];
		m_carParts = new CarPart[Integer.MAX_VALUE];
		m_services = new Service[Integer.MAX_VALUE];
		m_users = new User[Integer.MAX_VALUE];
		
		m_nbCarParts = 0;
		m_nbServices = 0;
		m_nbUsers = 0;
		m_nbVehicles = 0;
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
		List services = root.getChild("services").getChildren("service");
		
		//Add cars as data
		Iterator i = vehicles.iterator();
		while (i.hasNext()) {
			Element tmp = (Element)i.next();
			Vehicle v = new Vehicle(tmp.getChild("brand").getText(),
									tmp.getChild("model").getText(),
									Integer.parseInt(tmp.getChild("price").getText()),
									Integer.parseInt(tmp.getChild("kilometers").getText()));
			addVehicle(v);
		}
		
		Iterator i2 = carParts.iterator();
		while (i2.hasNext()) {
			Element tmp = (Element)i2.next();
			Vehicle v = new Vehicle(tmp.getChild("brand").getText(),
									tmp.getChild("model").getText(),
									Integer.parseInt(tmp.getChild("priceV").getText()),
									Integer.parseInt(tmp.getChild("kilometers").getText()));
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
		
		Iterator i4 = services.iterator();
		while (i4.hasNext()) {
			Element tmp = (Element)i4.next();
			Service s = new Service(tmp.getChild("name").getText(),
									Integer.parseInt(tmp.getChild("price").getText()));
			addService(s);
		}
	}
	
	@WebMethod
	public boolean addCarPart(CarPart c) {
		/*if (m_carParts.contains(c)) {
			m_carParts.get(m_carParts.indexOf(c)).addOne();
		} else {
			m_carParts.add(c);
		}*/
		for (int i = 0 ; i <= m_nbCarParts ; ++i) {
			if (m_carParts[i] == c) {
				m_carParts[i].addOne();
				return true;
			}
		}
		m_carParts[m_nbCarParts] = c;
		m_nbCarParts++;
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
		m_vehicles[m_nbVehicles] = v;
		m_nbVehicles++;
		return true;
	}
	
	/*@WebMethod
	public void addVehicle(String brand, String model, int price) {
		Vehicle v = new Vehicle(brand, model, price);
		m_vehicle.add(v);
	}*/
	
	@WebMethod
	public boolean connect(String username, String password) {
		for (int i = 0 ; i < m_nbUsers ; ++i) {
			if (m_users[i].connect(username, password)) {
				return true;
			}
		}
		return false;
	}
	
	@WebMethod
	public boolean register(String username, String password, Grade g) {
		for (int i = 0 ; i < m_nbUsers ; ++i) {
			if (m_users[i].connect(username, password)) {
				return false;
			}
		}
		User u = new User(username, password, g);
		m_users[m_nbUsers] = u;
		m_nbUsers++;
		return true;
	}
	
	@WebMethod
	public boolean sellVehicle(Vehicle v) {
		for (int i = 0 ; i < m_nbVehicles ; ++i) {
			if (m_vehicles[i] == v) {
				for (int j = i ; j < m_nbVehicles-1 ; ++j) {
					m_vehicles[j] = m_vehicles[j+1];
				}
				m_nbVehicles--;
				return true;
			}
		}
		return false;
	}
	
	@WebMethod
	public boolean sellCarPart(CarPart c) {
		for (int i = 0 ; i < m_nbCarParts ; ++i) {
			if (m_carParts[i] == c) {
				if (m_carParts[i].removeOne()) {
					for (int j = i ; j < m_nbCarParts-1 ; ++j) {
						m_carParts[j] = m_carParts[j+1];
					}
					m_nbCarParts--;
				}
				return true;
			}
		}
		return false;
	}
	
	@WebMethod
	public boolean addService(Service s) {
		m_services[m_nbServices]=s;
		m_nbServices++;
		return true;
	}
	
	/*public boolean addService(String name, int price) {
		Service s = new Service(name, price);
		m_services.add(s);
		return true;
	}*/
}

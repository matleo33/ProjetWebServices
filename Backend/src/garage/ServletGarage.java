package garage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGarage
 */
@WebServlet("/ServletGarage")
public class ServletGarage extends HttpServlet {
	private HashMap<String, String> m_pages;
	private GarageManager m_gm;
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletGarage() {
    	m_gm = new GarageManager();
    	m_pages = new HashMap<String, String>();
    	m_pages.put("/home", "/WEB-INF/GarageHome.jsp");
    	m_pages.put("/accessStock", "/WEB-INF/accessStock.jsp");
    	m_pages.put("/vehicleManaging", "/WEB-INF/vehicles.jsp");
    }
    
    private String matchURL(StringBuffer longURL) {
    	String URL = longURL.toString();
    	int index = URL.lastIndexOf("/");
    	URL = URL.substring(index);
    	if (m_pages.containsKey(URL)) {
    		return m_pages.get(URL);
    	}
    	return "/WEB-INF/Error404.jsp";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = matchURL(request.getRequestURL());
		request.setAttribute("vehicleList", m_gm.getVehicles());
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

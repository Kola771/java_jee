package bj.highfive.usermanagement.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bj.highfive.usermanagement.bean.User;
import bj.highfive.usermanagement.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PrintWriter out = response.getWriter();
		// out.println("Good");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		//PrintWriter out = response.getWriter();
		//out.println("user name =>" + name);
		//out.println("user email =>" + email);
		//out.println("user country =>" + country);
		
		// Initialisation de l'objet java (java bean)
		User utilisateur = new User();
		utilisateur.setName(name);
		utilisateur.setEmail(email);
		utilisateur.setCountry(country);
		
		// Appel de la passerelle UserDao pour persister l'utilisateur dans notre BDD
		UserDao userDao = new UserDao();
		userDao.createUser(utilisateur);
		
		// Méthode 1 de redirection
		//RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		//rd.forward(request, response);
		
		// Méthode 2 de redirection
		response.sendRedirect("index.jsp");
}

}

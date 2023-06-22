package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;



/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UsuarioNegocio userNeg = new UsuarioNegocioImpl();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnIngresar")!=null) {
			
			String pass = request.getParameter("txtContraseña");
			int dni = Integer.parseInt(request.getParameter("txtDNI")); 
			
			Usuario user = null;
			
			user = (Usuario) userNeg.obtenerUsuario(pass, dni);
			
			if(user != null) {
					if(user.getTipo().getIdTipoUsuario() == 0) {

						request.getSession().setAttribute("usuario", user);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalAdmin.jsp");
						dispatcher.forward(request, response);
					}
					else {
						
						request.getSession().setAttribute("usuario", user);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalMedic.jsp");
						dispatcher.forward(request, response);
					}

			}
		}
			
	}

}

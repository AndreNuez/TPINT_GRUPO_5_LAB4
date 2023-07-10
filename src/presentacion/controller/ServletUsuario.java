package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auxiliares.ErrorHandle;
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
		
		if(request.getParameter("Param") != null) {
			
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");

			if(user.getTipo().getIdTipoUsuario() == 0) {

		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalAdmin.jsp");
				dispatcher.forward(request, response);
			}
			else {
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalMedic.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ErrorHandle errorHandle = new ErrorHandle();
		
		if(request.getParameter("btnIngresar")!=null) {
			
			String pass = request.getParameter("txtContraseña");
			int dni = Integer.parseInt(request.getParameter("txtDNI")); 
			
			Usuario user = null;
			
			user = (Usuario) userNeg.obtenerUsuario(pass, dni);
			
			//Usuario not null y sin eliminar (baja lógica) // getEstado() == 1 -> True
			if(user != null && user.getEstado() == 1) {
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
			
			//Usuario eliminado (baja lógica) // getEstado() == 0 -> False
			else if (user != null && user.getEstado() == 0){
				String errorMessage = errorHandle.usuarioSoftDeleted();
			    request.getSession().setAttribute("errorMessage", errorMessage);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
			}
					
		}
		
		if(request.getParameter("btnSalir") != null)
		{
			request.getSession().removeAttribute("usuario");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
			dispatcher.forward(request, response);
		}
			
	}

}

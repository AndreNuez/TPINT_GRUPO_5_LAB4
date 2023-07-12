package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Medico;
import Exceptions.DniInvalido;
import auxiliares.ErrorHandle;
import auxiliares.Seguridad;
import entidad.Usuario;
import negocio.MedicoNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UsuarioNegocio userNeg = new UsuarioNegocioImpl();
	MedicoNegocio mNeg = new MedicoNegocioImpl();
	
    public ServletUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnIngresar")!=null) {
			
			String pass = request.getParameter("txtContraseña");
			int dni = Integer.parseInt(request.getParameter("txtDNI")); 
			
			Seguridad seguridad = new Seguridad();
			Usuario user = null;
			boolean eliminado = true;
			
			try {

				user = (Usuario) userNeg.obtenerUsuario(pass, dni);
				userNeg.validarDNI(user.getDNI());
				eliminado = seguridad.usuarioEliminado(user);

			} catch (DniInvalido dniInv) {
				
				dniInv.printStackTrace();
				
				Boolean errorDni = true;
				request.setAttribute("errorDni", errorDni);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
				request.getSession().setAttribute("errorMessage", "Ocurrio un error");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				dispatcher.forward(request, response);
			} 
			
			if(user.getDNI() != 0) {	
				if (!eliminado) {
					if(user.getTipo().getIdTipoUsuario() == 0) {
						request.getSession().setAttribute("usuario", user);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalAdmin.jsp");
						dispatcher.forward(request, response);
					}
					else {
						
						Medico m = mNeg.ListarUno(dni);
						
						request.getSession().setAttribute("usuario", user);
						request.getSession().setAttribute("medico", m);
						
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/PrincipalMedic.jsp");
						dispatcher.forward(request, response);
					}
				}
				
				else if (eliminado) {
						String mensajeBajaSistema = "Usted ha sido dado de baja en el sistema. Comuniquese con el sindicato";
						request.getSession().setAttribute("errorMessage", mensajeBajaSistema);
				    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
						dispatcher.forward(request, response);	
				}			
			}
			
			else if (user.getDNI() == 0) {
				
				//request.getSession().setAttribute("errorMessage", "Usuario o contraseña inexistente");
		    	//RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");
				//dispatcher.forward(request, response);
				return;
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

package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Especialidad;
import entidad.Localidad;
import entidad.Medico;
import entidad.Persona;
import entidad.Provincia;
import negocio.EspecialidadNegocio;
import negocio.LocalidadNegocio;
import negocio.MedicoNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

@WebServlet("/ServletMedicos")
public class ServletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	EspecialidadNegocio espNeg = new EspecialidadNegocioImpl();
	MedicoNegocio mNeg = new MedicoNegocioImpl();
	
    public ServletMedicos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null)
		{
			String param = request.getParameter("Param").toString();
			
			switch(param){
			
			case "agregarNuevo":
			{
				ArrayList<Provincia> listaP = provNeg.obtenerTodos();
				request.setAttribute("listaProv", listaP);
				
				ArrayList<Localidad> listaL = locNeg.obtenerTodos();
				request.setAttribute("listaLoc", listaL);
				
				ArrayList<Especialidad> listaE = espNeg.obtenerTodos();
				request.setAttribute("listaEsp", listaE);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
				dispatcher.forward(request, response);
				break;
			}
			
			case "list":
			{
				ArrayList<Medico> lista = mNeg.ListarTodos();
				request.setAttribute("listaMedicos", lista);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMedicos.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "confirmarSi":
			{
				boolean estado;
				int DNI = Integer.parseInt(request.getSession().getAttribute("dniMedicoAEliminar").toString());
				estado = mNeg.EliminarMedico(DNI);
				
				ArrayList<Medico> lista = mNeg.ListarTodos();
				request.setAttribute("listaMedicos", lista);
				request.setAttribute("estado", estado);
				request.removeAttribute("eliminando");
				RequestDispatcher rd = request.getRequestDispatcher("/AdminMedicos.jsp");
				
				rd.forward(request, response);	
			}
			case "confirmarNo":
			{
				ArrayList<Medico> lista = mNeg.ListarTodos();
				request.setAttribute("listaMedicos", lista);
				request.removeAttribute("eliminando");
				request.getSession().removeAttribute("dniMedicoAEliminar");
				RequestDispatcher rd = request.getRequestDispatcher("/AdminMedicos.jsp");
				
				rd.forward(request, response);	
			}
			
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//nuevo
		if(request.getParameter("btnEliminar") != null)
		{
			
			int DNI = Integer.parseInt(request.getParameter("dniMedico"));
			request.getSession().setAttribute("dniMedicoAEliminar", DNI);
			
			ArrayList<Medico> lista = mNeg.ListarTodos();
			request.setAttribute("listaMedicos", lista);
			
			boolean eliminando = true;
			request.setAttribute("eliminando", eliminando);
			RequestDispatcher rd = request.getRequestDispatcher("/AdminMedicos.jsp");
			
			rd.forward(request, response);			
		}

	}

}

package presentacion.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Direccion;
import entidad.Localidad;
import entidad.Persona;
import entidad.Provincia;
import negocio.DireccionNegocio;
import negocio.LocalidadNegocio;
import negocio.PacienteNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

@WebServlet("/ServletPacientes")
public class ServletPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PacienteNegocio pNeg = new PacienteNegocioImpl();
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	DireccionNegocio dpNeg = new DireccionNegocioImpl();
	
    public ServletPacientes() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null)
		{
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
			dispatcher.forward(request, response);
			
		}
		boolean estado;
		
		if(request.getParameter("btnEliminar") != null)
		{
			int DNI = Integer.parseInt(request.getParameter("DNIUsuario").toString());
			
			estado = pNeg.EliminarPaciente(DNI);

			ArrayList<Persona> lista = pNeg.ListarTodos();
			request.setAttribute("listaPersonas", lista);
			request.setAttribute("estado", estado);
			RequestDispatcher rd = request.getRequestDispatcher("/ListadoPacientes.jsp");
			
			rd.forward(request, response);			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAceptar")!=null) {
			
			Persona p = new Persona();
			p.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			p.setApellido(request.getParameter("txtApellido"));
			p.setNombre(request.getParameter("txtNombre"));
			p.setSexo(request.getParameter("Sexo").charAt(0));
			p.setFnac(LocalDate.parse(request.getParameter("FNac")));
			p.setNacionalidad(request.getParameter("txtNacionalidad"));
			p.setMail(request.getParameter("txtMail"));
			p.setTelefono(request.getParameter("txtTelefono"));
			p.setEstado(1);
			
			int DNI = p.getDNI();
			boolean estado = true;
			estado = pNeg.InsertarPaciente(p);
				
			Direccion dp = new Direccion();
				dp.setCalle(request.getParameter("txtCalle"));
				dp.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				dp.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
		
			boolean estadodp = true;
			estadodp = dpNeg.InsertarDP(DNI, dp);
			
			request.setAttribute("estadoPaciente", estado);
			request.setAttribute("estadoDP", estadodp);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
			dispatcher.forward(request, response);			
		}
	}

}

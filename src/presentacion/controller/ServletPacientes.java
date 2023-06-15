package presentacion.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import negocio.LocalidadNegocio;
import negocio.PacienteNegocio;
import negocio.ProvinciaNegocio;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

@WebServlet("/ServletPacientes")
public class ServletPacientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PacienteNegocio pNeg = new PacienteNegocioImpl();
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	
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
			p.setNombre(request.getParameter("txtNombre"));
			p.setApellido(request.getParameter("txtApellido"));
			p.setSexo(request.getParameter("Sexo").charAt(0));
			p.setNacionalidad(request.getParameter("txtNacionalidad"));
			
				Direccion d = new Direccion();
				d.setCalle(request.getParameter("txtCalle"));
				d.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				//d.setProvincia(new Provincia(Integer.parseInt(request.getParameter("Provincias"))));
				//d.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
			
			p.setDireccion(d);	
			p.setFnac(LocalDate.parse(request.getParameter("Fnac")));
			p.setMail(request.getParameter("txtMail"));
			p.setTelefono(request.getParameter("txtTelefono"));
			p.setEstado(Boolean.parseBoolean(request.getParameter("Estado")));
			
			
			boolean estado = true;
			estado = pNeg.InsertarPaciente(p);
			
			request.setAttribute("estadoPaciente", estado);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
			dispatcher.forward(request, response);
			
		}
	}

}

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

import Exceptions.DniInvalido;
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
			String param = request.getParameter("Param").toString();
			
			switch(param){
			
			case "agregarNuevo":
			{
				ArrayList<Provincia> listaP = provNeg.obtenerTodos();
				request.setAttribute("listaProv", listaP);
				
				ArrayList<Localidad> listaL = locNeg.obtenerTodos();
				request.setAttribute("listaLoc", listaL);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
				dispatcher.forward(request, response);
				break;
			}
			
			case "list":
			{
				ArrayList<Persona> lista = pNeg.ListarTodos();
				request.setAttribute("listaPacientes", lista);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPacientes.jsp");
				dispatcher.forward(request, response);
				break;
			}

			default:
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAceptar")!=null) {
			
			Persona p = new Persona();
			try {
				Persona.validarDNI(Integer.parseInt(request.getParameter("txtDNI")));
				
			} catch (DniInvalido dniInv) {
				System.out.println("Error al cargar el DNI");
				dniInv.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
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
			
			try {
				estado = pNeg.InsertarPaciente(p);
			} catch (Exception e) {
				// TODO: handle exception
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
			}

				
			Direccion dp = new Direccion();
				dp.setCalle(request.getParameter("txtCalle"));
				dp.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				dp.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
		
			boolean estadodp = true;
			estadodp = dpNeg.InsertarDP(DNI, dp);
			
			request.setAttribute("estadoPaciente", estado);
			request.setAttribute("estadoDP", estadodp);
			ArrayList<Persona> lista = pNeg.ListarTodos();
			request.setAttribute("listaPacientes", lista);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPacientes.jsp");
			dispatcher.forward(request, response);			
		}

		//GR Envia datos de paciente seleccionado en AdminPacientes al ABMPacientes.jsp
		if(request.getParameter("btnVer") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniPaciente"));
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			if(pNeg.ListarUno(dni) != null)
			{				
				Persona paciente = new Persona();
				paciente = pNeg.ListarUno(dni);	
				request.setAttribute("verPaciente", paciente);
				request.setAttribute("dniPaciente",dni);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(request.getParameter("btnModificar") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniPaciente"));
			
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			if(pNeg.ListarUno(dni) != null)
			{				
				Persona paciente = new Persona();
				paciente = pNeg.ListarUno(dni);	
				request.setAttribute("ModificarPaciente", paciente);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMPacientes.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		
		if(request.getParameter("btnConfirmar") != null)
		{
			Persona p = new Persona();
			p.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			p.setApellido(request.getParameter("txtApellido"));
			p.setNombre(request.getParameter("txtNombre"));
			p.setSexo(request.getParameter("Sexo").charAt(0));
			p.setFnac(LocalDate.parse(request.getParameter("FNac")));
			p.setNacionalidad(request.getParameter("txtNacionalidad"));
			p.setMail(request.getParameter("txtMail"));
			p.setTelefono(request.getParameter("txtTelefono"));
			
			
			boolean modificado = true;
			
			try {
				modificado = pNeg.EditarPaciente(p);
			} catch (Exception e) {
				// TODO: handle exception
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
			}
							
			int DNI = p.getDNI();
			
			Direccion dp = new Direccion();
				dp.setCalle(request.getParameter("txtCalle"));
				dp.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				dp.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
		
			boolean modificadodp = true;
			modificadodp = dpNeg.EditarDP(DNI, dp);
			
			request.setAttribute("modificado", modificado);
			request.setAttribute("modificadoDP", modificadodp);
			ArrayList<Persona> lista = pNeg.ListarTodos();
			request.setAttribute("listaPacientes", lista);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminPacientes.jsp");
			dispatcher.forward(request, response);			
		}
		
		if(request.getParameter("btnEliminar") != null)
		{	
			int DNI = Integer.parseInt(request.getParameter("dniPaciente"));
			
			boolean eliminado = pNeg.EliminarPaciente(DNI);
			
			ArrayList<Persona> lista = pNeg.ListarTodos();
			request.setAttribute("listaPacientes", lista);
			request.setAttribute("eliminado", eliminado);
			
			RequestDispatcher rd = request.getRequestDispatcher("/AdminPacientes.jsp");
			rd.forward(request, response);	
		}
		
	}

}

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
import entidad.Especialidad;
import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Provincia;
import negocio.DireccionNegocio;
import negocio.EspecialidadNegocio;
import negocio.HorarioNegocio;
import negocio.LocalidadNegocio;
import negocio.MedicoNegocio;
import negocio.ProvinciaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.DireccionNegocioImpl;
import negocioImpl.EspecialidadNegocioImpl;
import negocioImpl.HorarioNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/ServletMedicos")
public class ServletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ProvinciaNegocio provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocio locNeg = new LocalidadNegocioImpl();
	EspecialidadNegocio espNeg = new EspecialidadNegocioImpl();
	MedicoNegocio mNeg = new MedicoNegocioImpl();
	DireccionNegocio dmNeg = new DireccionNegocioImpl();
	UsuarioNegocio uNeg = new UsuarioNegocioImpl();
	HorarioNegocio hNeg = new HorarioNegocioImpl();
	
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
			
			default:
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnEliminar") != null)
		{
			
			int DNI = Integer.parseInt(request.getParameter("dniMedico"));
			request.getSession().setAttribute("dniMedicoAEliminar", DNI);
			
			boolean eliminarm = mNeg.EliminarMedico(DNI);
			
			ArrayList<Medico> lista = mNeg.ListarTodos();
			request.setAttribute("listaMedicos", lista);
			
			request.setAttribute("eliminarm", eliminarm);
			RequestDispatcher rd = request.getRequestDispatcher("/AdminMedicos.jsp");
			
			rd.forward(request, response);		
		}
		
		if(request.getParameter("btnAceptar")!=null) {
			
			
			Medico m = new Medico();
			m.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			m.setApellido(request.getParameter("txtApellido"));
			m.setNombre(request.getParameter("txtNombre"));
			m.setSexo(request.getParameter("Sexo").charAt(0));
			m.setFnac(LocalDate.parse(request.getParameter("FNac")));
			m.setNacionalidad(request.getParameter("txtNacionalidad"));
			m.setMail(request.getParameter("txtMail"));
			m.setTelefono(request.getParameter("txtTelefono"));
			m.setEspecialidad(new Especialidad(Integer.parseInt(request.getParameter("Especialidad"))));
			m.setEstado(1);
			
			int DNI = m.getDNI();
			String apellido = m.getApellido().toLowerCase();
			

			
			boolean estado = true;
			boolean estadohm = true;
			boolean estadoum = true;
			
			//Bloque TRY CATCH para evaluar si el usuario ya existe
			try {
				estadoum = uNeg.insertarUsuario(apellido, DNI, 1);
			
			} catch (Exception e) {
				// TODO: handle exception
				//Medico.validarPersona();
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
			}
			
			
			Horario h = new Horario();
			h.setDNIMedico(DNI);
			h.setDiaAtencion(request.getParameter("Dia"));
			h.setHoraInicio(Integer.parseInt(request.getParameter("txtDesde")));
			h.setHoraFin(Integer.parseInt(request.getParameter("txtHasta")));
			h.setEstado(1);
						
			estado = mNeg.InsertarMedico(m);
			estadohm = hNeg.InsertarHorario(h,DNI);
				
			Direccion dm = new Direccion();
				dm.setCalle(request.getParameter("txtCalle"));
				dm.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				dm.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
		
				boolean estadodm = true;
				estadodm = dmNeg.InsertarDM(DNI, dm);
			
			request.setAttribute("estadoMedico", estado);
			request.setAttribute("estadoHMedico", estadohm);
			request.setAttribute("estadoDM", estadodm);
			request.setAttribute("estadoUM", estadoum);
			
			ArrayList<Medico> lista = mNeg.ListarTodos();
			request.setAttribute("listaMedicos", lista);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMedicos.jsp");
			dispatcher.forward(request, response);			
		}
		
		if(request.getParameter("btnVer") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			if(mNeg.ListarUno(dni) != null)
			{				
				Medico medico = new Medico();
				medico = mNeg.ListarUno(dni);
				request.setAttribute("verMedico", medico);
				request.setAttribute("dniMedico",dni);
				
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(request.getParameter("btnModificar") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			ArrayList<Provincia> listaP = provNeg.obtenerTodos();
			request.setAttribute("listaProv", listaP);
			
			ArrayList<Localidad> listaL = locNeg.obtenerTodos();
			request.setAttribute("listaLoc", listaL);
			
			ArrayList<Especialidad> listaE = espNeg.obtenerTodos();
			request.setAttribute("listaEsp",listaE);
			
			if(mNeg.ListarUno(dni) != null)
			{				
				Medico medico = new Medico();
				medico = mNeg.ListarUno(dni);
				request.setAttribute("ModificarMedico", medico);
				
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMMedicos.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(request.getParameter("btnConfirmar") != null)
		{
			Medico m = new Medico();
			m.setDNI(Integer.parseInt(request.getParameter("txtDNI")));
			m.setApellido(request.getParameter("txtApellido"));
			m.setNombre(request.getParameter("txtNombre"));
			m.setSexo(request.getParameter("Sexo").charAt(0));
			m.setFnac(LocalDate.parse(request.getParameter("FNac")));
			m.setNacionalidad(request.getParameter("txtNacionalidad"));
			m.setMail(request.getParameter("txtMail"));
			m.setTelefono(request.getParameter("txtTelefono"));
			m.setEspecialidad(new Especialidad(Integer.parseInt(request.getParameter("Especialidad"))));
			
			int DNI = m.getDNI();
			String pass = m.getApellido().toLowerCase();
			
			boolean modificado = true;
			
			try {
				modificado = mNeg.EditarMedico(m);
			} catch (Exception e) {
				// TODO: handle exception
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Principal.jsp");
				dispatcher.forward(request, response);
			}
			
				
			Direccion dm = new Direccion();
				dm.setCalle(request.getParameter("txtCalle"));
				dm.setNumero(Integer.parseInt(request.getParameter("txtNumero")));
				dm.setLocalidad(new Localidad(Integer.parseInt(request.getParameter("Localidades"))));
		
			boolean modificadodm = true;
			modificadodm = dmNeg.EditarDM(DNI, dm);
			
			boolean modificarpass =true;
			modificarpass = uNeg.editarUsuario(pass, DNI);
			
			request.setAttribute("modificado", modificado);
			request.setAttribute("modificadoDM", modificadodm);
			request.setAttribute("modificadoUM", modificarpass);
			ArrayList<Medico> lista = mNeg.ListarTodos();
			request.setAttribute("listaMedicos", lista);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AdminMedicos.jsp");
			dispatcher.forward(request, response);			
		}

	}

}

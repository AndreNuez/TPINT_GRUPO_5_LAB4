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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


import entidad.Direccion;
import entidad.Horario;
import entidad.Localidad;
import entidad.Medico;
import entidad.Persona;
import entidad.Turno;
import entidad.Usuario;
import negocio.HorarioNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;
import negocio.TurnoNegocio;
import negocioImpl.HorarioNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
import negocioImpl.PacienteNegocioImpl;
import negocioImpl.TurnoNegocioImpl;


/**
 * Servlet implementation class ServletTurno
 */
@WebServlet("/ServletTurno")
public class ServletTurno extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	TurnoNegocio tneg = new TurnoNegocioImpl();
	MedicoNegocio mneg = new MedicoNegocioImpl();
	HorarioNegocio hNeg = new HorarioNegocioImpl();
	PacienteNegocio pneg = new PacienteNegocioImpl();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTurno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Param")!=null)
		{
			String param = request.getParameter("Param").toString();
			
			switch(param){
			
			case "list":
			{
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			
			case "listarM":
			{
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				dispatcher.forward(request, response);
				break;
			}
			case "listarTurnos":
			{
				Usuario u = (Usuario) request.getSession().getAttribute("usuario");
				Medico m = mneg.ListarUno(u.getDNI());
				
				ArrayList<Turno> listaturnos = tneg.ListaTurnosPorMedico(m);
				request.setAttribute("listaTurnos", listaturnos);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
				dispatcher.forward(request, response);
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

		if(request.getParameter("btnAsignar")!=null) {
			
			Persona paciente = new Persona();
			String mensajeDeActualizacion = "";
			
			if(request.getParameter("dni") == null || request.getParameter("dni") == "") 
			{
				mensajeDeActualizacion = "Por favor, ingrese un DNI.";
				
				//Carga de listas predeterminadas
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
		    	dispatcher.forward(request, response);
			}

			
			paciente = pneg.ListarUno((Integer.parseInt(request.getParameter("dni"))));
			if(paciente.getDNI() == 0) 
			{
				//Carga de listas predeterminadas
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				//Paciente no existe
				request.setAttribute("pacienteNoExiste", true);
				System.out.println(Integer.parseInt(request.getParameter("dni")));
				request.setAttribute("dniACrear", Integer.parseInt(request.getParameter("dni")));
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);				
			}
			
			paciente.setDNI((Integer.parseInt(request.getParameter("dni"))));
			
			Turno t = new Turno();
			t.setIdTurno((Integer.parseInt(request.getParameter("idTurno"))));
			t.setFecha(LocalDate.parse(request.getParameter("fechaTurno")));
			t.setHora(Integer.parseInt(request.getParameter("horaTurno")));			
			t.setPaciente(paciente);
			
			System.out.println("hora "+t.getHora()+" fecha: "+t.getFecha()+"dnipaciente: "+t.getPaciente().getDNI());
			
			if(tneg.existeTurnoEnHorarioFecha(t) == true) 
			{
				//Carga de listas predeterminadas
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				//Mensaje de error
				mensajeDeActualizacion = "El paciente ya tiene un turno asginado para esa fecha y hora.";
				request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
				
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);				
			}
			
			t.setEstado(1);
			
			boolean estado = tneg.ActualizarTurno(t);
			
			
			if(estado == true) {
				mensajeDeActualizacion = "Se asigno el paciente al turno exitosamente.";
			}
			else {
				mensajeDeActualizacion = "No se pudo asignar el turno."+"\n"+"Verifique que el DNI ingresado sea valido.";
			}
			
			//Carga de listas predeterminadas
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			ArrayList<Turno> lista = tneg.ListarTodos();
			request.setAttribute("listaTurnosPorAsignar", lista);
			
			request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
			dispatcher.forward(request, response);			
		}
		
		if(request.getParameter("btnFilter")!=null) 
		{
			Medico m = new Medico();
			m.setDNI(Integer.parseInt(request.getParameter("Medicos")));
			
			request.setAttribute("medicoSeleccionado", m);
			
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			if(Integer.parseInt(request.getParameter("Medicos")) == 0)
			{
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
				dispatcher.forward(request, response);
			}
			
			ArrayList<Turno> listaPorMedico = tneg.ListaTurnosPorMedico(m);
			request.setAttribute("listaTurnosPorMedico", listaPorMedico);				
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
			dispatcher.forward(request, response);
		}
		
		if(request.getParameter("deleteFilters")!=null) 
		{
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			ArrayList<Turno> lista = tneg.ListarTodos();
			request.setAttribute("listaTurnosPorAsignar", lista);
			
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
	    	dispatcher.forward(request, response);
		}
		
		// LOGICA PARA CREAR TURNOS
		
		//Buscar datos del mï¿½dico seleccionado
		if(request.getParameter("btnBuscar")!= null) 
		{
			int dni = Integer.parseInt(request.getParameter("Medicos"));
			request.getSession().setAttribute("dniMedico", dni);

			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			
			if(mneg.ListarUno(dni) != null)
			{				
				Medico medico = new Medico();
				medico = mneg.ListarUno(dni);
				request.setAttribute("verDatos", medico);
				
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				boolean buscar = true;
				request.setAttribute("buscar", buscar);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				dispatcher.forward(request, response);
			}
		}

		if(request.getParameter("btnAceptar") != null) 

		{
			int dniMedico = (int) request.getSession().getAttribute("dniMedico");
			
			Medico m = mneg.ListarUno(dniMedico);
			int cont = 0;
			String dia = request.getParameter("DiaAtencion");
			LocalDate fecha = LocalDate.parse(request.getParameter("FechaTurno"));
			//System.out.println(fecha);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("es"));
	        String dayOfWeek = fecha.format(formatter);
	        
	        // Check if the day of the week matches any of the names
	        
	        if (dayOfWeek.equalsIgnoreCase(dia)) 
	        {
	        	if(!tneg.chequearFecha(fecha, dniMedico))
	        	{
	        		Horario h = hNeg.buscarHorario(dniMedico, dia);
	    			
	    			for(int i = h.getHoraInicio(); i < h.getHoraFin();i++)
	    			{
	    				if(!tneg.insertarTurno(dniMedico, fecha, i))
	    				{
	    					boolean error = true;
	    					request.setAttribute("error", error);

	    				}
	    				cont++;
	    			}

	    			boolean exito = true;
					request.setAttribute("exito", exito);
	    			
					request.setAttribute("cantTurnos", cont);
					request.setAttribute("apellidoMedico", m.getApellido());
					request.setAttribute("nombreMedico", m.getNombre());
					request.setAttribute("fecha", fecha);
					
					ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					request.setAttribute("listaMedicos", listaMedicos);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
					dispatcher.forward(request, response);
	        	}
	        	else
	        	{
	        		ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					request.setAttribute("listaMedicos", listaMedicos);
					
	        		boolean errorFechaOcupada = true;
					request.setAttribute("errorFechaOcupada", errorFechaOcupada);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
					dispatcher.forward(request, response);
	        	}
	        

	        }
	        else
        	{
	        	ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
        		boolean errorDia = true;
				request.setAttribute("errorDia", errorDia);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
				dispatcher.forward(request, response);
        	}
		}
		
		if(request.getParameter("btnAsistio") != null) {
			
			int idTurno = Integer.parseInt(request.getParameter("idTurno"));
			String observacion = request.getParameter("txtObservacion");

			tneg.ActualizarEstadoTurnoAsistio(idTurno, observacion);
			
			Usuario u = (Usuario) request.getSession().getAttribute("usuario");
			Medico m = mneg.ListarUno(u.getDNI());
			
			ArrayList<Turno> listaturnos = tneg.ListaTurnosPorMedico(m);
			request.setAttribute("listaTurnos", listaturnos);
			
			boolean exito = true;
			request.setAttribute("actualizado", exito);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ListaTurno.jsp");
			dispatcher.forward(request, response);

		}
		
	}

}

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
import negocio.HorarioNegocio;
import negocio.MedicoNegocio;
import negocio.TurnoNegocio;
import negocioImpl.HorarioNegocioImpl;
import negocioImpl.MedicoNegocioImpl;
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
			
			String mensajeDeActualizacion = "";
			
			if(request.getParameter("dni") == null || request.getParameter("dni") == "") 
			{
				mensajeDeActualizacion = "Por favor, ingrese un DNI.";
				
				ArrayList<Medico> listaMedicos = mneg.ListarTodos();
				request.setAttribute("listaMedicos", listaMedicos);
				
				ArrayList<Turno> lista = tneg.ListarTodos();
				request.setAttribute("listaTurnosPorAsignar", lista);
				
				request.setAttribute("mensajeDeActualizacionDeTurno", mensajeDeActualizacion);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AsignarTurno.jsp");
		    	dispatcher.forward(request, response);
			}

			Persona paciente = new Persona();
			paciente.setDNI((Integer.parseInt(request.getParameter("dni"))));
			
			Turno t = new Turno();
			t.setIdTurno((Integer.parseInt(request.getParameter("idTurno"))));
			t.setPaciente(paciente);
			t.setEstado(1);
			
			boolean estado = tneg.ActualizarTurno(t);
			
			
			if(estado == true) {
				mensajeDeActualizacion = "Se asignï¿½ el paciente al turno exitosamente.";
			}
			else {
				mensajeDeActualizacion = "No se pudo asignar el turno. Verifique que el DNI ingresado sea vï¿½lido.";
			}
			
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
			
			ArrayList<Medico> listaMedicos = mneg.ListarTodos();
			request.setAttribute("listaMedicos", listaMedicos);
			System.out.println(Integer.parseInt(request.getParameter("Medicos")));
			
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
		
		//Chequear si la fecha corresponde al dï¿½a de atenciï¿½n + que ese dï¿½a ya no tenga turnos
		if(request.getParameter("btnChequear") != null) 
		{
			int dniMedico = (int) request.getSession().getAttribute("dniMedico");
			String dia = request.getParameter("DiaAtencion");
			LocalDate fecha = LocalDate.parse(request.getParameter("FechaTurno"));

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", new Locale("es"));
	        String dayOfWeek = fecha.format(formatter);
	        
	        // Chequeamos si el día que atiende el medico es igual al dia de la semana de la fecha
	        
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
	    			}
	    			
	    			boolean exito = true;
					request.setAttribute("exito", exito);
					
					ArrayList<Medico> listaMedicos = mneg.ListarTodos();
					request.setAttribute("listaMedicos", listaMedicos);
					
					//Borramos el dni del medico de la session
					request.getSession().removeAttribute("dniMedico");
					
	    			RequestDispatcher dispatcher = request.getRequestDispatcher("/CrearTurno.jsp");
					dispatcher.forward(request, response);
	        	}
	        	else
	        	{
	        		boolean errorFechaOcupada = true;
					request.setAttribute("errorFechaOcupada", errorFechaOcupada);
					
					int dni = (int)request.getSession().getAttribute("dniMedico");

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
	        }
	        else
        	{
        		boolean errorDia = true;
				request.setAttribute("errorDia", errorDia);
				
				int dni = (int)request.getSession().getAttribute("dniMedico");
				System.out.println(dni);

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
		}


	}

}

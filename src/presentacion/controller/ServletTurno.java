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
import entidad.Medico;
import entidad.Persona;
import entidad.Turno;
import negocio.MedicoNegocio;
import negocio.TurnoNegocio;
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
				mensajeDeActualizacion = "Se asigno el paciente al turno exitosamente.";
			}
			else {
				mensajeDeActualizacion = "No se pudo asignar el turno. Verifique que el DNI ingresado sea v�lido.";
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
	}

}

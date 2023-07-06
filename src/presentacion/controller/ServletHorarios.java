package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Horario;
import entidad.Medico;
import negocio.HorarioNegocio;
import negocioImpl.HorarioNegocioImpl;

@WebServlet("/ServletHorarios")
public class ServletHorarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HorarioNegocio hNeg = new HorarioNegocioImpl();
	
    public ServletHorarios() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnModificarHorario") != null) 
		{
			int dni =Integer.parseInt(request.getParameter("dniMedico"));
			
			if(hNeg.ListarTodos(dni) !=null) 
			{
				ArrayList<Horario> listaHorario = hNeg.ListarTodos(dni);
				request.setAttribute("listaHorarios", listaHorario);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/ABMHorarios.jsp");
				dispatcher.forward(request, response);
			}	
		}
		
		if(request.getParameter("btnEliminarH") != null)
		{
			
			int idHorario = Integer.parseInt(request.getParameter("idHorario"));
			System.out.println(idHorario);
			boolean eliminando = true;
			
			eliminando = hNeg.EliminarHorario(idHorario); 
			
			request.setAttribute("eliminando", eliminando);
			RequestDispatcher rd = request.getRequestDispatcher("/AdminMedicos.jsp");
			rd.forward(request, response);			
		}
	}

}

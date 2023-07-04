package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import negocio.TurnosXEspNegocio;
import negocioImpl.TurnosXEspNegocioImpl;
import entidad.ReporteTurnosXEsp;
import entidad.ReporteTurnosXMed;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	TurnosXEspNegocio rneg = new TurnosXEspNegocioImpl();

    public ServletReportes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("Param")!=null)
		{
			ArrayList <ReporteTurnosXEsp> reporteEsp = rneg.obtenerReporte();
			request.setAttribute("reporteTurnosXEsp", reporteEsp);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Reportes.jsp");
			dispatcher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

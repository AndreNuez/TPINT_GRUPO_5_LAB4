<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="entidad.Usuario"%>
   <%@page import="java.util.ArrayList"%>
   <%@page import="java.util.List"%>
   <%@page import="entidad.ReporteTurnosXEsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Reportes y Estadisticas</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    
    <!-- DataTable -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    
    <script type="text/javascript">
        $(document).ready(function() {
            $('#miTabla').DataTable();
        });
    </script>
</head>
<body>
    <!-- Header -->
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="navbar-brand" href="ServletUsuario?Param=1">
                            <img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Men� Principal
                        </a>
                    </li>
                </ul>
			<% Usuario a = (Usuario) session.getAttribute("usuario"); %>
			<ul class="text-end" style="margin: 5px 20px"> <b> DNI Usuario actual:</b> <%= a.getDNI() %> </ul>
                <form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
            </div>
        </div>
    </nav>
    <br>
	<%
		List<ReporteTurnosXEsp> reporte1 = new ArrayList<ReporteTurnosXEsp>();
		if (request.getAttribute("reporteTurnosXEsp") != null) {
			reporte1 = (List<ReporteTurnosXEsp>) request.getAttribute("reporteTurnosXEsp");
		}
	%>
    <!-- Tabla y botones -->
    <div class="container">
    <div class="text-center">
   		<h1>Reportes y Estadisticas</h1>
    </div>
    <div >
   		<h3>Turnos por Especialidad</h3>
    </div>
        <div class="row">
            <div class="col-4"></div>
            <br>
            <div>
                <table class="table table-striped" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                            <th>Especialidad</th>
                            <th>Cantidad</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
			for (ReporteTurnosXEsp r : reporte1) {

					%>	
                        <tr>
                            <td><%=r.getEspecialidad().getDescripcion()%></td>
                            <td><%=r.getCantidad()%></td>
                            <td></td>
                        </tr>

                        <% } %>
                    </tbody>
                    
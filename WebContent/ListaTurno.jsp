<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Turno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Turnos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
                            <img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menú Principal
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

<% List<Turno> listaT = new ArrayList<Turno>();
if (request.getAttribute("listaTurnos") != null) {
	listaT = (List<Turno>) request.getAttribute("listaTurnos");
} %>

	

    <!-- Tabla de Turnos -->
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Listado de Turnos</h1>
                <table class="table table-striped dataTable" style="margin: 0 auto;" id="miTabla">
                    <thead>
                        <tr>
                        	<th>ID Turno</th>
                            <th>Apellido</th>
                            <th>Nombre</th>
                            <th>DNI</th>
                            <th>Fecha y hora del Turno</th>
                            <th>Observaciones</th>
                            <th>Confirmar Asistencia</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    <%for (Turno t : listaT) { %>
                    
                        <tr>
                        	<form method="post" action="ServletTurno">
                        	<td><%= t.getIdTurno() %> <input type="hidden" name = "idTurno" value = <%=t.getIdTurno()%>></td>
                            <td><%= t.getPaciente().getApellido() %> </td>
                            <td><%= t.getPaciente().getNombre() %> </td>
                            <td><%= t.getPaciente().getDNI() %> </td>
                            <td><%= t.getFecha() %>  -  <%= t.getHora() %> Hs.</td>
                            <td><input type="text" class="form-control" id="observacion" name="txtObservacion" autofocus></td>
                            <td><input type="submit" name="btnAsistio" value="Asistió" class="btn btn-primary" onclick="return confirm('¿Está seguro que desea actualizar este turno como paciente presente?')"></td>
                            <td><input type="submit" name="btnAusente" value="Ausente" class="btn btn-danger"/></td>
                        	</form>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
     <div class="container">
        <div class="row">
        <h4>Filtrar turnos por fecha</h4>
        	<div class="col-2">
        		<div class="mb-2">
                	<label for="FInicio">Desde:</label>
					<input type="date" name="FInicio">
            	</div>
        	</div>
        	<div class="col-2">
        		<div class="mb-2">
                	<label for="FFin">Hasta:</label>
					<input type="date" name="FFin">
            	</div>
        	</div>
        	<div class="col-2">
        		<div class="mb-2">
					<input type="submit" name="btnBuscarxFecha" value="Aceptar" class="btn btn-primary">
            	</div>
        	</div>
        </div>
        <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>DNI</th>
                            <th>Fecha del Turno</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Juan</td>
                            <td>Pérez</td>
                            <td>12345678</td>
                            <td>12/06/2023</td>
                            <td>Presente</td>
                        </tr>
                        <tr>
                            <td>María</td>
                            <td>Gómez</td>
                            <td>98765432</td>
                            <td>15/06/2023</td>
                            <td>Ocupado</td>
                        </tr>
                        <tr>
                            <td>Pablo</td>
                            <td>Rodríguez</td>
                            <td>45678901</td>
                            <td>18/06/2023</td>
                            <td>Ocupado</td>
                        </tr>
                    </tbody>
                </table>
     </div>

<!-- Alerta asistio ok -->
 	<%
		if (request.getAttribute("actualizado") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Turno actualizado con exito");
		} 
		</script> 
	<%
		}
	%>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>

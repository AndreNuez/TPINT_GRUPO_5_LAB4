<%@page import="entidad.Persona"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Boostrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

<!-- DataTable -->
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

	<%
		List<Persona> listaP = new ArrayList<Persona>();
		if (request.getAttribute("listaPacientes") != null) {
			listaP = (List<Persona>) request.getAttribute("listaPacientes");
		}
	%>

<!-- Header -->
	<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="navbar-brand" href="PrincipalAdmin.jsp"> 
					<img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menu Principal
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
	
<!-- Tabla y botones -->	

<div class="container">
<h4>Lista de pacientes</h4> <hr> 	
  <div class="row">
    <div class="col-4"></div>
    <div class="text-center">
         <a href="ServletPacientes?Param=agregarNuevo" class="btn btn-primary">Agregar Nuevo</a>
  	</div>
  	<br>
  	<br>
    <div>
	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
		<thead>
			<tr>
				<th>DNI</th>
				<th>Nombre</th>
				<th>Apellido</th>
				<th>Sexo</th>
				<th>Fecha de Nacimiento</th>
				<th>Mail</th>
				<th>Telefono</th>
				<th>Estado</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<%
			for (Persona p : listaP) {

		%>	
		<tr>
			<form method="post" action="ServletPacientes">
			<td><%=p.getDNI()%> <input type="hidden" name = "dniPaciente" value = <%=p.getDNI()%>></td>
			<td><%=p.getNombre()%></td>
			<td><%=p.getApellido()%></td>
			<td><%=p.getSexo()%></td>
			<td><%=p.getFnac()%></td>
			<td><%=p.getMail()%></td>
			<td><%=p.getTelefono()%></td>
			<td><%=p.getEstado()%></td>
			<td> <input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"> </td>
			<td> <input type="submit" value="Eliminar" name="btnEliminar" class="btn btn-danger"/> </td>
			
			</form>
		</tr>
		<%
			}
		%>

		<% if(request.getAttribute("eliminando") != null) {
			%>
			<div class="row" height=100px>
			<div class="col-3"></div>
			<div class="col-3">
				<h3 align="center">Desea eliminar el paciente?</h3> 
				</div>
			<div class="col-1"><a href="ServletPacientes?Param=confirmarSi" class="btn btn-danger"> Si </a></div>
			<div class="col-1"><a href="ServletPacientes?Param=confirmarNo" class="btn btn-primary"> No </a></div>
			</div>
			<% } %>

	</tbody>
	</table>
</div>
<div class="col-4"></div>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
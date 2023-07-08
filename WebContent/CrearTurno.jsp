<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Horario"%>

<!-- Librerias -->
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>

<!-- Header -->
	<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="navbar-brand" href="PrincipalAdmin.jsp"> 
					<img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menú Principal
					</a>
				</li>
			</ul>

			<% Usuario a = (Usuario) session.getAttribute("usuario"); %>
			<ul class="text-end" style="margin: 5px 20px"><b> DNI Usuario actual: </b> <%= a.getDNI() %> </ul>

			<form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
		</div>
	</div>
	</nav>
	<br>


<!-- Llamado a listados -->
	
	<%
		ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
		if (request.getAttribute("listaMedicos") != null) 
		{
			listaMedicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
		}
		
		ArrayList<Horario> listaH = new ArrayList<Horario>();
		if (request.getAttribute("listaHorarios") != null) {
			listaH = (ArrayList<Horario>) request.getAttribute("listaHorarios");
		}
		
		Medico medico = new Medico();
		if (request.getAttribute("verDatos") != null) {
			medico = (Medico)request.getAttribute("verDatos");
		}
		
	%>
	
	
<!-- Formulario y controles --> 
 <div class="container">
 <% int dniMedico = 0;
 %>
 
 <h4>Crear turno</h4> <hr>
 <form action="ServletTurno" method="post">
    <div class="row justify-content-center g-4">
        <div class="col-md-4">
            <div class="mb-2">
				<label for="Medicos">Medico: </label>
					<select name="Medicos" required>
						<% for (Medico m : listaMedicos) {
      				if (request.getAttribute("verDatos") != null && m.getDNI() == medico.getDNI()) {%>
        			<option value="<%=medico.getDNI() %>" selected><%=medico.getNombre()+" "+medico.getApellido()%></option>
        		<%} else {%>
        			<option value="<%=m.getDNI()%>"><%=m.getNombre()+" "+m.getApellido()%></option>
        		<%}}%>
				<input type="submit" name="btnBuscar" value="Buscar" class="btn btn-primary">
            </div>

    <!-- Si se hizo click en buscar, se traen datos necesarios para crear turnos. -->
    
    <%if ((request.getAttribute("verDatos") != null) && (request.getAttribute("listaHorarios") != null)) {%>
    
            <div class="mb-2">
				<label for="Especialidad"> <b>Especialidad:</b> <%=medico.getEspecialidad().getDescripcion()%></label>
            </div>
            <div class="mb-2">
                <label for="Horario"> <b>Día y horario de atención:</b></label>
                <table class="table">       		
				<tr>
					<th>Dia</th>
					<th>Desde</th>
					<th>Hasta</th>
				</tr>
				<tbody>
			<% 
				for (Horario h : listaH) { 
			%>
				<tr>
					<td> <%=h.getDiaAtencion()%></td>
					<td> <%=h.getHoraInicio()%> </td>
					<td> <%=h.getHoraFin()%> </td>
	          	</tr>
			<% } %>
				</tbody>
				</table>
            </div>
            <div class="mb-2">
				<label for="DiaAtencion"><b>Dia de turno:</b></label>
				<select name="DiaAtencion" required>
					<option value="">Seleccionar opcion...</option>
					<% 
						for (Horario h : listaH) { 
					%>
					<option> <%=h.getDiaAtencion()%> </option>
					<% } %>
				</select>
            </div>
            <div class="mb-2">
            	<label for="FechaTurno"><b>Fecha del turno:</b></label>
				<input type="date" name="FechaTurno" min="<%=LocalDate.now() %>" required>
			</div>
			 <div class="mb-2">
				<input type="submit" name="btnChequear" value="Chequear Disponibilidad" class="btn btn-primary"> </input>
            </div>
   <%} %>
   
   
            <div>
            	<br>
            	<b>---> Se crearán x turnos para xx médico en el día xx/xx/xxxx</b>
            </div>
        </div>

    <div class="row justify-content-center g-4">
        <div class="col-md-4">
        	<input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        	<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary" style="margin-left: 50px"> </input>

        </div>
    </div>
    </form>   
</div>

	<%
		if (request.getAttribute("exito") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Turnos agregados con exito");
		} 
		</script> 
	<%
		}
	%>
	<%
		if (request.getAttribute("errorDia") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("El medico no trabaja ese dia");
		} 
		</script> 
	<%
		}
	%>
	
	<%if (request.getAttribute("errorFechaOcupada") != null) {
	%>
		<script type="text/javascript">
		function alertName(){
		alert("Ya se han creado turnos para ese medico ese dia");
		} 
		</script> 
	<%
		}
	%>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>

</body>
</html>
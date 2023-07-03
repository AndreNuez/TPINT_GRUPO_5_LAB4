<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Entidades -->
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Horario"%>

<!-- Librerias -->
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate" %>
 
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
			<ul class="text-end" style="margin: 5px 20px"> <b> DNI Usuario actual:</b> <%= a.getDNI() %> </ul>
			<form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
		</div>
	</div>
	</nav>
	<br>
<!-- Llamado a listados  -->

	<%
		ArrayList<Provincia> listaProv = new ArrayList<Provincia>();
		if (request.getAttribute("listaProv") != null) {
			listaProv = (ArrayList<Provincia>) request.getAttribute("listaProv");
		}
		
		ArrayList<Localidad> listaLoc = new ArrayList<Localidad>();
		if (request.getAttribute("listaLoc") != null) {
			listaLoc = (ArrayList<Localidad>) request.getAttribute("listaLoc");
		}
		
		ArrayList<Especialidad> listaEsp = new ArrayList<Especialidad>();
		if (request.getAttribute("listaEsp") != null) {
			listaEsp = (ArrayList<Especialidad>) request.getAttribute("listaEsp");
		}
		
		ArrayList<Horario> listaH = new ArrayList<Horario>();
		if (request.getAttribute("listaHorarios") != null) {
			listaH = (ArrayList<Horario>) request.getAttribute("listaHorarios");
		}
		
		Medico medico = new Medico();
		if (request.getAttribute("verMedico") != null) {
			medico = (Medico)request.getAttribute("verMedico");
		}
		
		if (request.getAttribute("modificarMedico") != null) {
			medico = (Medico)request.getAttribute("modificarMedico");
		}
		
		String esMasculino = medico.getSexo() == 'M' ? "checked" : "";
		String esFemenino = medico.getSexo() == 'F' ? "checked" : "";
		
	%>
	
<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del médico</h4> <hr>
 
 <!-- Si doy click en AgregarNuevo, cargo todos los campos vacíos --> 

<!-- Si doy click a VerCompleto, solo veo los datos como lbl -->	
	
<% if (request.getAttribute("verMedico") != null) {%>
    <div class="row justify-content-center g-4">
        <div class="col-md-4">
            <div class="mb-2">
                <label for="DNI"><b>DNI:</b> <%=medico.getDNI() %> </label>
            </div>
            <div class="mb-2">
                <label for="nombre"><b>Nombre:</b> <%=medico.getNombre() %></label>
            </div>
            <div class="mb-2">
               <label for="Apellido"><b>Apellido:</b> <%=medico.getApellido() %></label>
            </div>
            <div class="mb-2">
                <label for="Sexo"><b>Sexo:</b></label>
				<input type="radio" name="Sexo" value="Femenino" disabled <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" disabled <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="Nacionalidad"><b>Nacionalidad:</b> <%=medico.getNacionalidad() %></label>
            </div>
            <div class="mb-2">
                <label for="FNac"><b>Fecha de Nacimiento:</b> <%=medico.getFnac() %></label>
            </div>
            <div class="mb-2">
                <label for="Mail"><b>Mail:</b> <%=medico.getMail() %></label>
            </div>
            <div class="mb-2">
				<label for="Telefono"><b>Teléfono:</b> <%=medico.getTelefono() %></label>
            </div>
            <div class="mb-2">
				<label for="Especialidad"><b>Especialidad:</b> </label>
				<select name="Especialidad" disabled>
					<option value="<%=medico.getEspecialidad().getIdEspecialidad() %>" selected><%=medico.getEspecialidad().getDescripcion() %> </option>
				</select>
            </div>
        </div>

        <div class="col-md-4">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle"><b>Calle:</b> <%=medico.getDireccion().getCalle() %></label>
			</div>
			<div class="mb-2">
				<label for="Numero"><b>Numero:</b> <%=medico.getDireccion().getNumero() %></label>
            </div>
            <div class="mb-2">
				<label for="Provincia"><b>Provincia:</b> </label>
				<select name="Provincias" disabled >
					<option value= "<%=medico.getDireccion().getProvincia().getIDProvincia()%>" selected> <%=medico.getDireccion().getProvincia().getDescripcion() %> </option>
				</select>
            </div>
            <div class="mb-2">
				<label for="Localidad"><b>Localidad:</b></label>
				<select name="Localidades" disabled >
					<option value= "<%=medico.getDireccion().getLocalidad().getIDLocalidad() %>" selected> <%=medico.getDireccion().getLocalidad().getDescripcion() %> </option>
				</select>
            </div>
            </div>
            <br>
       <div class="col-md-4">
           <h5>Dias y horarios de atencion</h5> <hr>  
          
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
				<td> <%=h.getDiaAtencion() %></td>
				<td> <%=h.getHoraInicio() %></td>
				<td> <%=h.getHoraFin() %></td>
          	</tr>
			<%}%>
			</tbody>
			</table>
			<input type="submit" name="btnNuevoHorario" value="Agregar Nuevo" class="btn btn-primary"> </input>	
       </div>     
    </div>
    <div class="row justify-content-center g-4">
        <div class="col-auto">
        <div>
        <br><br>
        <form action="ServletMedicos" method="post">
            	<input type="hidden" name="dniMedico" value= <%=medico.getDNI()%>>
				<input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
			<a href="ServletMedicos?Param=list" class="btn btn-info">Regresar</a>
			</form>	
        </div>
        </div>
    </div>
<%} %>   

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
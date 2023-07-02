<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Importamos clases -->
<!-- Entidades -->
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Usuario"%>

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

		Persona paciente = new Persona();
		if (request.getAttribute("verPaciente") != null) {
			paciente = (Persona)request.getAttribute("verPaciente");
		}
		
		
		if (request.getAttribute("ModificarPaciente") != null) {
			paciente = (Persona)request.getAttribute("ModificarPaciente");
		}

		String esMasculino = paciente.getSexo() == 'M' ? "checked" : "";
		String esFemenino = paciente.getSexo() == 'F' ? "checked" : "";

	%>

<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del paciente</h4> <hr>
 
 <!-- Si doy click en AgregarNuevo, cargo todos los campos vac�os -->
 
 <% if ((request.getAttribute("verPaciente") == null) && (request.getAttribute("ModificarPaciente") == null)) { %>

	<form action="ServletPacientes" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>       
				<input type="text"  name="txtDNI" maxlength="8" placeholder="DNI" required>
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name="txtNombre" placeholder="Nombre" required>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name="txtApellido" placeholder="Apellido" required>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" required> Femenino
				<input type="radio" name="Sexo" value="Masculino" required> Masculino
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label>
				<input type="date" name="FNac" max="<%= LocalDate.now() %>" required>
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
				<input type="text" name="txtNacionalidad" placeholder="Nacionalidad" required>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
				<input type="email" name="txtMail" placeholder="Mail" required>		
            </div>
            <div class="mb-2">
				<label for="Telefono">Telefono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" required>
            </div>
		</div>
        <div class="col-6">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
				<input type="text" name="txtCalle" placeholder="Calle" required>
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Numero" required>
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" required>
					<option value="">Seleccionar opcion...</option>
					<%
						for (Localidad l : listaLoc) {
					%>
					<option value="<%=l.getIDLocalidad()%>"><%=l.getDescripcion()%></option>
					<%}%>
				</select>
            </div>
            <br>
          </div>         
    </div>
    <div class="row">
        <div class="col-auto">
        	<input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        <br><br>
        <div>
			<input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary"> </input>	
			<a href="ServletPacientes?Param=list" class="btn btn-info">Regresar</a>
        </div>
        </div>
    </div>
    </form>
    <%} %>
 
 <!-- Si doy click a VerCompleto, solo veo los datos como lbl -->	
 
 <% if (request.getAttribute("verPaciente") != null) {%>

    <div class="row justify-content-center g-4">
        <div class="col-3">
            <div class="mb-2">
                <label for="DNI"> <b>DNI:</b> <%=paciente.getDNI() %></label>
            </div>
            <div class="mb-2">
                <label for="Nombre"> <b>Nombre:</b> <%=paciente.getNombre() %></label>
            </div>
            <div class="mb-2">
               <label for="Apellido"><b>Apellido:</b> <%=paciente.getApellido() %></label>
            </div>
            <div class="mb-2">
                <label for="Sexo"><b>Sexo:</b></label>
				<input type="radio" name="Sexo" value="Femenino" disabled <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" disabled <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="FNac"><b>Fecha de Nacimiento:</b> <%=paciente.getFnac() %></label>
            </div>
            <div class="mb-2">
                <label for="Nacionalidad"><b>Nacionalidad:</b> <%=paciente.getNacionalidad() %></label>
            </div>
            <div class="mb-2">
                <label for="Mail"><b>Mail:</b> <%=paciente.getMail() %></label>	
            </div>
            <div class="mb-2">
				<label for="Telefono"><b>Telefono:</b> <%=paciente.getTelefono() %></label>
            </div>
		</div>
        
        <div class="col-3">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle"><b>Calle:</b> <%=paciente.getDireccion().getCalle() %></label>
			</div>
			<div class="mb-2">
				<label for="Numero"><b>Numero:</b> <%=paciente.getDireccion().getNumero() %></label>
            </div>
            <div class="mb-2">
				<label for="Procincia"><b>Provincia:</b> </label>
				<select name="Provincias" disabled >
					<option value="<%=paciente.getDireccion().getProvincia().getIDProvincia()%>" selected><%=paciente.getDireccion().getProvincia().getDescripcion()%></option>
				</select>
            </div>
            <div class="mb-2">
				<label for="Localidad"><b>Localidad:</b> </label>
				<select name="Localidades" disabled >
					<option value="<%=paciente.getDireccion().getLocalidad().getIDLocalidad()%>" selected><%=paciente.getDireccion().getLocalidad().getDescripcion()%></option>
				</select>
            </div>
            <br>
          </div>         
    </div>
    <div class="row justify-content-center g-4">
        <div class="col-auto">
        <div>
            <br><br>
 			<form action="ServletPacientes" method="post">
            	<input type="hidden" name="dniPaciente" value= <%=paciente.getDNI()%>>
				<input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
			<a href="ServletPacientes?Param=list" class="btn btn-info">Regresar</a>
			</form>	
        </div>
        </div>
    </div>
    <% } %> 
 
 
<% if (request.getAttribute("ModificarPaciente") != null) { %>

<!-- Si hago click en Modificar, se renderiza y muestra las cajas de txt para modificar -->
	<form action="ServletPacientes" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>       
				<input type="text"  name="txtDNI" maxlength="8" placeholder="DNI" required value=<%=paciente.getDNI() %> readonly=true style="background-color: #f2f2f2">
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name="txtNombre" placeholder="Nombre" required value=<%=paciente.getNombre() %>>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name="txtApellido" placeholder="Apellido" required value=<%=paciente.getApellido() %>>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" required <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" required <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label>
				<input type="date" name="FNac" max="<%= LocalDate.now() %>" required value=<%=paciente.getFnac() %>>
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
				<input type="text" name="txtNacionalidad" placeholder="Nacionalidad" required value=<%=paciente.getNacionalidad() %>>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
				<input type="email" name="txtMail" placeholder="Mail" required value=<%=paciente.getMail() %>>		
            </div>
            <div class="mb-2">
				<label for="Telefono">Telefono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" required value=<%=paciente.getTelefono() %>>
            </div>
		</div>
        <div class="col-6">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
				<input type="text" name="txtCalle" placeholder="Calle" value=<%=paciente.getDireccion().getCalle() %>>
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Numero" value=<%=paciente.getDireccion().getNumero() %>>
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" >
				 <% for (Localidad l : listaLoc) {
      				if (request.getAttribute("ModificarPaciente") != null && l.getIDLocalidad() == paciente.getDireccion().getLocalidad().getIDLocalidad()) {%>
        			<option value="<%=l.getIDLocalidad()%>" selected><%=l.getDescripcion()%></option>
        		<%} else {%>
        			<option value="<%=l.getIDLocalidad()%>"><%=l.getDescripcion()%></option>
        		<%}}%>
				</select>
            </div>
            <br>
          </div>         
    </div>
    <div class="row">
        <div class="col-auto">
        <br><br>
        <div>
		 <% if (request.getAttribute("verPaciente") != null) {%>
				<input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
				<%} else {%>
					<input type="submit" name="btnConfirmar" value="Confirmar" class="btn btn-primary"> </input>
				<%}%>	
		
		<a href="ServletPacientes?Param=list" class="btn btn-info">Regresar</a>
        </div>
        </div>
    </div>
    </form>
    <%} %>

<!-- Pregunto por estado para chequear si se inserto. -->    
    <%
		if (request.getAttribute("estadoPaciente") != null && request.getAttribute("estadoDP") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Paciente agregado con exito");
		} 
		</script> 
	<%
		}
	%>
	
	<!-- Pregunto por estado para chequear si se modifico. -->    
    <%
		if (request.getAttribute("modificado") != null && request.getAttribute("modificadoDP") != null) {
	%>
	<script type="text/javascript">
		function alertName(){
		alert("Paciente modificado con exito");
		} 
		</script> 
	<%
		}
	%>
       
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
<script type="text/javascript"> window.onload = alertName; </script>
</body>
</html>
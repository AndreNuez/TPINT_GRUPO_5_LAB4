<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Entidades -->
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Especialidad"%>

<!-- Librerias -->
<%@page import="java.util.ArrayList"%>
 
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
		
		Medico medico = new Medico();
		String esMasculino = medico.getSexo() == 'M' ? "checked" : "";
		String esFemenino = medico.getSexo() == 'F' ? "checked" : "";
		
	%>
	
<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del médico</h4> <hr>
 <form action="" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>
				<input type="text" name="txtDNI" maxlength="8" placeholder="DNI" required>
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name=txtNombre" placeholder="Nombre" required>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name=txtApellido" placeholder="Apellido" required>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
				<input type="text" name="txtNacionalidad" placeholder="Nacionalidad" required>
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label>
				<input type="date" name="FNac" required>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
				<input type="email" name="txtMail" placeholder="Mail" required>
            </div>
            <div class="mb-2">
				<label for="Telefono">Teléfono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" required>
            </div>
            <div class="mb-2">
				<label for="Especialidad">Especialidad:</label>
				<select name="Especialidad" required>
					<%
							for (Especialidad e : listaEsp) {
						%>
						<option value="<%=e.getIdEspecialidad()%>"><%=e.getDescripcion()%></option>
						<%
							}
						%>
				</select>
            </div>
        </div>

        <div class="col-6">
        <h5>Dirección</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
				<input type="text" name="txtCalle" placeholder="Calle" required>
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Número" required>	
            </div>
            <div class="mb-2">
				<label for="Procincia">Provincia:</label>
				<select name="Provincias" >
					<%
							for (Provincia p : listaProv) {
						%>
						<option value="<%=p.getIDProvincia()%>"><%=p.getDescripcion()%></option>
						<%
							}
						%>
				</select>
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" >
					<%
							for (Localidad l : listaLoc) {
						%>
						<option value="<%=l.getIDLocalidad()%>"><%=l.getDescripcion()%></option>
						<%
							}
						%>
				</select>
            </div>
            <br>
           <h5>Día y horario de atención</h5> <hr>  
          <div class="mb-2">
				<label for="Dia">Dia:</label>
				<select name="Dia">
					<option> Lunes </option>
					<option> Martes </option>
					<option> Miércoles </option>
					<option> Jueves </option>
					<option> Viernes </option>
				</select>
            </div>
            <div class="mb-2">
                <label for="Desde">De:</label>
				<input type="number" name="txtDesde" min="7" max="13" required>
 
                <label for="Hasta">A:</label>
				<input type="number" name="txtHasta" min="14" max="20" required>
            </div>
          </div>         
    </div>
    <div class="row">
        <div class="col-auto">
        <input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        <br><br>
        <div>
        <input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary"> </input>
        
        <!-- Si el llamado se hace desde opción Ver Completo, se cargan los datos del médico readonly y se habilita 
        	btnModificar-->
        <input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
        </div>
        </div>
    </div>
    </form>   
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- Importamos clases -->
<!-- Entidades -->
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Persona"%>

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
					<img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Men� Principal
					</a>
				</li>
			</ul>
			<ul class="text-end" style="margin: 5px 20px"> Usuario </ul>
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
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

		Boolean readOnly = (request.getParameter("verPaciente")) != null;
		String esMasculino = paciente.getSexo() == 'M' ? "checked disabled" : "";
		String esFemenino = paciente.getSexo() == 'F' ? "checked disabled" : "";

	%>

<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del paciente</h4> <hr>
 <form action="ServletPacientes" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>
				<input type="text" name="txtDNI" maxlength="8" placeholder="DNI" required <%=paciente.getDNI() %> readonly=<%=readOnly%>>
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name="txtNombre" placeholder="Nombre" required <%=paciente.getNombre() %> readonly=<%=readOnly%>>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name="txtApellido" placeholder="Apellido" required <%=paciente.getApellido() %> readonly=<%=readOnly%>>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino" <%=esFemenino%>> Femenino
				<input type="radio" name="Sexo" value="Masculino" <%=esMasculino%>> Masculino
            </div>
            <div class="mb-2">
                <label for="FNac">Fecha de Nacimiento:</label <%=paciente.getFnac() %> readonly=<%=readOnly%>>
				<input type="date" name="FNac" required>
            </div>
            <div class="mb-2">
                <label for="Nacionalidad">Nacionalidad:</label>
				<input type="text" name="txtNacionalidad" placeholder="Nacionalidad" required <%=paciente.getNacionalidad() %> readonly=<%=readOnly%>>
            </div>
            <div class="mb-2">
                <label for="Mail">Mail:</label>
				<input type="email" name="txtMail" placeholder="Mail" required <%=paciente.getMail() %> readonly=<%=readOnly%>>
            </div>
            <div class="mb-2">
				<label for="Telefono">Telefono:</label>
				<input type="tel" name="txtTelefono" placeholder="Telefono" required <%=paciente.getTelefono() %> readonly=<%=readOnly%>>
            </div>
		</div>
        <div class="col-6">
        <h5>Direccion</h5><hr>
            <div class="mb-2">
                <label for="Calle">Calle:</label>
				<input type="text" name="txtCalle" placeholder="Calle" <%=paciente.getDireccion().getCalle() %> readonly=<%=readOnly%>>
			</div>
			<div class="mb-2">
				<label for="Numero">Numero:</label>
				<input type="text" name="txtNumero" placeholder="Numero"<%=paciente.getDireccion().getNumero() %> readonly=<%=readOnly%>>	
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
          </div>         
    </div>
    <div class="row">
        <div class="col-auto">
        <input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        <br><br>
        <div>
        <input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary"> </input>
        
        <!-- Si el llamado se hace desde opcion Ver Completo, se cargan los datos del paciente readonly y se habilita 
        	btnModificar-->
        <input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
        </div>
        </div>
    </div>
    </form>

<!-- Pregunto por estado para chequear si se inserto. -->    
    <%
		if (request.getAttribute("estadoPaciente") != null && request.getAttribute("estadoDP") != null) {
	%>
	<h4>Paciente agregado con exito.</h4>
	<%
		}
	%>
       
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.Usuario"%>
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
			<% Usuario a = null;
				a= (Usuario) session.getAttribute("usuario"); %> 

			<ul class="text-end" style="margin: 5px 20px">
			    <b>DNI Usuario actual:</b>
			    <% if (a != null) { %>
			        <%= a.getDNI() %>
			    <% } %>
			</ul>		
			
			<form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
		</div>
	</div>
	</nav>
	<br>
<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Crear turno</h4> <hr>
 <form action="" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
				<label for="Medico">Médico:</label>
				<select name="Medicos" required>
					<option> Seleccione un médico... </option>
					<option> Antonio Gutiérrez </option>
					<option> Germán Sosa </option>
					<option> Lucia Vázquez </option>
					<option> Sofía Fernández </option>
				</select>
            </div>
            <div class="mb-2">
				<label for="Especialidad">Especialidad: Cardiólogo</label>
            </div>
            <div class="mb-2">
                <label for="nombre">Día y horario de atención: Lunes 17-20 PM</label>
            </div>
            <div class="mb-2">
				<label for="HorarioTurno">Horario de turno:</label>
				<select name="Provincias" required>
					<option> Elija un horario... </option>
					<option> 17 PM </option>
					<option> 18 PM </option>
					<option> 19 PM </option>
				</select>
            </div>
        </div>

    <div class="row">
        <div class="col-auto">
        <input type="reset" value="Restablecer" class="btn btn-secondary"> </input>
        <br><br>
        <div>
        <input type="submit" name="btnAceptar" value="Aceptar" class="btn btn-primary"> </input>
        </div>
        </div>
    </div>
    </form>   
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
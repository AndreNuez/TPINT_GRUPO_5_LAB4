<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
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
	<div class="row">
		<div class="col-4"></div>
		<div class="col">
			<div class="text-center">
				<br>
				<h2>Bienvenido Administrador</h2>
			</div>
			<div class="text-center text-muted">
				<p>Seleccione una opci�n:</p>
			</div>
			<div class="col-4"></div>
			<div class="col-2"></div>
			<br />
			<div class="d-grid mx-auto">
				<a href="PrincipalTurnos.jsp" class="btn btn-primary">Administrar Turnos</a> <br>
			</div>
			<div class="d-grid mx-auto">
				<a href="AdminPacientes.jsp" class="btn btn-primary">Administrar Pacientes</a> <br>
			</div>
			<div class="d-grid mx-auto">
				<a href="AdminMedicos.jsp" class="btn btn-primary">Administrar M�dicos</a> <br>
			</div>
			<br /> <br />
			<div class="col-4"></div>
			<div class="col-2"></div>
		</div>
		<div class="col-4"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
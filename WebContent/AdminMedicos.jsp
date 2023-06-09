<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<ul class="text-end" style="margin: 5px 20px"> Usuario </ul>
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
		</div>
	</div>
	</nav>
	<br>
	
<!-- Tabla y botones -->	

<div class="container">
  <div class="row">
    <div class="col-4"></div>
    <div class="text-center">
         <input type="submit" name="btnNuevoMedico" class="btn btn-primary" value="Agregar Nuevo"></input>
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
				<th>Mail</th>
				<th>Especialidad</th>
				<th>Día y Horario atención</th>
				<th>Estado</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>25789410</td>
				<td>Elissa</td>
				<td>Stoile</td>
				<td>Mujer</td>
				<td>estoile0@alexa.com</td>
				<td>Cardiología</td>
				<td>Jueves - 8 a 15hs</td>
				<td>Activo</td>
				<td><input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>4589630</td>
				<td>Emery</td>
				<td>Horsted</td>
				<td>Hombre</td>
				<td>ehorsted1@phpbb.com</td>
				<td>Nutrición</td>
				<td>Lunes - 10 a 18hs</td>
				<td>Activo</td>
				<td><input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>31589423</td>
				<td>Sharona</td>
				<td>Hubberstey</td>
				<td>Mujer</td>
				<td>shubberstey2@bloglines.com</td>
				<td>Clínica</td>
				<td>Martes - 13 a 19hs</td>
				<td>Activo</td>
				<td><input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>10258951</td>
				<td>Morey</td>
				<td>Pirouet</td>
				<td>Hombre</td>
				<td>mpirouet3@illinois.edu</td>
				<td>Traumatología</td>
				<td>Viernes - 10 a 19hs</td>
				<td>Activo</td>
				<td><input type="submit" value="Ver Completo" name="btnVer" class="btn btn-info"></td>
			</tr>

		</tbody>
	</table>
</div>
<div class="col-4"></div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
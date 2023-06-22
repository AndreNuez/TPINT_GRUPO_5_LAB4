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
			<<% Usuario a = (Usuario) session.getAttribute("usuario"); %>
			<ul class="text-end" style="margin: 5px 20px"><b> DNI Usuario actual: </b> <%= a.getDNI() %> </ul>
			<form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
		</div>
	</div>
	</nav>
	<br>
	
<!-- Tabla y botones -->	

<div class="container">
  <h4>Asignar turno</h4> <hr>
  <div class="mb-2">
				<select name="Provincias" required>
					<option> Seleccione un médico... </option>
					<option> Antonio Gutiérrez </option>
					<option> Germán Sosa </option>
					<option> Lucia Vázquez </option>
					<option> Sofía Fernández </option>
				</select>

				<select name="Especialidades" required>
					<option> Seleccione una especialidad... </option>
					<option> Cardiólogo </option>
					<option> Oftalmólogo </option>
				</select>

				<td><input type="submit" value="Filtrar" name="btnFilter" class="btn btn-info"></td>
  </div> 	
  <div class="row">
    <div class="col-4"></div>
  	<br>
  	<br>
    <div>
	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
		<thead>
			<tr>
				<th>Médico</th>
				<th>Especialidad</th>
				<th>Horario de atención</th>
				<th>Horario de turno</th>
				<th>DNI de paciente</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Sofia Fernández</td>
				<td>Cardiólogo</td>
				<td>Lunes 10-18 hs</td>
				<td>Lunes 14 hs</td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" required>
                </div>
                </td>
				<td><input type="submit" value="Asignar" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>Juan Gómez</td>
				<td>Oftalmólogo</td>
				<td>Martes 9-21 hs</td>
				<td>Martes 16 hs</td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" required>
                </div>
                </td>
				<td><input type="submit" value="Asignar" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>Brian Marincola</td>
				<td>Pediatra</td>
				<td>Martes 8-13 hs</td>
				<td>Martes 9 hs</td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" required>
                </div>
                </td>
				<td><input type="submit" value="Asignar" name="btnVer" class="btn btn-info"></td>
			</tr>
			<tr>
				<td>Julian Pérez</td>
				<td>Neurólogo</td>
				<td>Viernes 14-18 hs</td>
				<td>Viernes 15 hs</td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" required>
                </div>
                </td>
				<td><input type="submit" value="Asignar" name="btnVer" class="btn btn-info"></td>
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
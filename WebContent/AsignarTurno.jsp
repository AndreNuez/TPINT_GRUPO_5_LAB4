<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.Medico"%>
    <%@page import="entidad.Turno"%>
    <%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
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

<% 
String mensaje = "";
if(request.getAttribute("mensajeDeActualizacionDeTurno") != null)
{
	mensaje = (String)request.getAttribute("mensajeDeActualizacionDeTurno"); 
}
%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#miTabla').DataTable();
	});
	
	var mensaje = "<%=mensaje%>";
	if(mensaje != "")
	{
	    alert(mensaje);		
	}
	
</script>

</head>
<body>

	<%
		ArrayList<Turno> listaTurnosPorAsignar = new ArrayList<Turno>();
	
		if (request.getAttribute("listaTurnosPorAsignar") != null) {
			listaTurnosPorAsignar = (ArrayList<Turno>) request.getAttribute("listaTurnosPorAsignar");
		}
		
		if (request.getAttribute("listaTurnosPorMedico") != null) {
			listaTurnosPorAsignar = (ArrayList<Turno>) request.getAttribute("listaTurnosPorMedico");
		}
		
		ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
		if (request.getAttribute("listaMedicos") != null) {
			listaMedicos = (ArrayList<Medico>) request.getAttribute("listaMedicos");
		}
	%>

<!-- Header -->
	<nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="navbar-brand" href="ServletUsuario?Param=1">
					<img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Men� Principal
					</a>
				</li>
			</ul>
		    <%Usuario a = (Usuario)session.getAttribute("usuario"); %>
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
<form action="ServletTurno" method="post">
  <h4>Asignar turno</h4> <hr>
  <div class="mb-2">
				<select name="Medicos" required>
					<option value=0 selected> Seleccione un m�dico... </option>
					<% for (Medico m : listaMedicos) {%>
        			<option value="<%=m.getDNI()%>" ><%=m.getNombre()+" "+m.getApellido()%></option>
        			<%}%>
				</select>
				
				<input type="submit" value="Filtrar" name="btnFilter" class="btn btn-info">
				<input type="submit" value="Borrar Filtros" name="deleteFilters" class="btn btn-danger"/>
  </div> 
  <div class="row">
    <div class="col-4"></div>
  	<br>
  	<br>
    <div>
	<table class="table table-striped" style="margin: 0 auto;" id="miTabla">
		<thead>
			<tr>
				<th>ID de turno</th>
				<th>M�dico</th>
				<th>Especialidad</th>
				<th>Horario de turno</th>
				<th>DNI de paciente</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Turno t : listaTurnosPorAsignar) {

			%>
			<tr>
				<td><%=t.getIdTurno()%> <input type="hidden" name = "idTurno" value = <%=t.getIdTurno()%>></td>
				<td><%=t.getMedico().getNombre()+" "+t.getMedico().getApellido()%></td>
				<td><%=t.getMedico().getEspecialidad().getDescripcion()%></td>
				<td><%=t.getFecha()+"\n"+t.getHora()+"hs"%></td>
				<td>
				<div class="mb-3">
                        <input type="text" class="form-control" id="dni" name="dni" pattern="^[0-9]{8}$" autofocus title="Este campo solo admite un n�mero de 8 d�gitos.">
                </div>
                </td>
				<td><input type="submit" value="Asignar" name="btnAsignar" class="btn btn-info"></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
</div>
<div class="col-4"></div>
</div>
</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
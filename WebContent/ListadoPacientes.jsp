<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="entidad.Usuario"%>
   <%@ page import="auxiliares.ValidarUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listado de Pacientes</title>
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    
    <!-- DataTable -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
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
                        <a class="navbar-brand" href="ServletUsuario?Param=1">
                            <img src="https://icones.pro/wp-content/uploads/2021/03/symbole-du-docteur-icone-png-bleu.png" alt="Logo" width="30" height="30" class="d-inline-block align-text-top"> Menú Principal
                        </a>
                    </li>
                </ul>
				<% 
				    Usuario a = (Usuario) session.getAttribute("usuario"); 
				    
				    if (a == null) {
				        response.sendRedirect("Error.jsp"); 
				    } else {
				        boolean administrador = ValidarUsuario.validarUsuarioAdmin(a);
				    	boolean medico = ValidarUsuario.validarUsuarioMedico(a);
				        if (!administrador && !medico)
				            response.sendRedirect("Principal.jsp");
				    }
				%>
			<ul class="text-end" style="margin: 5px 20px"> <b> DNI Usuario actual:</b> <%= a.getDNI() %> </ul>
                <form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
            </div>
        </div>
    </nav>
    <br>

    <!-- Tabla y botones -->
    <div class="container">
        <div class="row">
            <div class="col-4"></div>
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
                            <th>Fecha de Nacimiento</th>
                            <th></th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <tr>
 							<form action="servletPersonas" method="get">
                            <td>Juan <!-- usuario.getDNI() <input type="hidden" name="DNIUsuario" value=" %= usuario.getDNI() =%"  --></td>
                            <td>Pérez</td>
                            <td>12345678</td>
                            <td>10/05/1980</td>
                            <td>12/06/2023</td>
                            <td><input type="radio" name="confirmacion" value="asistio"></td>
                            <td><input type="submit" value="Eliminar" name="btnEliminar""/></td>
                            </form>
                        </tr>
                        <tr>
                            <td>María</td>
                            <td>Gómez</td>
                            <td>98765432</td>
                            <td>05/12/1992</td>
                            <td>15/06/2023</td>
                            <td><input type="radio" name="confirmacion" value="asistio"></td>
                        </tr>
                        <tr>
                            <td>Pablo</td>
                            <td>Rodríguez</td>
                            <td>45678901</td>
                            <td>20/07/1985</td>
                            <td>18/06/2023</td>
                            <td><input type="radio" name="confirmacion" value="asistio"></td>
                        </tr>
                        <tr>
                            <td>Lucía</td>
                            <td>López</td>
                            <td>23456789</td>
                            <td>15/09/1990</td>
                            <td>20/06/2023</td>
                            <td><input type="radio" name="confirmacion" value="asistio"></td>
                        </tr>
                    </tbody>
                    
                    <tbody>

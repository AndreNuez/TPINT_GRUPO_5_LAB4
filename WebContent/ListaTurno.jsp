<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@page import="entidad.Usuario"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Turnos</title>
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
                <<form method="post" action="ServletUsuario">
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
			</form>
            </div>
        </div>
    </nav>
    <br>

    <!-- Tabla de Turnos -->
    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Listado de Turnos</h1>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>DNI</th>
                            <th>Fecha de Nacimiento</th>
                            <th>Fecha del Turno</th>
                            <th>Confirmar Asistencia</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Juan</td>
                            <td>Pérez</td>
                            <td>12345678</td>
                            <td>10/05/1980</td>
                            <td>12/06/2023</td>
                            <td><input type="radio" name="confirmacion" value="asistio"></td>
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
                </table>
            </div>
        </div>
    </div>
    
     <div class="container">
        <div class="row">
        <h4>Filtrar turnos por fecha</h4>
        	<div class="col-2">
        		<div class="mb-2">
                	<label for="FInicio">Desde:</label>
					<input type="date" name="FInicio">
            	</div>
        	</div>
        	<div class="col-2">
        		<div class="mb-2">
                	<label for="FFin">Hasta:</label>
					<input type="date" name="FFin">
            	</div>
        	</div>
        	<div class="col-2">
        		<div class="mb-2">
					<input type="submit" name="btnBuscarxFecha" value="Aceptar" class="btn btn-primary">
            	</div>
        	</div>
        </div>
        <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>DNI</th>
                            <th>Fecha del Turno</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Juan</td>
                            <td>Pérez</td>
                            <td>12345678</td>
                            <td>12/06/2023</td>
                            <td>Presente</td>
                        </tr>
                        <tr>
                            <td>María</td>
                            <td>Gómez</td>
                            <td>98765432</td>
                            <td>15/06/2023</td>
                            <td>Ocupado</td>
                        </tr>
                        <tr>
                            <td>Pablo</td>
                            <td>Rodríguez</td>
                            <td>45678901</td>
                            <td>18/06/2023</td>
                            <td>Ocupado</td>
                        </tr>
                    </tbody>
                </table>
     </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>

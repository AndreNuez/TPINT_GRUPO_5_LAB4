<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
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
                <ul class="text-end" style="margin: 5px 20px"> Usuario </ul>
                <input type="submit" class="btn btn-danger" name="btnSalir" value="Salir"></input>
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
                        <% 
                        try {
                            // Obtener el DataSource de la conexión a la base de datos
                            InitialContext context = new InitialContext();
                            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/tu_datasource");

                            // Establecer la conexión a la base de datos
                            Connection connection = dataSource.getConnection();

                            // Ejecutar la consulta para obtener los turnos
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM turnos");

                            // Iterar sobre los resultados y generar las filas de la tabla
                            while (resultSet.next()) {
                                String nombre = resultSet.getString("nombre");
                                String apellido = resultSet.getString("apellido");
                                String dni = resultSet.getString("dni");
                                String fechaNacimiento = resultSet.getString("fecha_nacimiento");
                                String fechaTurno = resultSet.getString("fecha_turno");

                                out.println("<tr>");
                                out.println("<td>" + nombre + "</td>");
                                out.println("<td>" + apellido + "</td>");
                                out.println("<td>" + dni + "</td>");
                                out.println("<td>" + fechaNacimiento + "</td>");
                                out.println("<td>" + fechaTurno + "</td>");
                                out.println("<td><input type=\"radio\" name=\"confirmacion\" value=\"asistio\"></td>");
                                out.println("</tr>");
                            }

                            // Cerrar la conexión y liberar los recursos
                            resultSet.close();
                            statement.close();
                            connection.close();
                        } catch (NamingException | SQLException e) {
                            e.printStackTrace();
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>

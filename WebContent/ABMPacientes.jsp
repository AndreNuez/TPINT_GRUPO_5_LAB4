<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<ul class="text-end" style="margin: 5px 20px"> Usuario </ul>
			<input type=submit class="btn btn-danger" name=btnSalir value="Salir"></input>
		</div>
	</div>
	</nav>
	<br>
<!-- Formulario y controles --> 
 <div class="container">
 	<h4>Datos del paciente</h4> <hr>
 <form action="" method="post">
    <div class="row">
        <div class="col-6">
            <div class="mb-2">
                <label for="DNI">DNI:</label>
				<input type="text" name="DNI" maxlength="8" placeholder="DNI" required>
            </div>
            <div class="mb-2">
                <label for="nombre">Nombre:</label>
				<input type="text" name=txtNombre" placeholder="Nombre" required>
            </div>
            <div class="mb-2">
               <label for="Apellido">Apellido:</label>
				<input type="text" name="txtApellido" placeholder="Apellido" required>
            </div>
            <div class="mb-2">
                <label for="Sexo">Sexo:</label>
				<input type="radio" name="Sexo" value="Femenino"> Femenino
				<input type="radio" name="Sexo" value="Masculino"> Masculino
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
				<input type="email" name="txtMail" placeholder="DNI" required>
            </div>
            <div class="mb-2">
				<label for="Teléfono">Teléfono:</label>
				<input type="tel" name="txtTeléfono" placeholder="Teléfono" required>
            </div>
            <div class="mb-2">
				<label for="Estado">Estado:</label>
				<input type="radio" name="Estado" value="Activo"> Activo
				<input type="radio" name="Estado" value="Inactivo"> Inactivo
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
				<select name="Provincias" required>
					<option> Seleccione una... </option>
					<option> Buenos Aires </option>
					<option> Santa Fe </option>
					<option> Córdoba </option>
					<option> Tucumán </option>
				</select>
            </div>
            <div class="mb-2">
				<label for="Localidad">Localidad:</label>
				<select name="Localidades" required>
					<option> Seleccione una... </option>
					<option> San Fernando </option>
					<option> San Isidro </option>
					<option> Tigre </option>
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
        
        <!-- Si el llamado se hace desde opción Ver Completo, se cargan los datos del médico readonly y se habilita 
        	btnModificar-->
        <input type="submit" name="btnModificar" value="Modificar" class="btn btn-warning"> </input>
        
        <!-- Si presiona Modificar, desactiva readonly (inhabilitando DNI) y 
        	tambíen activa btnEliminar con su correspondiente confirmación-->
        <input type="submit" name="btnEliminar" value="Eliminar" class="btn btn-danger"> </input>
        </div>
        </div>
    </div>
    </form>   
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
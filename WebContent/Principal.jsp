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
<br>
<div class="row">
        <div class="col-4"></div>
        <div class="col">
        <img src="https://centromedicomendoza.com/wordpress/wp-content/uploads/2016/04/012.jpg" class="img-fluid" alt="...">
            <div class="text-center">
            <br>
                <h2>Bienvenido a Clinica M�dica SA</h2>
            </div>
            <div class="text-center text-muted">
                <p>Por favor, ingrese su DNI y contrase�a para ingresar:</p>
            </div>
            <div class="col-4"></div>
            <div class="col-2"></div>
            <br />
            
            
            <form action="ServletUsuario" method="post">
            
	            <div class="d-grid mx-auto">
	                <input type="text" name="txtDNI" placeholder="DNI" class="form-control" required ></input>
	                <br>
	            </div>
	            <div class="d-grid mx-auto">
	                <input type="password" name="txtContrase�a" placeholder="Contrase�a" class="form-control" required ></input>
	                <br>
	            </div>   
	             <div class="d-grid mx-auto">
	                <input type=submit class="btn btn-primary" name=btnIngresar value="Ingresar"></input>
	                <br>
	            </div> 
            
            </form>
                     
            <br />
            <br />
            <div class="col-4"></div>
            <div class="col-2"></div>
        </div>
        <div class="col-4"></div>
    </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>
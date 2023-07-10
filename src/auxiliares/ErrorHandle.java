package auxiliares;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import entidad.Usuario;

public class ErrorHandle {
	
	public String usuarioSoftDeleted() {
			
		return "Usted a sido dado de baja. Contactarse con el sindicato"; 
	}
	
}

package auxiliares;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import entidad.Usuario;

public class ErrorHandle {
	
	public boolean usuarioValidateNotNull(Usuario user) {
			if (user != null) {
				return true; 
			}
		return false;
	}
}

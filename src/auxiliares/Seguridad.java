package auxiliares;

import entidad.Usuario;

public class Seguridad {
	
	public Seguridad(){
		
	}
	
	public boolean usuarioEliminado(Usuario user) {
		boolean eliminado=false;
			if (user.getEstado() == 0) {
				eliminado = true;
			}
		return eliminado;
	}
}

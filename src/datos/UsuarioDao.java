package datos;

import entidad.Usuario;

public interface UsuarioDao {
	public Usuario obtenerUsuario(String pass, int dni);
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario);
}



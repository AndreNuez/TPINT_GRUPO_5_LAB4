package negocio;

import entidad.Usuario;

public interface UsuarioNegocio {
	public Usuario obtenerUsuario(String pass, int dni);
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario);
}

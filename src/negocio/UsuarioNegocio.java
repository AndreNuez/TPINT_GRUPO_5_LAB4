package negocio;

import entidad.Usuario;

public interface UsuarioNegocio {
	public Usuario obtenerUsuario(String pass, int dni);
	public Usuario insertarUsuario(String pass, int dni, int tipoUsuario);
}

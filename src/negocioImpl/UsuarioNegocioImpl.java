package negocioImpl;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;

public class UsuarioNegocioImpl implements UsuarioNegocio{

	private UsuarioDao userDao = new UsuarioDaoImpl();
	
	public UsuarioNegocioImpl() {
		
	}
	
	@Override
	public Usuario obtenerUsuario(String pass, int dni) {
		
		 return (Usuario) userDao.obtenerUsuario(pass, dni);		
	
	}

	@Override
	public Usuario insertarUsuario(String pass, int dni, int tipoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

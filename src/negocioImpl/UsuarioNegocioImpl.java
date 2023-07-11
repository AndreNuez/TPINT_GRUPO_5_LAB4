package negocioImpl;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import Exceptions.DniInvalido;
import Exceptions.UsuarioRegistrado;
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
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario) {
		return userDao.insertarUsuario(pass, dni, tipoUsuario);
	}

	@Override
	public boolean editarUsuario(String pass, int dni) {
		return userDao.editarUsuario(pass, dni);
	}
	
	
	public boolean validarDNI(int dni) throws DniInvalido{
		Boolean dniCorrecto = false;
		
		if (dni >= 10000000 && String.valueOf(dni).length() >= 8) {
			dniCorrecto = true;
		}
		
		if (!dniCorrecto) {
			throw new DniInvalido();
		}

		if (dniCorrecto)
			return true;
		
		return true;			
	}
}

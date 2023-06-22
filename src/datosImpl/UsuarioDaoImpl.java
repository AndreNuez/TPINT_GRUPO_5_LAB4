package datosImpl;

import java.sql.ResultSet;

import datos.UsuarioDao;
import entidad.Usuario;
import entidad.TipoUsuario;


public class UsuarioDaoImpl implements UsuarioDao{

	private Conexion cn;
	
	public UsuarioDaoImpl() {
		
	}
	
	@Override
	public Usuario obtenerUsuario(String pass, int dni) {
		cn = new Conexion();
		cn.Open();
		Usuario usuario = new Usuario();
		
		try {
			ResultSet rs = cn.query("select DNI, IDTipoUsuario, Contraseña from usuarios where DNI = " + dni + "and Contraseña = " + pass); 
			rs.next();
			
			usuario.setDNI(rs.getInt("DNI"));
			usuario.setContraseña(rs.getString("Contraseña"));

			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setIdTipoUsuario(rs.getInt("IDTipoUsuario"));
			
			if (rs.getInt("IDTipoUsuario") == 0)
				tipoUsuario.setDescripcion("Médico");
			else 
				tipoUsuario.setDescripcion("Administrador");
				
			usuario.setTipo(tipoUsuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally{
			cn.close();
		}
		
		return usuario;
		
	}

	@Override
	public Usuario insertarUsuario(String pass, int dni, int tipoUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}

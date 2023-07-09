package datosImpl;

import java.sql.ResultSet;

import Exceptions.DniInvalido;
import Exceptions.UsuarioRegistrado;
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
			ResultSet rs = cn.query("select DNI, IDTipoUsuario, Contrase�a, Estado from usuarios where DNI = " + dni + " and Contrase�a = '" + pass + "'"); 
			rs.next();
			
			usuario.setDNI(rs.getInt("DNI"));
			usuario.setContrase�a(rs.getString("Contrase�a"));

			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setIdTipoUsuario(rs.getInt("IDTipoUsuario"));
			
			if (rs.getInt("IDTipoUsuario") == 0)
				tipoUsuario.setDescripcion("M�dico");
			else 
				tipoUsuario.setDescripcion("Administrador");
				
			usuario.setTipo(tipoUsuario);
			
			usuario.setEstado(rs.getInt("Estado"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally{
			cn.close();
		}
		
		return usuario;
		
	}

	@Override
	public boolean insertarUsuario(String pass, int dni, int tipoUsuario) {

		boolean estado = true;

		cn = new Conexion();
		cn.Open();
		
		try {
			
			String query = "Insert into usuarios (DNI, IDTipoUsuario, Contrase�a, Estado) VALUES ('"+dni+"','"+tipoUsuario+"','"+pass+"', 1)";
			estado=cn.execute(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		
		return estado;
	}

	@Override
	public boolean editarUsuario(String pass, int dni) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();
		
		String query = "UPDATE usuarios SET Contrase�a='"+pass+"' where DNI="+dni;
				
		try
		 {
			estado = cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}
	
	public boolean validarDNI(int dni) throws DniInvalido{
		Boolean dniCorrecto = false;
		
		if (dni > 10000000 && String.valueOf(dni).length() > 8) {
			dniCorrecto = true;
		}
		
		if (!dniCorrecto) {
			throw new DniInvalido();
		}

		if (dniCorrecto)
			return true;
		
		return true;
					
	}

	public boolean validarMedicoExistente(int dni) throws UsuarioRegistrado{
		boolean existe = false;
		int cantDniBD;
		cn = new Conexion();
		cn.Open();
		
		try {
			ResultSet rs = cn.query("select count(DNI) as Cantidad from medicos where DNI = '" + dni + "'"); 
			rs.next();
			
			cantDniBD = rs.getInt("Cantidad");
			
			if (cantDniBD == 1)
				existe = true; 
				throw new UsuarioRegistrado();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		
		return existe;
	}
	
	public boolean validarPacienteExistente(int dni) throws UsuarioRegistrado{
		boolean existe = false;
		int cantDniBD;
		cn = new Conexion();
		cn.Open();
		
		try {
			ResultSet rs = cn.query("select count(DNI) as Cantidad from pacientes where DNI = '" + dni + "'"); 
			rs.next();
			
			cantDniBD = rs.getInt("Cantidad");
			
			if (cantDniBD == 1)
				existe = true; 
				throw new UsuarioRegistrado();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cn.close();
		}
		
		return existe;

	}
}
package datosImpl;

import java.util.List;

import datos.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {
	
	private Conexion cn;
	
	public PersonaDaoImpl () {
		
	}

	@Override
	public List<Persona> ListarTodas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona ListarUna(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarPersona(Persona persona) {
		// TODO Auto-generated method stub
		return false;
	}

	// ANDRE
	public boolean EditarPersona(Persona persona) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		
		//Reemplazar por SP
		String query = "UPDATE Personas SET DNI='"+persona.getDNI()+"', Nombre='"+persona.getNombre()+"', Apellido='"+persona.getApellido()+"', Sexo='"+persona.getSexo()+"', Nacionalidad='"+persona.getNacionalidad()+"', FNac='"+persona.getFnac()+"', Calle='"+persona.getDireccion().getCalle()+"', Numero='"+persona.getDireccion().getNumero()+"', Localidad='"+persona.getDireccion().getLocalidad()+"', Provincia='"+persona.getDireccion().getProvincia()+"', Mail='"+persona.getMail()+"', Telefono='"+persona.getTelefono()+"', Estado='"+persona.getEstado()+"' WHERE DNI='"+persona.getDNI()+"'";
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
	

	@Override
	public boolean EliminarPersona(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}

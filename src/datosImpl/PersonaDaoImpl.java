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

	@Override
	public boolean EditarPersona(Persona persona) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EliminarPersona(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}

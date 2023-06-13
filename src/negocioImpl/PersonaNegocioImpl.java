package negocioImpl;

import java.util.List;

import datos.PersonaDao;
import datosImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {
	
	private PersonaDao personaDao = new PersonaDaoImpl();
	
	public PersonaNegocioImpl () {
		
	}

	public PersonaNegocioImpl(PersonaDao personaDao) {
		this.personaDao = personaDao;
	}

	@Override
	public List<Persona> ListarTodas() {
		return List<Persona>;
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

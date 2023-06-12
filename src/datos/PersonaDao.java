package datos;

import java.util.List;

import entidad.Persona;

public interface PersonaDao {
	
	public List<Persona> ListarTodas();
	public Persona ListarUna(int id);
	public boolean InsertarPersona(Persona persona);
	public boolean EditarPersona(Persona persona);
	public boolean EliminarPersona(int id); //Eliminar logico.

}

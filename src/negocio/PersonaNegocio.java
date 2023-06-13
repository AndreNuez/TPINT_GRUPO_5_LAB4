package negocio;

import java.util.List;

import entidad.Persona;

public interface PersonaNegocio {
	
	public List<Persona> ListarTodas();
	public Persona ListarUna(int id);
	public boolean InsertarPersona(Persona persona);
	public boolean EditarPersona(Persona persona);
	public boolean EliminarPersona(int id); //Eliminar logico.

}

package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Persona;

public interface PacienteNegocio {
	
	public ArrayList<Persona> ListarTodos();
	public Persona ListarUno(int dni);
	public boolean InsertarPaciente(Persona paciente);
	public boolean EditarPaciente(Persona paciente);
	public boolean EliminarPaciente(int dni); //Eliminar logico.

}

package negocio;

import java.util.ArrayList;

import entidad.Medico;

public interface MedicoNegocio {
	
	public ArrayList<Medico> ListarTodos();
	public Medico ListarUno(int dni);
	public boolean InsertarMedico(Medico medico);
	public boolean EditarMedico(Medico medico);
	public boolean EliminarMedico(int dni); //Eliminar logico.

}

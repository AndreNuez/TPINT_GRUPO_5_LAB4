package negocio;

import java.util.ArrayList;

import entidad.Horario;

public interface HorarioNegocio {
	
	public boolean InsertarHorario (Horario horario, int dni);
	public ArrayList<Horario> ListarTodos(int dni);
}

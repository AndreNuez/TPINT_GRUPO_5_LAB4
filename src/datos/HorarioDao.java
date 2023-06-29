package datos;

import java.util.ArrayList;

import entidad.Horario;

public interface HorarioDao {

	public boolean InsertarHorario (Horario horario, int dni);
	public ArrayList<Horario> ListarTodos(int dni);
}

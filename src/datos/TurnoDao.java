package datos;

import java.util.ArrayList;
import java.util.List;

import entidad.Turno;
import entidad.Medico;

public interface TurnoDao {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListarTurnosPorMedico(Medico medico);

}

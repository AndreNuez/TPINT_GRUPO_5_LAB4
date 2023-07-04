package datos;

import java.util.List;

import entidad.Turno;

public interface TurnoDao {
	
	public boolean ActualizarTurno(Turno turno);
	public List<Turno> ListarTodos();

}

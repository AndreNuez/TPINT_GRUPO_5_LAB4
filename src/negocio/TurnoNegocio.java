package negocio;
import java.util.ArrayList;
import java.util.List;

import entidad.Medico;
import entidad.Turno;
public interface TurnoNegocio {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListaTurnosPorMedico(Medico medico);

}

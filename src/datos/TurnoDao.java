package datos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Turno;
import entidad.Medico;

public interface TurnoDao {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListarTurnosPorMedico(Medico medico);
	public boolean ChequearFecha(LocalDate fecha, int dniMedico);
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i);
	public boolean existeTurnoEnHorarioFecha(Turno turno);

}

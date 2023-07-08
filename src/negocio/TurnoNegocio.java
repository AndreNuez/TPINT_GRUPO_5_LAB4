package negocio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Medico;
import entidad.Turno;
public interface TurnoNegocio {
	
	public boolean ActualizarTurno(Turno turno);
	public ArrayList<Turno> ListarTodos();
	public ArrayList<Turno> ListaTurnosPorMedico(Medico medico);
	public boolean chequearFecha(LocalDate fecha, int dniMedico);
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i);

}

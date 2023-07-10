package negocioImpl;

import entidad.Medico;
import entidad.Persona;
import entidad.Turno;
import negocio.TurnoNegocio;
import datosImpl.TurnoDaoImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.TurnoDao;

public class TurnoNegocioImpl implements TurnoNegocio{

	public TurnoDao tdao = new TurnoDaoImpl();

	public boolean ActualizarTurno(Turno turno) {
		return tdao.ActualizarTurno(turno);
	}
	
	public ArrayList<Turno> ListarTodos(){
		return tdao.ListarTodos();
	}
	
	public ArrayList<Turno> ListaTurnosPorMedico(Medico medico) {
		return tdao.ListarTurnosPorMedico(medico);
	}
	
	public ArrayList<Turno> ListarTurnosPorMedicoDiaActual(Medico medico)
	{
		return tdao.ListarTurnosPorMedicoDiaActual(medico);
	}
	
	public ArrayList<Turno> ListarTurnosPorMedicoYFecha(Medico medico, LocalDate fechaDesde, LocalDate fechaHasta)
	{
		return tdao.ListarTurnosPorMedicoYFecha(medico, fechaDesde, fechaHasta);
	}
	
	public boolean chequearFecha(LocalDate fecha, int dniMedico)
	{
		return tdao.ChequearFecha(fecha, dniMedico);
	}
	
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i)
	{
		return tdao.insertarTurno(dniMedico, fecha, i);
	}
	
	public boolean existeTurnoEnHorarioFecha(Turno turno) {
		return tdao.existeTurnoEnHorarioFecha(turno);
	}
	
	public boolean ActualizarEstadoTurnoAsistio(int idTurno, String observacion)
	{
		return tdao.ActualizarEstadoTurnoAsistio(idTurno, observacion);
	}
	
	public boolean ActualizarEstadoTurnoAusente(int idTurno)
	{
		return tdao.ActualizarEstadoTurnoAusente(idTurno);
	}
}

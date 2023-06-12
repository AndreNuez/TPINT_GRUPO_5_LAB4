package entidad;

import java.time.LocalTime;

public class Turno {
	
	private int idTurno;
	private Medico Medico;
	private Paciente Paciente;
	private Especialidad Especialidad;
	private LocalTime DiayHora;
	private boolean Estado;
	private String Observaciones;
	
	public Turno () {
		
	}

	public Turno(int idTurno, entidad.Medico medico, entidad.Paciente paciente, entidad.Especialidad especialidad,
			LocalTime diayHora, boolean estado, String observaciones) {
		this.idTurno = idTurno;
		Medico = medico;
		Paciente = paciente;
		Especialidad = especialidad;
		DiayHora = diayHora;
		Estado = estado;
		Observaciones = observaciones;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public Medico getMedico() {
		return Medico;
	}

	public void setMedico(Medico medico) {
		Medico = medico;
	}

	public Paciente getPaciente() {
		return Paciente;
	}

	public void setPaciente(Paciente paciente) {
		Paciente = paciente;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public LocalTime getDiayHora() {
		return DiayHora;
	}

	public void setDiayHora(LocalTime diayHora) {
		DiayHora = diayHora;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", Medico=" + Medico + ", Paciente=" + Paciente + ", Especialidad="
				+ Especialidad + ", DiayHora=" + DiayHora + ", Estado=" + Estado + ", Observaciones=" + Observaciones
				+ "]";
	}
	
}

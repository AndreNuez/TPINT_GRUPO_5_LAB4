package entidad;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
	
	private int idTurno;
	private Medico Medico;
	private Persona Paciente;
	private Especialidad Especialidad;
	private LocalDateTime DiayHora;
	private int Estado;
	private String Observaciones;
	
	public Turno () {
		
	}

	public Turno(int idTurno, entidad.Medico medico, Persona paciente, entidad.Especialidad especialidad,
			LocalDateTime diayHora, int estado, String observaciones) {
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

	public Persona getPaciente() {
		return Paciente;
	}

	public void setPaciente(Persona paciente) {
		Paciente = paciente;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public LocalDateTime getDiayHora() {
		return DiayHora;
	}

	public void setDiayHora(LocalDateTime diayHora) {
		DiayHora = diayHora;
	}

	public int isEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
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

package entidad;

public class Medico extends Persona {
	
	private Especialidad Especialidad;
	private String DiaAtencion;
	private int HoraInicio;
	private int HoraFin;
	boolean Estado;
	
	public Medico() {
		
	}

	public Medico(entidad.Especialidad especialidad, String diaAtencion, int horaInicio, int horaFin, boolean estado) {
		super();
		Especialidad = especialidad;
		DiaAtencion = diaAtencion;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
		Estado = estado;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public String getDiaAtencion() {
		return DiaAtencion;
	}

	public void setDiaAtencion(String diaAtencion) {
		DiaAtencion = diaAtencion;
	}

	public int getHoraInicio() {
		return HoraInicio;
	}

	public void setHoraInicio(int horaInicio) {
		HoraInicio = horaInicio;
	}

	public int getHoraFin() {
		return HoraFin;
	}

	public void setHoraFin(int horaFin) {
		HoraFin = horaFin;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Medico [Especialidad=" + Especialidad + ", DiaAtencion=" + DiaAtencion + ", HoraInicio=" + HoraInicio
				+ ", HoraFin=" + HoraFin + ", Estado=" + Estado + "]";
	}
	
}

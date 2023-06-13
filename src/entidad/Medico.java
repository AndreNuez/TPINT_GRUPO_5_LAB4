package entidad;

public class Medico extends Persona {
	
	private Especialidad Especialidad;
	private String DiaAtencion;
	private int HoraInicio;
	private int HoraFin;
	
	public Medico() {
		
	}

	public Medico(entidad.Especialidad especialidad, String diaAtencion, int horaInicio, int horaFin) {
		super();
		Especialidad = especialidad;
		DiaAtencion = diaAtencion;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
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

	@Override
	public String toString() {
		return "Medico [Especialidad=" + Especialidad + ", DiaAtencion=" + DiaAtencion + ", HoraInicio=" + HoraInicio
				+ ", HoraFin=" + HoraFin + "]";
	}
	
}

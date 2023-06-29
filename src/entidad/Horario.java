package entidad;

public class Horario {
	
	private String DiaAtencion;
	private int HoraInicio;
	private int HoraFin;
	
	public Horario () {
		
	}

	public Horario(String diaAtencion, int horaInicio, int horaFin) {
		super();
		DiaAtencion = diaAtencion;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
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
		return "Horario [DiaAtencion=" + DiaAtencion + ", HoraInicio=" + HoraInicio + ", HoraFin=" + HoraFin + "]";
	}
	
}

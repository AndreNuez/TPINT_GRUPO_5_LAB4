package entidad;

public class Horario {
	
	private int idHorario;
	private String DiaAtencion;
	private int HoraInicio;
	private int HoraFin;
	private int Estado;
	
	public Horario () {
		
	}


	public Horario(int idHorario, String diaAtencion, int horaInicio, int horaFin, int estado) {
		super();
		this.idHorario = idHorario;
		DiaAtencion = diaAtencion;
		HoraInicio = horaInicio;
		HoraFin = horaFin;
		Estado = estado;
	}


	public int getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
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

	public int getEstado() {
		return Estado;
	}

	public void setEstado(int estado) {
		Estado = estado;
	}


	@Override
	public String toString() {
		return "Horario [idHorario=" + idHorario + ", DiaAtencion=" + DiaAtencion + ", HoraInicio=" + HoraInicio
				+ ", HoraFin=" + HoraFin + ", Estado=" + Estado + "]";
	}

}

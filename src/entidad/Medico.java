package entidad;

public class Medico extends Persona {
	
	private Especialidad Especialidad;
	
	public Medico() {
		
	}

	public Medico(entidad.Especialidad especialidad) {
		super();
		Especialidad = especialidad;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	@Override
	public String toString() {
		return "Medico [Especialidad=" + Especialidad + "]";
	}
			
}

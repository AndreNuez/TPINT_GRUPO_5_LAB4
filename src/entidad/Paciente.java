package entidad;

import java.time.LocalDate;

public class Paciente extends Persona {
	
	boolean Estado;
	
	public Paciente() {
		
	}

	public Paciente(boolean estado) {
		super();
		Estado = estado;
	}

	public boolean isEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Paciente [Estado=" + Estado + "]";
	}

	
}

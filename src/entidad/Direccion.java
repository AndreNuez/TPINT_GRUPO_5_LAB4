package entidad;

public class Direccion {
	
	private String Calle;
	private int Numero;
	private String Localidad;
	private String Provincia;
	
	public Direccion() {
		
	}
	
	public Direccion(String calle, int numero, String localidad, String provincia) {
		Calle = calle;
		Numero = numero;
		Localidad = localidad;
		Provincia = provincia;
	}

	public String getCalle() {
		return Calle;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}

	public int getNumero() {
		return Numero;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public String getLocalidad() {
		return Localidad;
	}

	public void setLocalidad(String localidad) {
		Localidad = localidad;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	@Override
	public String toString() {
		return "Direccion [Calle=" + Calle + ", Numero=" + Numero + ", Localidad=" + Localidad + ", Provincia="
				+ Provincia + "]";
	}
	
	
}


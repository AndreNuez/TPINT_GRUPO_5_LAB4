package entidad;

public class Usuario {
	
	private int DNI;
	private String Contrase�a;
	private TipoUsuario Tipo;
	private boolean estado;
	
	public Usuario() {
		
	}

	public Usuario(int dNI, String contrase�a, TipoUsuario tipo, boolean estado) {
		DNI = dNI;
		Contrase�a = contrase�a;
		Tipo = tipo;
		this.estado = estado;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getContrase�a() {
		return Contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		Contrase�a = contrase�a;
	}

	public TipoUsuario getTipo() {
		return Tipo;
	}
	
	//public String getTipo() {
	//	return Tipo.getDescripcion();
	//}

	public void setTipo(TipoUsuario tipo) {
		Tipo = tipo;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", Contrase�a=" + Contrase�a + ", Tipo=" + Tipo + "]";
	}
	
}

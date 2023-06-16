package datos;

import entidad.Direccion;

public interface DireccionDao {
	
	public Direccion ListarUno(int id);
	public boolean InsertarDP(int dni, Direccion direccion);
	public boolean EditarDP(Direccion direccion);

}

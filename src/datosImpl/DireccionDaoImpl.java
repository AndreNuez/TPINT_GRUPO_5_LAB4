package datosImpl;

import datos.DireccionDao;
import entidad.Direccion;

public class DireccionDaoImpl implements DireccionDao {

	Conexion cn;
	@Override
	public Direccion ListarUno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarDP(int dni, Direccion direccion) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		
		String query = "INSERT INTO direccionespacientes (DNI, Calle, Numero, IDLocalidad) VALUES ('"+dni+"','"+direccion.getCalle()+"','"+direccion.getNumero()+"','"+direccion.getLocalidad().getIDLocalidad()+"')"; 	
		try
		 {
			estado=cn.execute(query);
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}

	@Override
	public boolean EditarDP(Direccion direccion) {
		// TODO Auto-generated method stub
		return false;
	}

}

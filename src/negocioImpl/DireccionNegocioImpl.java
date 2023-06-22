package negocioImpl;

import datos.DireccionDao;
import datosImpl.DireccionDaoImpl;
import entidad.Direccion;
import negocio.DireccionNegocio;

public class DireccionNegocioImpl implements DireccionNegocio {
	
	private DireccionDao dpdao = new DireccionDaoImpl();

	@Override
	public Direccion ListarUno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarDP(int dni, Direccion direccion) {
		return dpdao.InsertarDP(dni, direccion);
	}

	@Override
	public boolean EditarDP(int DNI, Direccion direccion) {
		return dpdao.EditarDP(DNI, direccion);
	}

}

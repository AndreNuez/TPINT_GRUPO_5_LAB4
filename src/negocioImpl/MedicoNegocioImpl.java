package negocioImpl;

import java.util.ArrayList;

import datos.MedicoDao;
import datos.PacienteDao;
import datosImpl.MedicoDaoImpl;
import entidad.Medico;
import entidad.Persona;
import negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio {
	
	private MedicoDao mdao = new MedicoDaoImpl();
	
	public MedicoNegocioImpl () {
	
	}

	@Override
	public ArrayList<Medico> ListarTodos() {
		return (ArrayList<Medico>) mdao.ListarTodos();
	}

	@Override
	public Medico ListarUno(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarMedico(Medico medico) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EditarMedico(Medico medico) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EliminarMedico(int dni) {
		// TODO Auto-generated method stub
		return false;
	}

}

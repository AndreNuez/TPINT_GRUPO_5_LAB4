package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datos.PacienteDao;
import datosImpl.PacienteDaoImpl;
import entidad.Persona;
import negocio.PacienteNegocio;

public class PacienteNegocioImpl implements PacienteNegocio {
	
	private PacienteDao pacienteDao = new PacienteDaoImpl();
	
	public PacienteNegocioImpl () {
		
	}

	public PacienteNegocioImpl(PacienteDao pacienteDao) {
		this.pacienteDao = pacienteDao;
	}

	@Override
	public ArrayList<Persona> ListarTodos() {
		return (ArrayList<Persona>) pacienteDao.ListarTodos();
	}

	@Override
	public Persona ListarUno(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean InsertarPaciente(Persona paciente) {
		return pacienteDao.InsertarPaciente(paciente);
	}

	@Override
	public boolean EditarPaciente(Persona paciente) {
		return pacienteDao.EditarPaciente(paciente);
	}

	@Override
	public boolean EliminarPaciente(int dni) {
		return pacienteDao.EliminarPaciente(dni);
	}

}

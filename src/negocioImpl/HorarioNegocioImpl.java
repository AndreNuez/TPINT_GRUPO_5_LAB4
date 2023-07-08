package negocioImpl;

import java.util.ArrayList;

import datos.HorarioDao;
import datosImpl.HorarioDaoImpl;
import entidad.Horario;
import negocio.HorarioNegocio;

public class HorarioNegocioImpl implements HorarioNegocio {
	
	private HorarioDao hdao = new HorarioDaoImpl();
	
	public HorarioNegocioImpl() {
		
	}

	@Override
	public boolean InsertarHorario(Horario horario, int dni) {
		return hdao.InsertarHorario(horario, dni);
	}

	@Override
	public ArrayList<Horario> ListarTodos(int dni) {
		return hdao.ListarTodos(dni);
	}

	@Override
	public boolean ModificarHorario(Horario horario) {
		return hdao.ModificarHorario(horario);
	}

	@Override
	public boolean EliminarHorario(int idHorario) {
		return hdao.EliminarHorario(idHorario);
	}
	
	public Horario buscarHorario(int dniMedico, String dia) {
		return hdao.buscarHorario(dniMedico, dia);
	}
}

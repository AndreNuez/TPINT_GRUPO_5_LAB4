package datosImpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import datos.MedicoDao;
import entidad.Especialidad;
import entidad.Medico;

public class MedicoDaoImpl implements MedicoDao {
	
	private Conexion cn;
	
	public MedicoDaoImpl () {
		
	}

	public List<Medico> ListarTodos() {
		cn = new Conexion();
		cn.Open();
			
		List<Medico> list = new ArrayList<Medico>();
		Especialidad esp = new Especialidad();
			
		try
			{
				ResultSet rs= cn.query("SELECT m.DNI, m.Apellido, m.Nombres, m.Sexo, m.Mail, e.IDEspecialidad, e.Nombre, m.Estado FROM medicos m INNER JOIN especialidades e ON m.IDEspecialidad = e.IDEspecialidad WHERE m.Estado = 1");
				while(rs.next())
				{
					Medico medico = new Medico();
					medico.setDNI(rs.getInt("DNI"));
					medico.setApellido(rs.getString("Apellido"));
					medico.setNombre(rs.getString("Nombres"));
					medico.setSexo(rs.getString("Sexo").charAt(0));
					medico.setMail(rs.getString("Mail"));
					esp.setIdEspecialidad(rs.getInt("IDEspecialidad"));
					esp.setDescripcion(rs.getString("Nombre"));
					medico.setEspecialidad(esp);
					medico.setEstado(rs.getInt("Estado"));
					list.add(medico);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				cn.close();
			}
			return list;
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

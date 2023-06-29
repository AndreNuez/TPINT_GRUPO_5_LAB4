package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.HorarioDao;
import entidad.Especialidad;
import entidad.Horario;

public class HorarioDaoImpl implements HorarioDao {
	
	private Conexion cn;
	
	public HorarioDaoImpl() {
		
	}

	@Override
	public boolean InsertarHorario(Horario horario, int dni) {
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		
		String query = "Insert into horariosxmedicos (DNIMedico, HoraInicio, HoraFin, DiaAtencion) VALUES ('"+dni+"','"+horario.getHoraInicio()+"','"+horario.getHoraFin()+"','"+horario.getDiaAtencion()+"')";
		
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
	public ArrayList<Horario> ListarTodos(int dni) {
		
		cn = new Conexion();
		cn.Open();
			
		ArrayList<Horario> list = new ArrayList<Horario>();
			
		try
			{
				ResultSet rs= cn.query("SELECT HoraInicio, HoraFin, DiaAtencion FROM horariosxmedicos where DNIMedico="+dni);
				while(rs.next())
				{
					Horario horario = new Horario();
					horario.setHoraInicio(rs.getInt("HoraInicio"));
					horario.setHoraFin(rs.getInt("HoraFin"));
					horario.setDiaAtencion(rs.getString("DiaAtencion"));
					list.add(horario);
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

}

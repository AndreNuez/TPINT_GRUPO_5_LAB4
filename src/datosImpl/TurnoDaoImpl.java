package datosImpl;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import datos.TurnoDao;
import entidad.Persona;
import entidad.Provincia;
import entidad.Turno;
import entidad.Medico;
import entidad.Especialidad;
import entidad.Horario;
import entidad.Localidad;

public class TurnoDaoImpl implements TurnoDao{
	
	private Conexion cn;
	
	public boolean ActualizarTurno(Turno turno) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "UPDATE turnos SET DNIPaciente='"+turno.getPaciente().getDNI()+"',IDEstado='"+turno.isEstado()+"' where IDTurno="+turno.getIdTurno();
		try
		 {
			estado = cn.execute(query);
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
	
	public ArrayList<Turno> ListarTodos() {
		cn = new Conexion();
		cn.Open();
			ArrayList<Turno> list = new ArrayList<Turno>();
			try
			{
				ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre, horariosxmedicos.HoraInicio, horariosxmedicos.HoraFin, horariosxmedicos.DiaAtencion FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad =  especialidades.IDEspecialidad INNER JOIN horariosxmedicos ON medicos.DNI = horariosxmedicos.DNIMedico WHERE turnos.IDEstado = 0");
				while(rs.next())
				{
					Especialidad especialidad = new Especialidad();
					especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
					especialidad.setDescripcion(rs.getString("especialidades.Nombre"));
					
					Horario horario = new Horario();
					
					horario.setHoraInicio(rs.getInt("horariosxmedicos.HoraInicio"));
					horario.setHoraFin(rs.getInt("horariosxmedicos.HoraFin"));
					horario.setDiaAtencion(rs.getString("horariosxmedicos.DiaAtencion"));
					
					
					Medico medico = new Medico();
					medico.setDNI(rs.getInt("turnos.DNIMedico"));
					medico.setApellido(rs.getString("medicos.Apellido"));
					medico.setNombre(rs.getString("medicos.Nombres"));
					medico.setEspecialidad(especialidad);
					medico.setHorario(horario);
					
					
					Turno turno = new Turno();
					turno.setMedico(medico);
					turno.setIdTurno(rs.getInt("turnos.IDTurno"));
					turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
					turno.setHora(rs.getInt("turnos.Hora"));
					
					list.add(turno);
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
	
	public ArrayList<Turno> ListarTurnosPorMedico(Medico medico) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Turno> list = new ArrayList<Turno>();
		int dniMedico = medico.getDNI();
		try
		{
			ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre, horariosxmedicos.HoraInicio, horariosxmedicos.HoraFin, horariosxmedicos.DiaAtencion FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad =  especialidades.IDEspecialidad INNER JOIN horariosxmedicos ON medicos.DNI = horariosxmedicos.DNIMedico WHERE turnos.IDEstado = 0 AND medicos.DNI = "+dniMedico);
			while(rs.next())
			{
				Medico m = new Medico();
				Turno turno = new Turno();
				Horario horario = new Horario();
				Especialidad especialidad = new Especialidad();
				
				especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
				especialidad.setDescripcion(rs.getString("especialidades.Nombre"));
				
				horario.setHoraInicio(rs.getInt("horariosxmedicos.HoraInicio"));
				horario.setHoraFin(rs.getInt("horariosxmedicos.HoraFin"));
				horario.setDiaAtencion(rs.getString("horariosxmedicos.DiaAtencion"));
				
				m.setDNI(dniMedico);
				m.setApellido(rs.getString("medicos.Apellido"));
				m.setNombre(rs.getString("medicos.Nombres"));
				m.setEspecialidad(especialidad);
				m.setHorario(horario);
				
				turno.setMedico(m);
				turno.setIdTurno(rs.getInt("turnos.IDTurno"));
				turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
				turno.setHora(rs.getInt("turnos.Hora"));	
				
				list.add(turno);	
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
	
	public boolean ChequearFecha(LocalDate fecha, int dniMedico)
	{
		cn = new Conexion();
		cn.Open();	

		String query = "SELECT * FROM turnos where turnos.DNIMedico = "+ dniMedico +" and turnos.Fecha = '"+ fecha+"'";
		
		try
		 {
			ResultSet rs = cn.query(query);
			if(rs.next())
			{
				return true;
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
		return false;
	}
	
	public boolean insertarTurno(int dniMedico, LocalDate fecha, int i)
	{
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "insert into turnos (Fecha, Hora, DNIMedico, DNIPaciente, IDEstado, Observacion) values ('" + fecha + "'," + i + ","+ dniMedico + ", null, 0, null)";
		try
		 {
			estado = cn.execute(query);
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
	
	
	public boolean existeTurnoEnHorarioFecha(Turno turno) {
		cn = new Conexion();
		cn.Open();
		boolean existe = false;
		try
		{
			ResultSet rs= cn.query("SELECT * FROM turnos WHERE Fecha= '"+turno.getFecha()+"' AND Hora = "+turno.getHora()+" AND DNIPaciente = "+turno.getPaciente().getDNI()+" AND IDEstado = 1");
			if(rs.next())
			{
				existe = true;	
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
		System.out.println(existe);
		return existe;
	}
	
}

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
import entidad.Turno;
import entidad.Medico;
import entidad.Especialidad;
import entidad.Horario;

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
					turno.setHora(LocalTime.parse(rs.getString("turnos.Hora")));
					
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
		
		System.out.println("DNI: "+medico.getDNI());
		ArrayList<Turno> list = new ArrayList<Turno>();

			try
			{
				ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.Fecha, turnos.Hora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre, horariosxmedicos.HoraInicio, horariosxmedicos.HoraFin, horariosxmedicos.DiaAtencion FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad =  especialidades.IDEspecialidad INNER JOIN horariosxmedicos ON medicos.DNI = horariosxmedicos.DNIMedico WHERE turnos.IDEstado = 0 AND medicos.DNI = "+medico.getDNI());
				while(rs.next());
				{
					Turno turno = new Turno();
					Horario horario = new Horario();
					Especialidad especialidad = new Especialidad();
					
					especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
					especialidad.setDescripcion(rs.getString("especialidades.Nombre"));
					
					horario.setHoraInicio(rs.getInt("horariosxmedicos.HoraInicio"));
					horario.setHoraFin(rs.getInt("horariosxmedicos.HoraFin"));
					horario.setDiaAtencion(rs.getString("horariosxmedicos.DiaAtencion"));
					
					medico.setApellido(rs.getString("medicos.Apellido"));
					medico.setNombre(rs.getString("medicos.Nombres"));
					medico.setEspecialidad(especialidad);
					medico.setHorario(horario);
					
					turno.setMedico(medico);
					turno.setIdTurno(rs.getInt("turnos.IDTurno"));
					turno.setFecha(LocalDate.parse(rs.getString("turnos.Fecha")));
					turno.setHora(LocalTime.parse(rs.getString("turnos.Hora")));	
					
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

}

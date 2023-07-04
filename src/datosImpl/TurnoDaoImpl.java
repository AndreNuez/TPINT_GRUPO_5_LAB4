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
	
	public List<Turno> ListarTodos() {
		cn = new Conexion();
		cn.Open();
			List<Turno> list = new ArrayList<Turno>();
			try
			{
				ResultSet rs= cn.query("SELECT turnos.DNIMedico, turnos.IDTurno, turnos.FechaHora, medicos.Nombres, medicos.Apellido, medicos.IDEspecialidad, especialidades.Nombre, horariosxmedicos.HoraInicio, horariosxmedicos.HoraFin, horariosxmedicos.DiaAtencion FROM turnos INNER JOIN medicos ON turnos.DNIMedico = medicos.DNI INNER JOIN especialidades ON medicos.IDEspecialidad =  especialidades.IDEspecialidad INNER JOIN horariosxmedicos ON medicos.DNI = horariosxmedicos.DNIMedico WHERE turnos.IDEstado = 0");
				while(rs.next())
				{
					Especialidad especialidad = new Especialidad();
					especialidad.setIdEspecialidad(rs.getInt("medicos.IDEspecialidad"));
					especialidad.setDescripcion(rs.getString("especialidades.Nombre"));
					
					
					Medico medico = new Medico();
					medico.setDNI(rs.getInt("turnos.DNIMedico"));
					medico.setApellido(rs.getString("medicos.Apellido"));
					medico.setNombre(rs.getString("medicos.Nombres"));
					medico.setHoraInicio(rs.getInt("horariosxmedicos.HoraInicio"));
					medico.setHoraFin(rs.getInt("horariosxmedicos.HoraFin"));
					medico.setDiaAtencion(rs.getString("horariosxmedicos.DiaAtencion"));
					medico.setEspecialidad(especialidad);
					
					
					Turno turno = new Turno();
					turno.setMedico(medico);
					turno.setIdTurno(rs.getInt("turnos.IDTurno"));
					//turno.setDiayHora(LocalDateTime.parse(rs.getString("turnos.FechaHora")));
					
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

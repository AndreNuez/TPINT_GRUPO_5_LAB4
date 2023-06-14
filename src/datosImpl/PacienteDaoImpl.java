package datosImpl;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.List;

import datos.PacienteDao;
import entidad.Persona;

public class PacienteDaoImpl implements PacienteDao {
	
	private Conexion cn;
	
	public PacienteDaoImpl () {
		
	}

	@Override
	public ArrayList<Persona> ListarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona ListarUno(int dni) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// ANDRE
	@Override
	public boolean InsertarPaciente(Persona paciente) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	

		String query = "INSERT INTO pacientes (dni,apellido,nombres,sexo,nacionalidad,fechanacimiento,IDdireccion,mail,telefono,estado) VALUES ('"+paciente.getDNI()+"','"+paciente.getApellido()+"','"+paciente.getApellido()+"','"+paciente.getSexo()+"','"+paciente.getNacionalidad()+"','"+paciente.getFnac()+"','"+paciente.getDireccion().getCalle()+"','"+paciente.getDireccion().getNumero()+"','"+paciente.getDireccion().getLocalidad()+"','"+paciente.getDireccion().getProvincia()+"'\"','"+paciente.getMail()+"','"+paciente.getTelefono()+"','"+paciente.getEstado()+")";
		System.out.println(query);
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

	// ANDRE
	public boolean EditarPaciente(Persona paciente) {
		
		boolean estado = true;

		cn = new Conexion();
		cn.Open();	
		
		//Reemplazar por SP
		String query = "UPDATE Personas SET DNI='"+paciente.getDNI()+"', Nombre='"+paciente.getNombre()+"', Apellido='"+paciente.getApellido()+"', Sexo='"+paciente.getSexo()+"', Nacionalidad='"+paciente.getNacionalidad()+"', FNac='"+paciente.getFnac()+"', Calle='"+paciente.getDireccion().getCalle()+"', Numero='"+paciente.getDireccion().getNumero()+"', Localidad='"+paciente.getDireccion().getLocalidad()+"', Provincia='"+paciente.getDireccion().getProvincia()+"', Mail='"+paciente.getMail()+"', Telefono='"+paciente.getTelefono()+"', Estado='"+paciente.getEstado()+"' WHERE DNI='"+paciente.getDNI()+"'";
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
	

	@Override
	public boolean EliminarPaciente(int dni) {
		
		cn = new Conexion();
		cn.Open();
		
		boolean estado = false;
		
		try
		{
			
			
			String query = "Delete from pacientes where DNI = " + dni;
			estado = cn.execute(query);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return estado;
	}
	
}

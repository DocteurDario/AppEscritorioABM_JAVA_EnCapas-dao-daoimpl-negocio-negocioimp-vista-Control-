package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import dao.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {

	private static final String insert = "INSERT INTO Personas(Dni,Nombre,Apellido) values (?,?,?)";
	private static final String readall = "SELECT * FROM bdpersonas.personas;";
	private static final String update = "UPDATE bdpersonas.personas SET Nombre= ?, Apellido= ? WHERE Dni= ?;";
	private static final String dniExistente = "SELECT COUNT(*) from Personas where Dni=?";
	private static final String delete = "DELETE FROM Personas WHERE Dni = ?";
	
	public boolean existeDni(String dni) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
			statement = conexion.prepareStatement(dniExistente);
			statement.setString(1, dni);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				return resultSet.getInt(1) > 0;
			}
		}
			catch(SQLException e){
				e.printStackTrace();
			}
		return false;
	}

	public boolean insert (Persona persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
				
		boolean isInsertExitoso = false;
		
		if(existeDni(persona.getDni())){
			JOptionPane.showMessageDialog(null, "El DNI ingresado ya existe en la base de datos","ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1,persona.getDni());
			statement.setString(2, persona.getNombre());
			statement.setString(3,persona.getApellido());
			
			if(statement.executeUpdate() > 0 ) {
				conexion.commit();
				isInsertExitoso = true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
		
	}

	@Override
	public boolean delete(Persona persona_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, persona_a_eliminar.getDni());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	@Override
	public List<Persona> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personas.add(getPersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personas;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException
	{
		String dni = resultSet.getString("Dni");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		return new Persona(nombre, apellido, dni);
	}

	@Override
	public boolean modificar(Persona personaModificar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean modificarExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, personaModificar.getNombre());
			statement.setString(2, personaModificar.getApellido());
			statement.setString(3, personaModificar.getDni());
			if(statement.executeUpdate() > 0) 
			{
				conexion.commit();
				modificarExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return modificarExitoso;
	}
}

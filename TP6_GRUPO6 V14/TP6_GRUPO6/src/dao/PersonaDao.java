package dao;

import java.util.List;

import entidad.Persona;

public interface PersonaDao {

	public boolean insert (Persona persona);
	public boolean delete(Persona personaBorrada);
	public boolean modificar(Persona personaModificar);
	public List<Persona> readAll();
	public boolean existeDni(String dni);
}

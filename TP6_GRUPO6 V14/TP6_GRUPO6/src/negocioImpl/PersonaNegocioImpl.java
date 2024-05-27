package negocioImpl;

import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import negocio.PersonaNegocio;

public class PersonaNegocioImpl implements PersonaNegocio {

	PersonaDao pDao = new PersonaDaoImpl();
	
	@Override
	public boolean insert(Persona persona) {
		boolean estado=false;
		if(persona.getNombre().trim().length()>0 && persona.getApellido().trim().length()>0 && persona.getDni().trim().length()>0)
		{
			estado=pDao.insert(persona);
		}
		return estado;
	}

	@Override
	public boolean modificar(Persona personaModificar) {
		boolean estado=false;
		estado = pDao.modificar(personaModificar);
		
		return estado;
	}


	@Override
	public boolean delete(Persona personaBorrada) {
		boolean estado=false;
		//if(personaBorrada.getDni().trim().length()>0 )
		//{
			estado=pDao.delete(personaBorrada);
		//}
		return estado;
	}
	
	@Override
	public List<Persona> readAll() {
		return pDao.readAll();
	}
	
	@Override
	public boolean existeDni(String dni) {
		return pDao.existeDni(dni);
	}

}

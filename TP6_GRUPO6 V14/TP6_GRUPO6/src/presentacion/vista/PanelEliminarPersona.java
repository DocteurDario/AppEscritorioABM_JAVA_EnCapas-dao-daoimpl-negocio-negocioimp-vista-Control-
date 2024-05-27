package presentacion.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import entidad.Persona;
import java.awt.Font;
import java.util.List;

public class PanelEliminarPersona extends JPanel {
	private JLabel lblEliminarPersona;
	private JList<Persona> jListadoPersona;
	private JButton btnEliminar;
	private DefaultListModel<Persona> personaModel;
	private JLabel lblMensaje;
	
	public PanelEliminarPersona() {
		super();
		dibujarControlesEliminar();
	}
	
	public void dibujarControlesEliminar() {
		setLayout(null);
		
		lblEliminarPersona = new JLabel("Eliminar usuarios: ");
		lblEliminarPersona.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEliminarPersona.setBounds(190, 41, 350, 28);
		add(lblEliminarPersona);
		
		personaModel = new DefaultListModel<>(); // Inicializa el modelo de la lista
		jListadoPersona = new JList<>(personaModel); // Asocia el modelo a la lista
		jListadoPersona.setBounds(191, 68, 349, 307);
		jListadoPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección única
		add(jListadoPersona);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEliminar.setBounds(190, 380, 350, 45);
		add(btnEliminar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensaje.setBounds(190, 430, 350, 28);
		add(lblMensaje);
	}

	public JLabel getLblEliminarPersona() {
		return lblEliminarPersona;
	}

	public void setLblEliminarPersona(JLabel lblEliminarPersona) {
		this.lblEliminarPersona = lblEliminarPersona;
	}

	public JList<Persona> getjListadoPersona() {
		return jListadoPersona;
	}

	public void setjListadoPersona(JList<Persona> jListadoPersona) {
		this.jListadoPersona = jListadoPersona;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	
	public void mostrarMensaje(String mensaje) {
		lblMensaje.setText(mensaje);
	}
	
	public void actualizarListaModel(List<Persona> personas) {
		personaModel.clear();
		for (Persona persona : personas) {
			personaModel.addElement(persona);
		}
		jListadoPersona.setModel(personaModel); // ver como funciona con esto------------
	}

	public DefaultListModel<Persona> getPersonaModel() {
		if (personaModel == null) {
			personaModel = new DefaultListModel<>();
		}
		return personaModel;
	}

	public void setPersonaModel(DefaultListModel<Persona> personaModel) {
		this.personaModel = personaModel;
	}
	
	public Persona getPersonaSeleccionada() {
	    return jListadoPersona.getSelectedValue();
	}
	
}

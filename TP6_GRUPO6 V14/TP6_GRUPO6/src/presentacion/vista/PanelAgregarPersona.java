package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelAgregarPersona extends JPanel {
	
	
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	
	private JButton btnAceptar; 

	public PanelAgregarPersona() {
		super();
		dibujarControles();
	}
	
	public void dibujarControles() {
		setBounds(50, 50, 400, 300);
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre : ");
		lblNombre.setBounds(60, 43, 77, 16);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(156, 40, 116, 22);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setBounds(60, 78, 77, 16);
		add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setBounds(156, 75, 116, 22);
		add(txtApellido);
		txtApellido.setColumns(10);
		
		JLabel lblDni = new JLabel("Dni  :");
		lblDni.setBounds(60, 107, 55, 16);
		add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtDni.setBounds(156, 110, 116, 22);
		add(txtDni);
		txtDni.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(121, 162, 84, 25);
		add(btnAceptar);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtDni() {
		return txtDni;
	}

	public void setTxtDni(JTextField txtDni) {
		this.txtDni = txtDni;
	}

	public void mostrarMensajeConfirmacion(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	public void mostrarMensajeError(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
	}
}


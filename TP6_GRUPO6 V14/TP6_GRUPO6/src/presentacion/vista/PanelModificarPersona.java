package presentacion.vista;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelModificarPersona extends JPanel {
	private JLabel lblDni;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JTextField txtDNI;
	private JButton btnModificar;
	
	private JTable tblPersonas;
	private DefaultTableModel mdlPersonas;
	private String[] nombreColumnas = {"DNI","Nombre", "Apellido"};
	
	public PanelModificarPersona() {
		super();
		dibujarControles();
	}
	
	
	public JTextField getTxtDni() {
		return txtDNI;
	}


	public void setTxtDni(JTextField txtDni) {
		this.txtDNI = txtDni;
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


	public JButton getBtnModificar() {
		return btnModificar;
	}


	public void setBtnModificar(JButton btnModificar) {
		this.btnModificar = btnModificar;
	}


	private void dibujarControles() {
		setBounds(100, 100, 800, 600);
		setLayout(null);
		
		lblDni = new JLabel("Dni");
		lblDni.setBounds(38, 395, 28, 14);
		add(lblDni);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(465, 392, 159, 20);
		add(txtApellido);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(180, 395, 48, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(238, 392, 159, 20);
		add(txtNombre);
		
		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(407, 395, 48, 14);
		add(lblApellido);
		
		txtDNI = new JTextField();
		txtDNI.setEditable(false);
		txtDNI.setBounds(68, 392, 102, 20);
		add(txtDNI);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(634, 391, 110, 23);
		add(btnModificar);
		
		JScrollPane spListaPersonas;
		spListaPersonas = new JScrollPane();
		spListaPersonas.setBounds(64, 33, 680, 331);
		add(spListaPersonas);
		
		mdlPersonas = new DefaultTableModel(null, nombreColumnas);
		tblPersonas = new JTable(mdlPersonas);
		tblPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spListaPersonas.setViewportView(tblPersonas);
	}


	public JTable getTblPersonas() {
		return tblPersonas;
	}


	public DefaultTableModel getMdlPersonas() {
		return mdlPersonas;
	}


	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public void llenarTabla(List<Persona> listaPersonas) 
	{
		this.getMdlPersonas().setRowCount(0); // PARA VACIAR TABLA
		this.getMdlPersonas().setColumnCount(0);
		this.getMdlPersonas().setColumnIdentifiers(this.getNombreColumnas());
		
		for(Persona p : listaPersonas) 
		{
			String dni = p.getDni();
			String nombre = p.getNombre();
			String apellido = p.getApellido();
			Object[] row = {dni, nombre, apellido};
			this.getMdlPersonas().addRow(row);
		}
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

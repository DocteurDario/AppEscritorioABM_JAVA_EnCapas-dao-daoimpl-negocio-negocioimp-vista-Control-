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

public class PanelListarPersona extends JPanel {
	private JTable tblPersonas;
	private DefaultTableModel mdlPersonas;
	private String[] nombreColumnas = {"DNI","Nombre", "Apellido"};
	
	public PanelListarPersona() {
		super();
		dibujarControles();
	}
	
	private void dibujarControles() {
		setBounds(100, 100, 800, 600);
		setLayout(null);
		
		JScrollPane spListaPersonas;
		spListaPersonas = new JScrollPane();
		spListaPersonas.setBounds(64, 33, 680, 331);
		add(spListaPersonas);
		
		mdlPersonas = new DefaultTableModel(null, nombreColumnas);
		tblPersonas = new JTable(mdlPersonas);
		//tblPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
		this.getMdlPersonas().setRowCount(0);
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
}

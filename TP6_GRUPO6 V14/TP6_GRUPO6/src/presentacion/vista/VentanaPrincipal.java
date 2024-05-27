package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JMenuBar menuBar;
	private JMenu mnPersonas;
	private JMenuItem menuItemAgregar;
	private JMenuItem menuItemModificar;
	private JMenuItem menuItemEliminar;
	private JMenuItem menuItemListar;
	
	
	public VentanaPrincipal() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
		menuItemAgregar = new JMenuItem("Agregar");
		mnPersonas.add(menuItemAgregar);
		
		menuItemModificar = new JMenuItem("Modificar");
		mnPersonas.add(menuItemModificar);
		
		menuItemEliminar = new JMenuItem("Eliminar");
		mnPersonas.add(menuItemEliminar);
		
		menuItemListar = new JMenuItem("Listar");
		mnPersonas.add(menuItemListar);
	}


	public JMenu getMnPersonas() {
		return mnPersonas;
	}


	public void setMnPersonas(JMenu mnPersonas) {
		this.mnPersonas = mnPersonas;
	}


	public JMenuItem getMenuItemAgregar() {
		return menuItemAgregar;
	}


	public void setMenuItemAgregar(JMenuItem menuItemAgregar) {
		this.menuItemAgregar = menuItemAgregar;
	}


	public JMenuItem getMenuItemModificar() {
		return menuItemModificar;
	}


	public void setMenuItemModificar(JMenuItem menuItemModificar) {
		this.menuItemModificar = menuItemModificar;
	}


	public JMenuItem getMenuItemEliminar() {
		return menuItemEliminar;
	}


	public void setMenuItemEliminar(JMenuItem menuItemEliminar) {
		this.menuItemEliminar = menuItemEliminar;
	}


	public JMenuItem getMenuItemListar() {
		return menuItemListar;
	}


	public void setMenuItemListar(JMenuItem menuItemListar) {
		this.menuItemListar = menuItemListar;
	}
}

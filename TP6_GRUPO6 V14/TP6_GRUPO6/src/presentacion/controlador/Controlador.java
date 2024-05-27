package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import presentacion.vista.PanelAgregarPersona;
import presentacion.vista.PanelEliminarPersona;
import presentacion.vista.PanelListarPersona;
import presentacion.vista.PanelModificarPersona;
import presentacion.vista.VentanaPrincipal;

public class Controlador implements ActionListener {
	private VentanaPrincipal ventanaPrincipal;
	private PanelAgregarPersona panelAgregarPersona;
	private PanelModificarPersona panelModificarPersona;
	private PanelEliminarPersona panelEliminarPersona;
	private PanelListarPersona panelListarPersona;
	private ArrayList<Persona> listaTabla;
	private PersonaNegocio pNeg;
	
	public Controlador(VentanaPrincipal vista, PersonaNegocio pNeg) {
		this.ventanaPrincipal = vista;
		this.pNeg = pNeg;
		
		// Instancias de paneles
		this.panelAgregarPersona = new PanelAgregarPersona();
		this.panelModificarPersona = new PanelModificarPersona();
		this.panelEliminarPersona = new PanelEliminarPersona();
		this.panelListarPersona = new PanelListarPersona();
		
		// Eventos de menu
		this.ventanaPrincipal.getMenuItemAgregar().addActionListener(a->EventoClickMenu_AbrirPanel_AgregarPersona(a));
		this.ventanaPrincipal.getMenuItemModificar().addActionListener(a->EventoClickMenu_AbrirPanel_ModificarPersona(a));
		this.ventanaPrincipal.getMenuItemEliminar().addActionListener(a->EventoClickMenu_AbrirPanel_EliminarPersona(a));
		this.ventanaPrincipal.getMenuItemListar().addActionListener(a->EventoClickMenu_AbrirPanel_ListarPersona(a));
		
		// Eventos panel Agregar
		
		this.panelAgregarPersona.getBtnAceptar().addActionListener(a->EventoClick_AgregarPersona(a));
		this.panelAgregarPersona.getTxtNombre().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char valNombre = e.getKeyChar();
				if(!Character.isLetter(valNombre) && valNombre != KeyEvent.VK_BACK_SPACE && valNombre != KeyEvent.VK_SPACE) {
					e.consume();
				}
			}
		});
		this.panelAgregarPersona.getTxtApellido().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char valApellido = e.getKeyChar();
				if(!Character.isLetter(valApellido) && valApellido != KeyEvent.VK_BACK_SPACE && valApellido != KeyEvent.VK_SPACE) {
					e.consume();
				}
				
			}
		});
		this.panelAgregarPersona.getTxtDni().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char valDni = e.getKeyChar();
				if(!Character.isDigit(valDni)) {
					e.consume();
				}
			}
		});
		
		//Eventos panel Modificar
		this.panelModificarPersona.getTblPersonas().getSelectionModel().addListSelectionListener(a->EventoCambiarFila_SeleccionUsuario_ModificarPersona());
		this.panelModificarPersona.getBtnModificar().addActionListener(a->EventoClick_Modificar_Persona(a));
		this.panelModificarPersona.getTxtNombre().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        Evento_Key_VerificarCaracter(e);
		    }
		});
		this.panelModificarPersona.getTxtApellido().addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        Evento_Key_VerificarCaracter(e);
		    }
		});
		
		//Eventos PanelEliminarPersonas
		this.panelEliminarPersona.getBtnEliminar().addActionListener(s -> EventoClickBoton_BorrarPesona_PanelEliminarPersonas(s));
	
	}

	public void EventoClickMenu_AbrirPanel_AgregarPersona(ActionEvent a) {
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panelAgregarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void EventoClickMenu_AbrirPanel_ModificarPersona(ActionEvent a) {
		this.actualizarTabla();
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panelModificarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void EventoClickMenu_AbrirPanel_EliminarPersona(ActionEvent a) {
		this.actualizarListaPersonas();
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panelEliminarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void EventoClickMenu_AbrirPanel_ListarPersona(ActionEvent a) {
		this.actualizarTabla();
		ventanaPrincipal.getContentPane().removeAll();
		ventanaPrincipal.getContentPane().add(panelListarPersona);
		ventanaPrincipal.getContentPane().repaint();
		ventanaPrincipal.getContentPane().revalidate();
	}
	
	public void inicializar()
	{ 
		this.ventanaPrincipal.setVisible(true);
	}
	
	// Eventos panel Agregar
	
	public void EventoClick_AgregarPersona(ActionEvent a) {
		try {
			if(panelAgregarPersona.getTxtNombre().getText().trim().isEmpty() && panelAgregarPersona.getTxtApellido().getText().trim().isEmpty()) {
				String mensaje = "No se creó el registro. Por favor, complete todos los campos.";
				panelAgregarPersona.mostrarMensajeError(mensaje);
			}
			else if (panelAgregarPersona.getTxtDni().getText().length() < 7 || panelAgregarPersona.getTxtDni().getText().length() > 8) {
				String mensaje = "No se creó el registro. Por favor, ingrese un DNI válido.";
				panelAgregarPersona.mostrarMensajeError(mensaje);
			}
			else if (pNeg.existeDni(panelAgregarPersona.getTxtDni().getText())) {
				String mensaje = "No se creó el registro. Ya existe otro registro con el DNI ingresado.";
				panelAgregarPersona.mostrarMensajeError(mensaje);
			}
			else {
				Persona persona = new Persona();
				persona.setDni(panelAgregarPersona.getTxtDni().getText());
				persona.setNombre(panelAgregarPersona.getTxtNombre().getText());
				persona.setApellido(panelAgregarPersona.getTxtApellido().getText());
				pNeg.insert(persona);
				
				String mensaje = "Registro creado exitosamente.";
				panelAgregarPersona.mostrarMensajeConfirmacion(mensaje);
				
				panelAgregarPersona.getTxtDni().setText("");
				panelAgregarPersona.getTxtNombre().setText("");
				panelAgregarPersona.getTxtApellido().setText("");
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//DESARROLLO EVENTOS PANEL MODIFICAR
	
	public void EventoCambiarFila_SeleccionUsuario_ModificarPersona() {
		if (panelModificarPersona.getTblPersonas().getSelectedRow() != -1) 
		{
			int filaSeleccionada = panelModificarPersona.getTblPersonas().getSelectedRow();
			panelModificarPersona.getTxtDni().setText((String)panelModificarPersona.getTblPersonas().getValueAt(filaSeleccionada, 0));
			panelModificarPersona.getTxtNombre().setText((String)panelModificarPersona.getTblPersonas().getValueAt(filaSeleccionada, 1));
			panelModificarPersona.getTxtApellido().setText((String)panelModificarPersona.getTblPersonas().getValueAt(filaSeleccionada, 2));
		}
	}
	
	public void EventoClick_Modificar_Persona(ActionEvent a) 
	{
		boolean estado = false;
		int filaSeleccionada = panelModificarPersona.getTblPersonas().getSelectedRow();
		
		if(filaSeleccionada != -1) 
		{
			if(panelModificarPersona.getTxtNombre().getText().trim().isEmpty() || panelModificarPersona.getTxtApellido().getText().trim().isEmpty()) 
			{				
				String mensaje = "No se permiten campos vacios";
				panelModificarPersona.mostrarMensajeError(mensaje);
			}else
			{	
				Persona personaModificar = this.listaTabla.get(filaSeleccionada);
				
				personaModificar.setNombre(panelModificarPersona.getTxtNombre().getText());
				personaModificar.setApellido(panelModificarPersona.getTxtApellido().getText());
				
				estado = pNeg.modificar(personaModificar);
				
				if(estado) 
				{
					this.actualizarTabla();
					panelModificarPersona.getTxtDni().setText("");
					panelModificarPersona.getTxtNombre().setText("");
					panelModificarPersona.getTxtApellido().setText("");
					
					String mensaje = "Registro actulizado correctamente";
					panelModificarPersona.mostrarMensajeConfirmacion(mensaje);			
				}else
				{
					String mensaje = "Ha ocurrido un error al actualizar el registro.";
					panelModificarPersona.mostrarMensajeError(mensaje);
				}

			}
		}

	}
	
	public void Evento_Key_VerificarCaracter(KeyEvent e) {
	    char c = e.getKeyChar();
	    if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != KeyEvent.VK_BACK_SPACE) {
	        e.consume(); // IGNORAR EL CARACTER SI ES UN NUMERO.
	    }
	}
	private void actualizarTabla() 
	{
		this.listaTabla = (ArrayList<Persona>) pNeg.readAll();
		this.panelModificarPersona.llenarTabla(this.listaTabla);
		this.panelListarPersona.llenarTabla(this.listaTabla);
	}
	
	// Eventos Panel Eliminar
	
	public void actualizarListaPersonas() {
	    // Obtener la lista de personas del negocio
	    List<Persona> personas = pNeg.readAll();
	    
	    // Actualizar el JList en el PanelEliminarPersonas
	    panelEliminarPersona.actualizarListaModel(personas);
	}
	
	public void actualizarListaModel(List<Persona> personas) {
	    DefaultListModel<Persona> modeloLista = new DefaultListModel<>();
	    for (Persona persona : personas) {
	        modeloLista.addElement(persona);
	    }
	    panelEliminarPersona.getjListadoPersona().setModel(modeloLista);
	}
	
	private void EventoClickBoton_BorrarPesona_PanelEliminarPersonas(ActionEvent e) {
	    Persona personaSeleccionada = panelEliminarPersona.getPersonaSeleccionada();
	    
	    // Verificar si se ha seleccionado una persona
	    if (personaSeleccionada != null) {
	        // Mostrar mensaje con los datos de la persona seleccionada
	        JOptionPane.showMessageDialog(null, "Persona seleccionada:\nDNI: " + personaSeleccionada.getDni() + 
	                                            "\nNombre: " + personaSeleccionada.getNombre() + 
	                                            "\nApellido: " + personaSeleccionada.getApellido(), 
	                                      "Persona Seleccionada", JOptionPane.INFORMATION_MESSAGE);

	        // Eliminar la persona de la base de datos
	        boolean estado = pNeg.delete(personaSeleccionada);
	        
	        // Mostrar mensaje de éxito o error
	        String mensaje;
	        if (estado) {
	            mensaje = "Persona eliminada con éxito";
	        } else {
	            mensaje = "No se pudo eliminar la persona";
	        }
	        panelEliminarPersona.mostrarMensaje(mensaje);
	        
	        // Actualizar la lista de personas
	        actualizarListaPersonas();
	    } else {
	        // Mostrar mensaje de error si no se ha seleccionado una persona
	        panelEliminarPersona.mostrarMensaje("Por favor, seleccione una persona para eliminar");
	    }
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
}

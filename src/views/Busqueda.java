package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Controllers.ReservasControllers;
import Controllers.HuespedesControllers;
import com.hotel.modelo.*;

@SuppressWarnings("serial")
public class Busqueda<modelo> extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHuesped;
	private JLabel labelAtras;
	private JLabel labelExit;
	private ReservasControllers reservacontrollers;
	private HuespedesControllers huespedControllers;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		this.reservacontrollers = new ReservasControllers();
		this.huespedControllers = new HuespedesControllers();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if (!Character.isDigit(c) && !Character.isLetter(c)) {
		            e.consume(); // no se permite ingresar el caracter
		        }
		    }
		});


		
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 280, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		Integer id = 0;
		cargarTabla(id);
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		modeloHuesped.addColumn("Número de Huesped");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Fecha de Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("Número de Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		String texto = "";
		cargarTablaHuesped(texto);

		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked (MouseEvent e) {   
			//	 limpiarTabla();
		            if (!"".equals(txtBuscar.getText())) {	          
		            	 String text = txtBuscar.getText();  	            
			        if (text.matches("\\d+")) { // verifica si es un número entero
			            int numero = Integer.parseInt(text);
						String texto ="N";
						modelo.setRowCount(0);
			            buscar(numero,texto);
			    		
			        } else {
			        	
			        	String texto = text;
			        	int numero = 0;
			        	modeloHuesped.setRowCount(0);
						buscar(numero,texto);
			        	}
			      	
		            }else {
		            	limpiarTabla();
		            	String texto = "";
		        		cargarTablaHuesped(texto);
		        		Integer id = 0;
		        		cargarTabla(id);
		            }
		        }
		            
		      
			});
		
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
			modificar();	
			}
		});
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				eliminar();
			}
		});
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
	}
	
private void buscar(Integer id, String texto) {
	if(texto == "N") {
		
		cargarTabla(id);
	//Buscar por reserva
  }else {
	 //Buscar por Huespedes

	  cargarTablaHuesped(texto);
	 
/*	var busca = this.huespedControllers.listBuscarHuesped(nuevaBusqueda);
	 modeloHuesped.setRowCount(0);
	 busca.forEach(buscar-> modeloHuesped.addRow(new Object[] {
		   buscar.getId(),
		   buscar.getNombre(),
		   buscar.getApellido(),
		   buscar.getFechaNacimiento(),
		   buscar.getNacionalidad(),
		   buscar.getTelefono(),
		   buscar.getIdReserva()
		   }));
	    		
  */}		
		
	}


private void cargarTablaHuesped(String texto) {
		
		var huespedes = this.huespedControllers.listarH(texto);
		
	    // TODO
	    huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { 
	    		huesped.getId(),
	    		huesped.getNombre(),
	    		huesped.getApellido(),
	    		huesped.getFechaNacimiento(),
	    		huesped.getNacionalidad(),
	    		huesped.getTelefono(),
	    		huesped.getIdReserva()}));
		}

		

private void cargarTabla(Integer id) {
	
	var reservas = this.reservacontrollers.listar(id);
	
    // TODO
    reservas.forEach(reserva -> modelo.addRow(new Object[] { 
    		reserva.getId(),
    		reserva.getFechaE(),
            reserva.getFechaS(), 
            reserva.getValor(),
            reserva.getFormPago()}));
	}

private String tieneFilaElegida() {
	String seleccion = "";
	if (tbReservas.getSelectedRowCount() > 0 && tbReservas.getSelectedColumnCount() > 0) {
	    // se seleccionó al menos una celda en tbReservas
	    seleccion = "R";
	} else if (tbHuespedes.getSelectedRowCount() > 0 && tbHuespedes.getSelectedColumnCount() > 0) {
	    // se seleccionó al menos una celda en tbHuespedes
	    seleccion = "H";
	} else {
	    // no se seleccionó ninguna celda en ninguna de las tablas
	    seleccion = "N";
	}
    return seleccion;
}

private void modificar() {
	
	switch(tieneFilaElegida()) {
	case "N":
        JOptionPane.showMessageDialog(this, "Por favor, elije una reserva a Eliminar");
        return;

     
	case "R":
		   Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
	       .ifPresentOrElse(fila -> {
	    	   
	           Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	           String fechaEStr = modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
	           String fechaSStr =  modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
	           String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
	           String formPago = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
	           
	           Reserva nuevaReserva = new Reserva(id, java.sql.Date.valueOf(fechaEStr),
	        		   java.sql.Date.valueOf(fechaSStr),valor, formPago );
			  
	             
	           int cantidadModificada;
	           
	          // System.out.printf("Enviado desde la base: " + fechaE, fechaS, valor,formPago ,id);
	 
					cantidadModificada = this.reservacontrollers.modificar(nuevaReserva);
		
	           
	           JOptionPane.showMessageDialog(this, cantidadModificada + " Item modificado con éxito!");
	           
	       }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		
		break;
	case "H":
		   Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
	       .ifPresentOrElse(fila -> {
	           Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbReservas.getSelectedRow(), 0).toString());
	           String nombre = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1);
	           String apellido = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2);
	           String fNacimiento =  modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();       
	           String nacionalidad = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4);
	           String telefono = (String) modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5);
	           Integer idR = Integer.valueOf(modeloHuesped.getValueAt(tbReservas.getSelectedRow(), 6).toString());
	          
	           Huesped huespedNuevo = new Huesped(id, nombre, apellido ,java.sql.Date.valueOf(fNacimiento)
	        	,nacionalidad, telefono, idR);
	           
	           int cantidadModificada;
	           
	        //   System.out.printf("Enviado de Huespedes: " +id + nombre, apellido, nacionalidad,telefono ,idR);
	 
					cantidadModificada = this.huespedControllers.modificarH(huespedNuevo);
		
	           
	           JOptionPane.showMessageDialog(this, cantidadModificada + " Item modificado con éxito!");
	           
	       }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		
		break;
	}
}

private void eliminar() {
	switch(tieneFilaElegida()) {
	case "N":
        JOptionPane.showMessageDialog(this, "Por favor, elije un item a Eliminar");
        return;
	case "R":
		Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            
            int cantidadEliminada ;
              String tabla = "reservas"; 
				cantidadEliminada = this.reservacontrollers.eliminar(id,tabla);
	
            modelo.removeRow(tbReservas.getSelectedRow());

            JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		break;
	case "H":
		Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn()))
        .ifPresentOrElse(fila -> {
            Integer id = Integer.valueOf(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
            
            int cantidadEliminada ;
            String tabla = "huespedes";
				cantidadEliminada = this.huespedControllers.eliminar(id,tabla);
	
            modeloHuesped.removeRow(tbHuespedes.getSelectedRow());

            JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
		break;
		
	}
	
}

	private void limpiarTabla() {
		((DefaultTableModel) tbHuespedes.getModel()).setRowCount(0);
		((DefaultTableModel) tbReservas.getModel()).setRowCount(0);
		
	}

	//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}

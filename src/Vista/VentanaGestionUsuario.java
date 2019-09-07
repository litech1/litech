package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.invertor.modbus.serial.SerialPortException;

import ControlUsuarios.Usuario;
import Controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class VentanaGestionUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField rut;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField cargo;
	private JTextField email;
	private JPasswordField contraseña;
	private JComboBox tipoUsuario;
	private JCheckBox estadoUsuario;
	private Usuario interfazUsuario;
	private Controlador controlador;
	
	private JButton btnBuscar;
	private JButton btnEditar;
	private JButton btnLimpiar;
	private JButton btnGuardar;
	private JButton btnCancelar;

	public VentanaGestionUsuario() throws SQLException, IOException, SerialPortException {
		
		interfazUsuario = new Usuario();
		controlador = new Controlador();
		
		setIconImage(new ImageIcon("Imagenes/favicon.png").getImage());
		setTitle("Monitor v1.1");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 370, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 26, 331, 284);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(191, 5, 0, 0);
		panel.add(label);
		
		JLabel lblNombre = new JLabel("RUT");
		lblNombre.setBounds(10, 11, 68, 14);
		panel.add(lblNombre);
		
		rut = new JTextField();
		rut.setBounds(88, 8, 130, 20);
		panel.add(rut);
		rut.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 40, 68, 14);
		panel.add(lblNewLabel);
		
		nombre = new JTextField();
		nombre.setBounds(88, 37, 130, 20);
		panel.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 71, 68, 14);
		panel.add(lblApellido);
		
		apellido = new JTextField();
		apellido.setBounds(88, 68, 130, 20);
		panel.add(apellido);
		apellido.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(10, 102, 68, 14);
		panel.add(lblCargo);
		
		cargo = new JTextField();
		cargo.setBounds(88, 99, 130, 20);
		panel.add(cargo);
		cargo.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 136, 68, 14);
		panel.add(lblEmail);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 169, 68, 14);
		panel.add(lblContrasea);
		
		email = new JTextField();
		email.setBounds(88, 133, 130, 20);
		panel.add(email);
		email.setColumns(10);
		
		contraseña = new JPasswordField();
		contraseña.setBounds(88, 166, 130, 20);
		panel.add(contraseña);
		
		JLabel lblPrivilegios = new JLabel("Privilegios");
		lblPrivilegios.setBounds(10, 198, 68, 14);
		panel.add(lblPrivilegios);
		
		tipoUsuario = new JComboBox();
		tipoUsuario.setBounds(88, 195, 130, 20);
		tipoUsuario.addItem("Usuario");
		tipoUsuario.addItem("Supervisor");
		tipoUsuario.addItem("Administrador");
		panel.add(tipoUsuario);
		
		estadoUsuario = new JCheckBox("Habilitado");
		estadoUsuario.setBackground(Color.GRAY);
		estadoUsuario.setBounds(88, 225, 97, 23);
		panel.add(estadoUsuario);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Usuario usuarioConsulta = new Usuario();
				try {
					usuarioConsulta.buscarUsuario(rut.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				nombre.setText(usuarioConsulta.getNombre());
				apellido.setText(usuarioConsulta.getApellido());
				cargo.setText(usuarioConsulta.getCargo());
				email.setText(usuarioConsulta.getEmail());
				estadoUsuario.setSelected(usuarioConsulta.isEnabled());
				
				nombre.setEditable(false);
				apellido.setEditable(false);
				cargo.setEditable(false);
				email.setEditable(false);
				estadoUsuario.setEnabled(false);
				
				switch(usuarioConsulta.getTipo()){
				
				case "Usuario":
					tipoUsuario.setSelectedIndex(0);
					break;
				
				case "Supervisor":
					tipoUsuario.setSelectedIndex(1);
					break;
				
				case "Administrador":
					tipoUsuario.setSelectedIndex(2);
					break;
				}	
				
				tipoUsuario.setEditable(false);
				
				btnEditar.setVisible(true);
				btnLimpiar.setVisible(true);
				btnGuardar.setEnabled(false);
				
				
				
			}
		});
		btnBuscar.setBounds(228, 5, 80, 31);
		panel.add(btnBuscar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(228, 47, 80, 31);
		panel.add(btnEditar);
		
		JCheckBox chckbxNotificar = new JCheckBox("Notificar");
		chckbxNotificar.setBackground(Color.GRAY);
		chckbxNotificar.setBounds(88, 254, 97, 23);
		panel.add(chckbxNotificar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				nombre.setEditable(true);
				apellido.setEditable(true);
				cargo.setEditable(true);
				email.setEditable(true);
				estadoUsuario.setEnabled(true);
				
				rut.setText("");
				nombre.setText("");
				apellido.setText("");
				cargo.setText("");
				email.setText("");
				estadoUsuario.setEnabled(true);
				estadoUsuario.setSelected(false);
				
				btnEditar.setVisible(false);
				btnLimpiar.setVisible(false);
			}
		});
		btnLimpiar.setBounds(228, 89, 80, 31);
		panel.add(btnLimpiar);
		
		btnEditar.setVisible(false);
		btnLimpiar.setVisible(false);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(btnGuardar.isEnabled()){
					Usuario usuario = new Usuario();
					try {
						if(usuario.isUsuario(rut.getText())){
							
							JOptionPane.showMessageDialog(rootPane, "Usuario ya registrado");
						}
						
						else{
							
						usuario.setRut(rut.getText());
						usuario.setNombre(nombre.getText());
						usuario.setApellido(apellido.getText());
						usuario.setCargo(cargo.getText());
						usuario.setEmail(email.getText());
						usuario.setContraseña(contraseña.getText());
						usuario.setTipo(tipoUsuario.getSelectedItem().toString());
						usuario.setEstado(estadoUsuario.isSelected());
						
						usuario.guardarUsuario();
						JOptionPane.showMessageDialog(rootPane, "Usuario ha sido registrado");
						hide();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnGuardar.setBounds(35, 321, 102, 37);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hide();
			}
		});
		btnCancelar.setBounds(218, 321, 102, 37);
		contentPane.add(btnCancelar);
	}
}

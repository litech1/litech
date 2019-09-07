package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.invertor.modbus.serial.SerialPortException;

import Controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	
	private JTextField inputRut;
	private JPasswordField inputcontraseña;
	
	private Controlador controlador;
	
	public VentanaLogin() throws SQLException, IOException, SerialPortException {
		
		controlador = new Controlador();
		
		setIconImage(new ImageIcon("Imagenes/favicon.png").getImage());
		setTitle("Monitor v1.1");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 290, 245);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(10, 16, 254, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("RUT");
		lblUsuario.setBounds(10, 24, 45, 17);
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		inputRut = new JTextField();
		inputRut.setBounds(114, 37, 115, 32);
		contentPane.add(inputRut);
		inputRut.setColumns(10);
		
		JLabel lblPass = new JLabel("Contrase\u00F1a");
		lblPass.setBounds(10, 77, 86, 14);
		panel.add(lblPass);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		inputcontraseña = new JPasswordField();
		inputcontraseña.setBounds(114, 94, 115, 32);
		contentPane.add(inputcontraseña);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(64, 129, 125, 40);
		panel.add(btnIngresar);
		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String rutUsuario = inputRut.getText();
				String contraseña = inputcontraseña.getText();
				
				try {
					switch(controlador.validarUsuario(rutUsuario,contraseña)){
					
					case "Validado como Administrador":
						controlador.registrarIngreso(rutUsuario);
						JOptionPane.showMessageDialog(rootPane, "Bienvenido "+controlador.getNombreUsuario(rutUsuario)+" Validado como Administrador");
						hide();
						break;
						
					case "Validado como Usuario":
						controlador.registrarIngreso(rutUsuario);
						JOptionPane.showMessageDialog(rootPane, "Bienvenido "+controlador.getNombreUsuario(rutUsuario)+" Validado como Usuario");
						hide();
						break;
						
					case "Validado como Supervisor":
						controlador.registrarIngreso(rutUsuario);
						JOptionPane.showMessageDialog(rootPane, "Bienvenido "+controlador.getNombreUsuario(rutUsuario)+" Validado como Supervisor");
						hide();
						break;
						
					case "Usuario no existe":
						JOptionPane.showMessageDialog(rootPane, "Usuario no encontrado");
						hide();
						break;
						
					case "Usuario inhabilitado":
						JOptionPane.showMessageDialog(rootPane, "Usuario inhabilitado");
						hide();
						break;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
	}
}

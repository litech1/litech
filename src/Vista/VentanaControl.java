package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.invertor.modbus.exception.ModbusIOException;

import Controlador.Controlador;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaControl extends JFrame {

	private JPanel contentPane;
	private JTextField textoModulo;
	private JTextField textoDireccion;
	private JTextField textoValor;
	
	private Controlador controlador;

	public VentanaControl() {

		controlador = new Controlador();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 310, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 274, 290);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblModulo = new JLabel("Modulo");
		lblModulo.setBounds(28, 31, 61, 14);
		panel.add(lblModulo);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(99, 31, 60, 14);
		panel.add(lblDireccion);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(169, 29, 60, 14);
		panel.add(lblValor);
		
		textoModulo = new JTextField();
		textoModulo.setBounds(29, 54, 60, 20);
		panel.add(textoModulo);
		textoModulo.setColumns(10);
		
		textoDireccion = new JTextField();
		textoDireccion.setBounds(99, 54, 60, 20);
		panel.add(textoDireccion);
		textoDireccion.setColumns(10);
		
		textoValor = new JTextField();
		textoValor.setBounds(169, 54, 60, 20);
		panel.add(textoValor);
		textoValor.setColumns(10);
		
		JButton btnEscribir = new JButton("Escribir");
		btnEscribir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int slaveId = Integer.parseInt(textoModulo.getText());
				int direccion = Integer.parseInt(textoDireccion.getText());
				int valor = Integer.parseInt(textoValor.getText());
				
				controlador.setRegistro(slaveId, direccion, valor);
				
				
			}
		});
		btnEscribir.setBounds(29, 105, 89, 23);
		panel.add(btnEscribir);
		
		JButton btnLeer = new JButton("Leer");
		btnLeer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int slaveId = Integer.parseInt(textoModulo.getText());
				int direccion = Integer.parseInt(textoDireccion.getText());
				
				try {
					
					int respuesta = controlador.getRegistro(slaveId, direccion);
					
					textoValor.setText(Integer.toString(respuesta));
					
				} catch (ModbusIOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLeer.setBounds(140, 105, 89, 23);
		panel.add(btnLeer);
		
		
	}
}

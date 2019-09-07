package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import com.invertor.modbus.serial.SerialPortException;

import Controlador.Controlador;
import Procesador.Configuracion;
import Procesador.ProcesadorVariables;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.JTable;

public class VentanaConfiguracion extends JFrame {

	private JPanel contentPane;
	Controlador controlador;
	
	private JTextField textoTiempoActualizacion;
	private JTextField textoFactorAjuste;
	private JTextField textoOffset;
	
	private JTextField textoPuertoCom;
	private JComboBox boxNumeroVariables;
	private JTable tablaDirecciones;
	
	private JTextField [] arrayModulos;
	private JTextField [] arrayDirecciones;
	
	
	public VentanaConfiguracion() throws IOException, SQLException, SerialPortException {
		
		controlador = new Controlador();
		
		//controlador.inicializacion();
		
		setIconImage(new ImageIcon("Imagenes/favicon.png").getImage());
		setTitle("Monitor v1.1");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 550, 490);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 514, 381);
		contentPane.add(tabbedPane);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		tabbedPane.addTab("General", null, layeredPane_2, null);
		
		JPanel panel_general = new JPanel();
		panel_general.setBounds(0, 0, 509, 353);
		layeredPane_2.add(panel_general);
		panel_general.setLayout(null);
		
		JLabel lblTiempoDeActualizacion = new JLabel("Tiempo de actualizaci\u00F3n");
		lblTiempoDeActualizacion.setBounds(40, 50, 120, 14);
		panel_general.add(lblTiempoDeActualizacion);
		
		textoTiempoActualizacion = new JTextField();
		textoTiempoActualizacion.setBounds(170, 47, 86, 20);
		panel_general.add(textoTiempoActualizacion);
		textoTiempoActualizacion.setColumns(10);
		
		JLabel lblFactorDeAjuste = new JLabel("Factor de ajuste");
		lblFactorDeAjuste.setBounds(40, 90, 120, 14);
		panel_general.add(lblFactorDeAjuste);
		
		textoFactorAjuste = new JTextField();
		textoFactorAjuste.setBounds(170, 84, 86, 20);
		panel_general.add(textoFactorAjuste);
		textoFactorAjuste.setColumns(10);
		
		JLabel lblOffset = new JLabel("Offset");
		lblOffset.setBounds(40, 130, 120, 14);
		panel_general.add(lblOffset);
		
		textoOffset = new JTextField();
		textoOffset.setBounds(170, 127, 86, 20);
		panel_general.add(textoOffset);
		textoOffset.setColumns(10);
		
		JLabel lblOrigenDeDatos = new JLabel("Origen de datos");
		lblOrigenDeDatos.setBounds(40, 170, 120, 14);
		panel_general.add(lblOrigenDeDatos);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 167, 86, 20);
		panel_general.add(comboBox);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Serial", null, layeredPane, null);
		
		JPanel panel_serial = new JPanel();
		panel_serial.setBounds(0, 0, 509, 353);
		layeredPane.add(panel_serial);
		panel_serial.setLayout(null);
		
		JLabel lblPuertoCom = new JLabel("Puerto COM");
		lblPuertoCom.setBounds(40, 50, 120, 14);
		panel_serial.add(lblPuertoCom);
		
		textoPuertoCom = new JTextField();
		textoPuertoCom.setBounds(170, 47, 86, 20);
		panel_serial.add(textoPuertoCom);
		textoPuertoCom.setColumns(10);
		
		JLabel lblCantidadDeVariables = new JLabel("Cantidad de variables");
		lblCantidadDeVariables.setBounds(40, 90, 120, 14);
		panel_serial.add(lblCantidadDeVariables);
		
		boxNumeroVariables = new JComboBox();
		boxNumeroVariables.setBounds(206, 87, 50, 20);
		panel_serial.add(boxNumeroVariables);
		boxNumeroVariables.addItem("1");
		boxNumeroVariables.addItem("2");
		boxNumeroVariables.addItem("3");
		boxNumeroVariables.addItem("4");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(70, 157, 368, 160);
		panel_serial.add(panel);
		
		JLabel label = new JLabel("Variable 1");
		label.setBounds(40, 29, 65, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Variable 2");
		label_1.setBounds(40, 58, 65, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Variable 3");
		label_2.setBounds(40, 87, 65, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Variable 4");
		label_3.setBounds(40, 116, 65, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Modulo");
		label_4.setBounds(125, 10, 80, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Direcci\u00F3n");
		label_5.setBounds(242, 10, 80, 14);
		panel.add(label_5);
		
		arrayModulos = new JTextField[4];
		arrayDirecciones = new JTextField[4];
		
		arrayModulos[0] = new JTextField();
		arrayModulos[0].setColumns(10);
		arrayModulos[0].setBounds(123, 25, 46, 20);
		panel.add(arrayModulos[0]);
		arrayModulos[0].setEnabled(false);
		
		arrayDirecciones[0] = new JTextField();
		arrayDirecciones[0].setColumns(10);
		arrayDirecciones[0].setBounds(241, 25, 46, 20);
		panel.add(arrayDirecciones[0]);
		arrayDirecciones[0].setEnabled(false);
		
		arrayModulos[1] = new JTextField();
		arrayModulos[1].setColumns(10);
		arrayModulos[1].setBounds(123, 55, 46, 20);
		panel.add(arrayModulos[1]);
		arrayModulos[1].setEnabled(false);
		
		arrayDirecciones[1] = new JTextField();
		arrayDirecciones[1].setColumns(10);
		arrayDirecciones[1].setBounds(241, 55, 46, 20);
		panel.add(arrayDirecciones[1]);
		arrayDirecciones[1].setEnabled(false);
		
		arrayModulos[2] = new JTextField();
		arrayModulos[2].setColumns(10);
		arrayModulos[2].setBounds(123, 85, 46, 20);
		panel.add(arrayModulos[2]);
		arrayModulos[2].setEnabled(false);
		
		arrayDirecciones[2] = new JTextField();
		arrayDirecciones[2].setColumns(10);
		arrayDirecciones[2].setBounds(241, 85, 46, 20);
		panel.add(arrayDirecciones[2]);
		arrayDirecciones[2].setEnabled(false);
		
		arrayModulos[3] = new JTextField();
		arrayModulos[3].setColumns(10);
		arrayModulos[3].setBounds(123, 115, 46, 20);
		panel.add(arrayModulos[3]);
		arrayModulos[3].setEnabled(false);
		
		arrayDirecciones[3] = new JTextField();
		arrayDirecciones[3].setColumns(10);
		arrayDirecciones[3].setBounds(241, 115, 46, 20);
		panel.add(arrayDirecciones[3]);
		arrayDirecciones[3].setEnabled(false);
		
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
					try {
							if( Double.parseDouble(textoTiempoActualizacion.getText())>1){
								
								controlador.setParametroConfiguracion("Tiempo actualizacion",Double.parseDouble(textoTiempoActualizacion.getText()));
			
							}
							else{
								
							}
							
							controlador.setParametroConfiguracion("Factor ajuste", Double.parseDouble(textoFactorAjuste.getText()));
							controlador.setParametroConfiguracion("Offset", Double.parseDouble(textoOffset.getText()));
										
							JOptionPane.showMessageDialog(rootPane, "Datos guardados");
										
						} catch (NumberFormatException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						hide();
			}
		});
		
		btnGuardar.setBounds(277, 407, 118, 34);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				hide();
				
			}
		});
		
		btnCancelar.setBounds(406, 407, 118, 34);
		contentPane.add(btnCancelar);
		
		cargarDatos();
	}
	
	public void cargarDatos() throws IOException{
		
		mostrarPuertoCom();
		mostrarTiempoActualizacion();
		mostrarFactorAjuste();
		mostrarOffsetVariable();
		mostrarNumeroVariables();
		mostrarDireccionesVariables();
	}
	
	public void mostrarTiempoActualizacion() throws IOException{
		
		textoTiempoActualizacion.setText(Double.toString(controlador.getParametroConfiguracion("Tiempo actualizacion")));
		
	}
	
	public void mostrarFactorAjuste() throws IOException{
		
		textoFactorAjuste.setText(Double.toString(controlador.getParametroConfiguracion("Factor ajuste")));
		
	}
	
	public void mostrarOffsetVariable() throws IOException{
		
		textoOffset.setText(Double.toString(controlador.getParametroConfiguracion("Offset")));
		
	}
	
	public void mostrarPuertoCom() throws IOException{
			
		textoPuertoCom.setText(controlador.getPuertoSerial());
			
	}
	
	public void mostrarNumeroVariables() throws IOException{
		
		boxNumeroVariables.setSelectedIndex(controlador.getParametroVariables("Numero variables")-1);
		
	}
	
	public void mostrarDireccionesVariables() throws IOException{
		
		arrayModulos[0].setText(Integer.toString(controlador.getParametroVariables("Modulo 1")));
		arrayModulos[1].setText(Integer.toString(controlador.getParametroVariables("Modulo 2")));
		arrayModulos[2].setText(Integer.toString(controlador.getParametroVariables("Modulo 3")));
		arrayModulos[3].setText(Integer.toString(controlador.getParametroVariables("Modulo 4")));
		
		arrayDirecciones[0].setText(Integer.toString(controlador.getParametroVariables("Direccion 1")));
		arrayDirecciones[1].setText(Integer.toString(controlador.getParametroVariables("Direccion 2")));
		arrayDirecciones[2].setText(Integer.toString(controlador.getParametroVariables("Direccion 3")));
		arrayDirecciones[3].setText(Integer.toString(controlador.getParametroVariables("Direccion 4")));
		
		for (int i=0 ; i <controlador.getParametroVariables("Numero variables");i++){
			
			arrayModulos[i].setEnabled(true);
			arrayDirecciones[i].setEnabled(true);
		}
		
	}
}

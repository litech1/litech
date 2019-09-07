package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.Layer;

import com.invertor.modbus.serial.SerialPortException;

import Controlador.Controlador;
import Controlador.Main;
import Procesador.ProcesadorVariables;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.List;
import java.awt.MenuBar;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JMenuItem;

public class VentanaMonitoreo extends JFrame {
	
	private static boolean usuario = false;
	private static String rutUsuario = null;
	private int tiempoMax = 60000;
	private int rangoTiempo = 10;
	
	private Controlador controlador;
	
	private JPanel contentPane;
	
	private JMenuBar menuAdministrador;
	private JMenu mnConfig;
	private JMenu mnUsuarios;
	private JMenu mnReportes;
	private JMenu mnControl;
	
	private JLabel variableUno;
	private JLabel variableDos;
	private JLabel variableTres;
	private JLabel variableCuatro;
	private List listaEventos;
	private JButton botonUsuario;
	
	private TimeSeries [] arraySeries;
	private TimeSeriesCollection dataset;

	private IntervalMarker marca;
	private XYPlot plot;
	
	
	public VentanaMonitoreo(int numeroVariables) throws IOException, SQLException, SerialPortException {
		
		controlador = new Controlador();
		
		setIconImage(new ImageIcon("Imagenes/favicon.png").getImage());
		setTitle("Monitor v1.1");
		setMinimumSize(new Dimension(840, 630));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 630);
		
		menuAdministrador = new JMenuBar();
		setJMenuBar(menuAdministrador);
		
		mnConfig = new JMenu("Config");
		mnConfig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					controlador.setVentanaConfig();
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuAdministrador.add(mnConfig);
		
		mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					controlador.setVentanaGestionUsuario();
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuAdministrador.add(mnUsuarios);
		
		mnReportes = new JMenu("Reportes");
		menuAdministrador.add(mnReportes);
		
		mnControl = new JMenu("Control");
		mnControl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				controlador.setVentanaControl();
			
			}
		});
		menuAdministrador.add(mnControl);
		
		menuAdministrador.setVisible(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(559, 43, 255, 197);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 13, 46, 14);
		panel.add(lblFecha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionText);
		panel_1.setBounds(10, 38, 235, 30);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVariable = new JLabel("Variable 1");
		lblVariable.setFont(new Font("Arial", Font.BOLD, 13));
		lblVariable.setForeground(Color.WHITE);
		lblVariable.setBounds(10, 11, 98, 14);
		panel_1.add(lblVariable);
		
		variableUno = new JLabel("Valor 1");
		variableUno.setFont(new Font("Calcula", Font.PLAIN, 14));
		variableUno.setForeground(Color.WHITE);
		variableUno.setBounds(118, 11, 107, 14);
		panel_1.add(variableUno);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionText);
		panel_3.setBounds(10, 79, 235, 30);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblVariable_1 = new JLabel("Variable 2");
		lblVariable_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblVariable_1.setForeground(Color.WHITE);
		lblVariable_1.setBounds(10, 11, 98, 14);
		panel_3.add(lblVariable_1);
		
		variableDos = new JLabel("Valor 2");
		variableDos.setFont(new Font("Calcula", Font.PLAIN, 14));
		variableDos.setForeground(Color.WHITE);
		variableDos.setBounds(118, 11, 107, 14);
		panel_3.add(variableDos);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaptionText);
		panel_4.setBounds(10, 120, 235, 30);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblVariable_2 = new JLabel("Variable 3");
		lblVariable_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblVariable_2.setForeground(Color.WHITE);
		lblVariable_2.setBounds(10, 11, 98, 14);
		panel_4.add(lblVariable_2);
		
		variableTres = new JLabel("Valor 3");
		variableTres.setFont(new Font("Calcula", Font.PLAIN, 14));
		variableTres.setForeground(Color.WHITE);
		variableTres.setBounds(118, 11, 57, 14);
		panel_4.add(variableTres);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.inactiveCaptionText);
		panel_5.setBounds(10, 156, 235, 30);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Variable 4");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 98, 14);
		panel_5.add(lblNewLabel);
		
		variableCuatro = new JLabel("Valor 4");
		variableCuatro.setFont(new Font("Calcula", Font.PLAIN, 14));
		variableCuatro.setForeground(Color.WHITE);
		variableCuatro.setBounds(118, 11, 57, 14);
		panel_5.add(variableCuatro);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(559, 251, 255, 192);
		contentPane.add(panel_2);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setBounds(10, 11, 46, 14);
		panel_2.add(lblLog);
		
		listaEventos = new List();
		listaEventos.setBounds(10, 39, 235, 143);
		panel_2.add(listaEventos);
		
		ImageIcon logo = new ImageIcon("Imagenes/Logo_systems_240.png");
		
		ImageIcon imagenUsuario = new ImageIcon("Imagenes/usuario_icon_20.png");
		botonUsuario = new JButton(imagenUsuario);
		botonUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			try {
				
				if (usuario){
					botonUsuario.setIcon(imagenUsuario);
					controlador.registrarSalida(rutUsuario);
					setUsuario(false, null);
					menuAdministrador.setVisible(false);
				}
				else{
					controlador.setVentanaLogin();
					
				}
				
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
		});
		
		botonUsuario.setBorder(null);
		botonUsuario.setBounds(559, 468, 255, 91);
		contentPane.add(botonUsuario);
		
		
		JPanel panelGraficoSuperior = new JPanel();
		panelGraficoSuperior.setBounds(36, 43, 500, 400);
		panelGraficoSuperior.setLayout(new java.awt.BorderLayout());
		contentPane.add(panelGraficoSuperior);
		
		arraySeries = new TimeSeries[numeroVariables];
		dataset = new TimeSeriesCollection();
		
		for(int i=0; i<arraySeries.length;i++){
			
			arraySeries[i]= new TimeSeries("Variable "+i, Second.class);
			dataset.addSeries(arraySeries[i]);
			
		}
		
		JFreeChart chartSuperior = ChartFactory.createTimeSeriesChart("Grafico variables", "tiempo", "Valor", dataset);
		
		plot = (XYPlot)chartSuperior.getPlot();
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		plot.setBackgroundPaint(Color.WHITE);
		plot.setDomainGridlinePaint(Color.BLACK);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		rangoTiempo = controlador.getParametroGrafico("Rango tiempo");
		tiempoMax = controlador.getParametroGrafico("Tiempo max");
		
		DateAxis ejeX = (DateAxis) chartSuperior.getXYPlot().getDomainAxis();
		ejeX.setTickUnit(new DateTickUnit(DateTickUnitType.SECOND,rangoTiempo));
		ejeX.setTickMarkPosition(DateTickMarkPosition.START);
		ejeX.setDateFormatOverride(new SimpleDateFormat("mm:ss"));	
		ejeX.setFixedAutoRange(tiempoMax);
		
		NumberAxis range = (NumberAxis)plot.getRangeAxis();  
        range.setTickUnit(new NumberTickUnit(1)); 
	
		ChartPanel chartPanel = new ChartPanel(chartSuperior);
		chartPanel.setRangeZoomable(false);
		chartPanel.setDomainZoomable(false);
		chartPanel.setMouseWheelEnabled(false);
		chartPanel.setPopupMenu(null);
		chartPanel.setBackground(Color.WHITE);
		panelGraficoSuperior.add(chartPanel, BorderLayout.CENTER);
		
		
		
	}
	
	public void graficarVariables(double [] variables, Second tiempo){
		
		for(int i=0; i<variables.length;i++){
		
		switch(i){
		
		case 0 :
			variableUno.setText(Double.toString(variables[i]));
			break;
		case 1 :
			variableDos.setText(Double.toString(variables[i]));
			break;
		case 2 :
			variableTres.setText(Double.toString(variables[i]));
			break;
		case 3 :
			variableCuatro.setText(Double.toString(variables[i]));
			break;	
		}
		
		arraySeries[i].add(tiempo,variables[i]);
		
		}
		repaint();
	}
	
	public void setMensaje(String mensaje){
		
		listaEventos.add(mensaje, 0);
	}
	
	public void setEtiquetaLimites(String limiteSuperior, String limiteInferior){
		/*
		lblLimiteSuperior.setText("Limite superior: "+limiteSuperior);
		lblLimiteInferior.setText("Limite inferior: "+limiteInferior);
		*/
		
		/*marca = new IntervalMarker(Double.parseDouble(limiteSuperior),Double.parseDouble(limiteSuperior));
		Color colorMarca = new Color(250,255,255);
		marca.setPaint(colorMarca);
		plot.addRangeMarker(marca, Layer.BACKGROUND);
		*/
		
	}
	
	public void setPantallaAdministrador(){
		
		menuAdministrador.setVisible(true);
		setBotonLogOut();
		
	}
	
	public void setPantallaUsuario(){
		menuAdministrador.setVisible(false);
		setBotonLogOut();
	}
	
	public void setPantallaSupervisor(){
		
		menuAdministrador.setVisible(true);
		mnConfig.setVisible(false);
		mnUsuarios.setVisible(false);
		mnControl.setVisible(false);
		setBotonLogOut();
		
	}
	
	private void setBotonLogOut(){
		
		ImageIcon imagenSalir = new ImageIcon("Imagenes/SALIR.png");
		botonUsuario.setIcon(imagenSalir);
	}
	
	public void setUsuario(boolean estado, String usuario){
		
		this.rutUsuario = usuario;
		this.usuario = estado;
	}
	
	public String getUsuarioLogeado(){
		
		return rutUsuario;
	}
	
}




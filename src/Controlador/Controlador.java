package Controlador;
import Variables.InterfazVariables;
import Vista.VentanaConfiguracion;
import Vista.VentanaControl;
import Vista.VentanaLogin;
import Vista.VentanaMonitoreo;
import Vista.VentanaGestionUsuario;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Generated;
import javax.swing.JOptionPane;

import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;

import com.invertor.modbus.exception.ModbusIOException;
import com.invertor.modbus.serial.SerialPortException;

import BD.BaseDeDatos;
import ComunicacionSerial.Modbus;
import ControlUsuarios.Usuario;
import Procesador.Configuracion;
import Procesador.ProcesadorVariables;

public class Controlador {
	
	private static Configuracion configuracion;
	private static ProcesadorVariables monitorVariables;
	private static Usuario usuario = new Usuario();
	
	private static Modbus modbus;
	private static InterfazVariables simulacion;
	
	private static VentanaMonitoreo ventana;
	private VentanaConfiguracion ventanaConfiguracion;
	private VentanaLogin ventanaLogin;
	private VentanaGestionUsuario ventanaGestionUsuario;
	private VentanaControl ventanaControl;
	
	private double[] arrayVariables;
	
	public Controlador()  {
		
	}
	
	public void inicializacion() throws IOException, SQLException, SerialPortException {
		
		monitorVariables = new ProcesadorVariables();
		configuracion = new Configuracion();
		
		switch(configuracion.getModo()){
		
		case "Simulacion":
			simulacion = new InterfazVariables(configuracion.getNumeroVariables());
			break;
			
		case "Modbus":
			modbus = new Modbus(configuracion.getPuertoSerial());
			break;
			
		}
		
		setVentanaMonitoreo(configuracion.getNumeroVariables());
	
	}
	
	public void iniciarMonitor() throws IOException, SQLException, SerialPortException{
		
		//monitorVariables.iniciarMonitoreo();
		
	}
	
	public void thread() throws IOException{
		
		arrayVariables = new double[configuracion.getNumeroVariables()];
		
		Thread monitor = new Thread(){
					
					public void run(){
						
						while(true){
							
							Date tiempo = new Date();
							try {
								
								for (int i=0; i<configuracion.getNumeroVariables();i++){
									
										arrayVariables[i] = (getVariable(i)*configuracion.getAjusteVariable())+configuracion.getOffsetVariable();
										
								}
								
								graficarVariables(arrayVariables, tiempo);
								
								try {
									
									Thread.sleep((long) (1000*configuracion.getTiempoActualizacion()));
									
									} catch (InterruptedException e) {
									
									e.printStackTrace();
									}
											
							} catch (IOException e) {
								
								e.printStackTrace();
							} catch (NumberFormatException e) {
								
								e.printStackTrace();
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							} catch (ModbusIOException e) {
								
								e.printStackTrace();
							}
						}
					}
				};
				
				monitor.run();
	}
	
	//Parametros de configuracion
	
		public void setPuertoSerial(String puertoSerial) throws IOException{
			
			configuracion.setPuertoSerial(puertoSerial);
			
		}
		
		public String getPuertoSerial() throws IOException{
			
				return configuracion.getPuertoSerial();
				
		}
		
		public void setParametroConfiguracion(String parametro, double valor) throws IOException{
				
				switch(parametro){
				
				case "Tiempo actualizacion":
					configuracion.setTiempoActualizacion(valor);
					break;
				
				case "Factor ajuste":
					configuracion.setAjusteVariable(valor);
					break;
				
				case "Offset":
					configuracion.setOffsetVariable(valor);
					break;
					
				}
			}
		
		public double getParametroConfiguracion(String parametro) throws IOException{
			
			switch(parametro){
			
			case "Tiempo actualizacion":
				return configuracion.getTiempoActualizacion();
			
			case "Factor ajuste":
				return configuracion.getAjusteVariable();
				
			case "Offset":
				return configuracion.getOffsetVariable();
			}
			
			return 0;
		}
		
		public void setParametroVariable( String parametro, int valor) throws IOException{
			
			switch(parametro){
			
				case "Numero variables":
				configuracion.setNumeroVariables(valor);
				
				case "Modulo 1":
				configuracion.setModuloVariable(1,valor);
				
				case "Modulo 2":
				configuracion.setModuloVariable(2,valor);
					
				case "Modulo 3":
				configuracion.setModuloVariable(3,valor);
					
				case "Modulo 4":
				configuracion.setModuloVariable(4,valor);
					
				case "Direccion 1":
				configuracion.setDireccionVariable(1,valor);
					
				case "Direccion 2":
				configuracion.setDireccionVariable(2,valor);
					
				case "Direccion 3":
				configuracion.setDireccionVariable(3,valor);
					
				case "Direccion 4":
				configuracion.setDireccionVariable(4,valor);
				
			}
		}
		
		public int getParametroVariables (String parametro) throws IOException{
			
			switch(parametro){
			
				case "Numero variables":
				return configuracion.getNumeroVariables();
				
				case "Modulo 1":
				return configuracion.getModuloVariable(1);
				
				case "Modulo 2":
					return configuracion.getModuloVariable(2);
					
				case "Modulo 3":
					return configuracion.getModuloVariable(3);
					
				case "Modulo 4":
					return configuracion.getModuloVariable(4);
					
				case "Direccion 1":
					return configuracion.getDireccionVariable(1);
					
				case "Direccion 2":
					return configuracion.getDireccionVariable(2);
					
				case "Direccion 3":
					return configuracion.getDireccionVariable(3);
					
				case "Direccion 4":
					return configuracion.getDireccionVariable(4);
					
			}
			
			return 0;
		}
		
		public void setParametroGrafico(String parametro, int valor) throws IOException{
			
			switch(parametro){
			
			case "Tiempo max":
				configuracion.setTiempoMax(valor);
				break;
				
			case "Rango tiempo":
				configuracion.setRangoTiempo(valor);
				break;
				
			}
		}
		
		public int getParametroGrafico(String parametro) throws IOException{
			
			switch(parametro){
			
			case "Tiempo max":
				return configuracion.getTiempoMax();
				
			case "Rango tiempo":
				return configuracion.getRangoTiempo();
			}
			
			return 0;
		}

	
	//Configuracion de ventanas
	
	public void setVentanaMonitoreo(int numeroVariables) throws IOException, SQLException, SerialPortException{
		
		ventana = new VentanaMonitoreo(numeroVariables);
		ventana.setVisible(true);
		
	}
	
	public void setVentanaLogin() throws SQLException, IOException, SerialPortException{
		
		ventanaLogin = new VentanaLogin();
		ventanaLogin.setVisible(true);
		
	}
	
	public void setVentanaConfig() throws IOException, SQLException, SerialPortException{
		
		ventanaConfiguracion = new VentanaConfiguracion();
		ventanaConfiguracion.setVisible(true);
	}
	
	public void setVentanaGestionUsuario() throws SQLException, IOException, SerialPortException{
		
		ventanaGestionUsuario = new VentanaGestionUsuario();
		ventanaGestionUsuario.setVisible(true);
	}
	
	public void setVentanaControl(){
		
		ventanaControl = new VentanaControl();
		ventanaControl.setVisible(true);
	}
	
	//Metodo para graficar variables
	
	public void graficarVariables(double[] variable, Date tiempo){
		
		ventana.graficarVariables(variable, new Second(tiempo.getSeconds(),tiempo.getMinutes(),
				tiempo.getHours(),tiempo.getDay()+1,tiempo.getMonth()+1,tiempo.getYear()+1900));
		
	}
	
	public void setMensaje(String mensaje){
			
			ventana.setMensaje(mensaje);
	}
	
	// Metodos para la gestion de usuarios
	
	public String validarUsuario(String rutUsuario, String contraseña) throws SQLException{
			
			switch(usuario.validarUsuario(rutUsuario, contraseña)){
			
			case "Administrador":
				ventana.setUsuario(true, rutUsuario);
				ventana.setPantallaAdministrador();
				return "Validado como Administrador";
			
			case "Usuario":
				ventana.setUsuario(true, rutUsuario);
				ventana.setPantallaUsuario();
				return "Validado como Usuario";
			
			case "Supervisor":
				ventana.setUsuario(true, rutUsuario);
				ventana.setPantallaSupervisor();
				return "Validado como Supervisor";
				
			
			case "Usuario no existe":
				return "Usuario no existe";
			
			case "Usuario inhabilitado":
				return "Usuario inhabilitado";
			}
			return null;

	}
	
	public String getUsuarioLogeado(){
		
		return ventana.getUsuarioLogeado();
	}
	
	public String getNombreUsuario(String rutUsuario) throws SQLException{
		
		return usuario.getNombreUsuario(rutUsuario);
		
	}
	
	public void guardarUsuario(Usuario usuario) throws SQLException{
		
		usuario.guardarUsuario();
		
	}
	
	//Gestion de usuarios: Registro de eventos Ingreso / Salida
	
	
	public void registrarIngreso(String rutUsuario){
		
		usuario.guardarEvento("Ingreso de usuario", rutUsuario);
		
	}
	
	public void registrarSalida(String rutUsuario){
		
		usuario.guardarEvento("Salida de usuario", rutUsuario);
		
	}
	
	// Comunicacion Modbus
	
	public void setRegistro(int slaveId, int direccion, int valor){
		
		modbus.setRegistro(slaveId, direccion, valor);
	}
	
	public int getRegistro (int slaveId, int direccion) throws ModbusIOException{
		
		return modbus.getRegistro(slaveId, direccion, 1);
	}
	
	//Origen de datos
	
	public double getVariable(int numeroVariable) throws NumberFormatException, IOException, InterruptedException, ModbusIOException{
		
		switch(configuracion.getModo()){
		
		case "Simulacion":
			return simulacion.getVariable(numeroVariable);
			
		case "Modbus":
			return modbus.getRegistro(configuracion.getModuloVariable(numeroVariable+1), configuracion.getDireccionVariable(numeroVariable+1), 1);
		
		}
		
		return 0;
		
	}
	
	
	}

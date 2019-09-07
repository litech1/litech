package Procesador;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;

import com.invertor.modbus.exception.ModbusIOException;
import com.invertor.modbus.serial.SerialPortException;

import BD.BaseDeDatos;
import Controlador.Controlador;
import Controlador.Main;
import Notificaciones.Notificacion;
import Variables.InterfazVariables;

import java.util.Calendar;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ProcesadorVariables {
	
	private double limiteSuperior;
	private double limiteInferior;
	private double tiempoActualizacion;
	private double [] arrayVariables;
	private double ajusteVariable = 0.1;
	private double offsetVariable = 0;
	private int numeroVariables;
	private String stringFecha;
	private String mensaje;
	
	private Configuracion configuracion;
	private Controlador controlador;
	
	static private BaseDeDatos conexionBD = new BaseDeDatos();
	private String variablesSQL;
	
	public ProcesadorVariables() throws SQLException, IOException{
		
		configuracion = new Configuracion();
		this.numeroVariables = configuracion.getNumeroVariables();
		this.tiempoActualizacion = configuracion.getTiempoActualizacion();
		setLimites();
		arrayVariables = new double[numeroVariables];
		
	}	
	
	// Thread para monitorear variables
	
	public void iniciarMonitoreo() throws IOException, SQLException, SerialPortException{
		
		controlador = new Controlador();
		controlador.setVentanaMonitoreo(numeroVariables);
		
		Thread monitorear = new Thread(){
			
			public void run(){
				
				while(true){
					
					Date fecha = new Date();
					SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					stringFecha = formatoFecha.format(fecha);
					
					for(int i=0;i<numeroVariables;i++){
						
						try {
							
							arrayVariables[i] = (controlador.getVariable(i+1)*ajusteVariable)+offsetVariable;
							/*mensaje = analizarVariable(arrayVariables[i], i+1);
							if(mensaje!="OK"){
								controlador.setMensaje(mensaje+" - "+stringFecha);
							}*/
							
						
								} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
								} catch (ModbusIOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
					
					controlador.graficarVariables(arrayVariables, fecha);
					guardarVariables(arrayVariables, stringFecha);
					
					try {
						
						Thread.sleep((long) (1000*tiempoActualizacion));
						
						} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						}
					
					}
			}
		};
		
		// Inicia el monitoreo
		
		monitorear.run();
	}
	
	// Guardar variables en Base de Datos
	
	private void guardarVariables(double[] arrayVariables, String fecha){
		
		
		conexionBD.setQuery("insert into variables (Fecha, "+getTotalVariables(arrayVariables)+", idControlador)"
				+ " values('"+stringFecha+"','"+prepararVariables(arrayVariables)+"','00001')");
		
	}
	
	// Metodos para preparar consulta SQL
	private String prepararVariables( double [] arrayVariables){
		
		String string0 = null;
		variablesSQL = null;
		
		for (int i=0; i<arrayVariables.length; i++){
			
			string0 = Double.toString(arrayVariables[i]);
			if(i==0){
				variablesSQL =string0;
			}
			else{
			variablesSQL=string0+"','"+variablesSQL;
			}
		}
		
		return variablesSQL;
	
	}
	
	private String getTotalVariables(double [] arrayVariables){
		
		switch(arrayVariables.length){
		
		case 1 :
			return "Entrada_1";
			
		
		case 2:
			return "Entrada_2, Entrada_1";
			
		
		case 3:
			return "Entrada_3, Entrada_2, Entrada_1";
			
			
		case 4:
			return "Entrada_4, Entrada_3, Entrada_2, Entrada_1";
			
		}
	
		return "";
	}
	
	
	// Metodo para analizar variable
	
	private String analizarVariable(double variable, int numeroVariable) throws IOException {
		
		setLimites();
		
		if (variable<limiteInferior){
			
			guardarEvento("Alarma variable "+numeroVariable+" baja", controlador.getUsuarioLogeado());
			return "Alarma limite inferior - Variable: "+numeroVariable;
		}
			
		if (variable>limiteSuperior){
			
			guardarEvento("Alarma variable "+numeroVariable+" alta", controlador.getUsuarioLogeado());
			return "Alarma limite superior - Variable: "+numeroVariable;
		}	
		
		return "OK";
		
		}
	
	// Configuracion de parametros del archivo conf.properties
	
	
	public void setLimites() throws IOException{
		
		this.limiteInferior = configuracion.getLimite("Inferior");
		this.limiteSuperior = configuracion.getLimite("Superior");
		
	}
	
	public double getLimite(String limite) throws IOException{
		
		switch(limite){
			
			case "Superior":
				return limiteSuperior;
				
			case "Inferior":
				return limiteInferior;
		}
		
		return 0;
	}
	
	
	public void setLimites(double limiteSuperior, double limiteInferior) throws IOException{
		
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
		configuracion.setLimites(limiteSuperior, limiteInferior);
		
	}
	
	public double getTiempoActualizacion(){
		
		return tiempoActualizacion;
	}
	
	public void setTiempoActualizacion(int tiempoActualizacion) throws IOException{
		
		this.tiempoActualizacion = tiempoActualizacion;
		configuracion.setTiempoActualizacion(tiempoActualizacion);
	}
	
	public int getNumeroVariables(){
		
		return numeroVariables;
	}
	
	public void setNumeroVariables(int numeroVariables) throws IOException{
		
		this.numeroVariables = numeroVariables;
		configuracion.setNumeroVariables(numeroVariables);
	}
	
	public double getAjusteVariable(){
		
		return ajusteVariable;
	}
	
	public void setAjusteVariable(double justeVariable){
		
		
	}
	
	public double getOffsetVariable(){
		
		return offsetVariable;
	}
	
	public void setOffsetVariable(double offsetVariable){
		
		
	}
	
	public void guardarEvento(String evento, String rutUsuario){
		
		Date date = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String fecha = formatoFecha.format(date);
		
		conexionBD.setQuery("insert into eventos (Evento,Fecha,Usuario) "
				+ "values ('"+evento+"','"+fecha+"','"+rutUsuario+"')");
		
	}
			
}
	
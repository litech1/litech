package Procesador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
	
	private Properties propiedades;
	private FileOutputStream salida;
	private FileInputStream entrada;

	public Configuracion() throws IOException {
		
		propiedades = new Properties();
		
	}
	
	public void setLimites(double limiteSuperior, double limiteInferior) throws IOException{
		
		getLimite("Superior");
		propiedades.setProperty("Limite_superior", Double.toString(limiteSuperior));
		propiedades.setProperty("Limite_inferior", Double.toString(limiteInferior));
		
		salida = new FileOutputStream("src/config.properties");
		propiedades.store(salida, "Actualización");
		
	}
	
	public double getLimite(String limite) throws IOException{
			
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			switch(limite){
			
			case "Superior":
			
				return  Double.parseDouble(propiedades.getProperty("Limite_superior"));
						
				
			case "Inferior":
				
				return Double.parseDouble(propiedades.getProperty("Limite_inferior"));
				
				}
			
			return 0;
			}
	
	public void setNumeroVariables (int numeroVariables) throws IOException{
		
		getNumeroVariables();
		propiedades.setProperty("Numero_variables", Integer.toString(numeroVariables));
		
		salida = new FileOutputStream("src/config.properties");
		propiedades.store(salida, "Actualización");
		salida.close();
	}
	
	public int getNumeroVariables() throws IOException{
		
		entrada = new FileInputStream ("src/config.properties");
		propiedades.load(entrada);
		
		return Integer.parseInt(propiedades.getProperty("Numero_variables"));
	}
	
	public void setTiempoActualizacion(double tiempoActualizacion) throws IOException{
		
		getTiempoActualizacion();
		propiedades.setProperty("Tiempo_actualizacion", Double.toString(tiempoActualizacion));
		
		salida = new FileOutputStream("src/config.properties");
		propiedades.store(salida, "Actualización");
		
	}
	
	public double getTiempoActualizacion() throws IOException{
			
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return Double.parseDouble(propiedades.getProperty("Tiempo_actualizacion"));
		}
	
	public void setAjusteVariable(double ajusteVariable) throws IOException{
		
		getAjusteVariable();
		propiedades.setProperty("Ajuste_variable", Double.toString(ajusteVariable));
		
		salida = new FileOutputStream("src/config.properties");
		propiedades.store(salida, "Actualización");
	}
	
	public double getAjusteVariable() throws IOException{
			
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return Double.parseDouble(propiedades.getProperty("Ajuste_variable"));
		}
	
	public void setOffsetVariable(double offsetVariable) throws IOException{
		
		getOffsetVariable();
		propiedades.setProperty("Offset_variable", Double.toString(offsetVariable));
		
		salida = new FileOutputStream("src/config.properties");
		propiedades.store(salida, "Actualización");
	}
	
	public double getOffsetVariable() throws IOException{
			
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return Double.parseDouble(propiedades.getProperty("Offset_variable"));
		}
	
	public void setDireccionVariable(int numeroVariable, int direccion) throws IOException{
		
		getDireccionVariable(numeroVariable);
		switch(numeroVariable){
		
		case 1:
		
			propiedades.setProperty("Direccion_variable_1", Integer.toString(direccion));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 2:
			
			propiedades.setProperty("Direccion_variable_2", Integer.toString(direccion));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 3:
			
			propiedades.setProperty("Direccion_variable_3", Integer.toString(direccion));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 4:
			
			propiedades.setProperty("Direccion_variable_4", Integer.toString(direccion));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
		}
		
	}
	
	public int getDireccionVariable(int numeroVariable) throws IOException{
			
			switch(numeroVariable){
					
					case 1:
						entrada = new FileInputStream ("src/config.properties");
						propiedades.load(entrada);
						return Integer.parseInt(propiedades.getProperty("Direccion_variable_1"));
						
						
					case 2:
						entrada = new FileInputStream ("src/config.properties");
						propiedades.load(entrada);
						return Integer.parseInt(propiedades.getProperty("Direccion_variable_2"));
						
						
					case 3:
						entrada = new FileInputStream ("src/config.properties");
						propiedades.load(entrada);
						return Integer.parseInt(propiedades.getProperty("Direccion_variable_3"));
						
					case 4:
						entrada = new FileInputStream ("src/config.properties");
						propiedades.load(entrada);
						return Integer.parseInt(propiedades.getProperty("Direccion_variable_4"));
					}
					
					return 0;
				}
	
	public void setModuloVariable(int numeroVariable, int idModulo) throws IOException{
		
		getModuloVariable(numeroVariable);
		switch(numeroVariable){
		
		case 1:
			propiedades.setProperty("Modulo_variable_1", Integer.toString(idModulo));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 2:
			propiedades.setProperty("Modulo_variable_2", Integer.toString(idModulo));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 3:
			propiedades.setProperty("Modulo_variable_3", Integer.toString(idModulo));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
			
		case 4:
			propiedades.setProperty("Modulo_variable_4", Integer.toString(idModulo));
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
			break;
		}
	}
	
	public int getModuloVariable(int numeroVariable) throws IOException{
		
		switch(numeroVariable){
		
		case 1:
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			return Integer.parseInt(propiedades.getProperty("Modulo_variable_1"));
			
			
		case 2:
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			return Integer.parseInt(propiedades.getProperty("Modulo_variable_2"));
			
			
		case 3:
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			return Integer.parseInt(propiedades.getProperty("Modulo_variable_3"));
			
		case 4:
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			return Integer.parseInt(propiedades.getProperty("Modulo_variable_4"));
		}
		
		return 0;
	}
	
	public void setPuertoSerial(String puertoSerial) throws IOException{
		
			getPuertoSerial();
			propiedades.setProperty("Puerto_serial", puertoSerial);
			
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
	}
	
	public String getPuertoSerial() throws IOException{
		
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return propiedades.getProperty("Puerto_serial");
	}
	
	public void setModo(String modo) throws IOException{
		
			getModo();
			propiedades.setProperty("Modo", modo);
			
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
	}
	
	public String getModo() throws IOException{
		
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return propiedades.getProperty("Modo");
	}
	
	public void setRangoTiempo(int rangoTiempo) throws IOException{
		
			getRangoTiempo();
			propiedades.setProperty("Rango_tiempo", Integer.toString(rangoTiempo));
			
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
	}
	
	public int getRangoTiempo() throws IOException{
		
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return Integer.parseInt(propiedades.getProperty("Rango_tiempo"));
	}
	
	public void setTiempoMax(int tiempoMax) throws IOException{
		
			getTiempoMax();
			propiedades.setProperty("Tiempo_max", Integer.toString(tiempoMax));
			
			salida = new FileOutputStream("src/config.properties");
			propiedades.store(salida, "Actualización");
	}
	
	public int getTiempoMax() throws IOException{
			
			entrada = new FileInputStream ("src/config.properties");
			propiedades.load(entrada);
			
			return Integer.parseInt(propiedades.getProperty("Tiempo_max"));
	}
}



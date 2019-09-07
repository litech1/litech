package ComunicacionSerial;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class ComunicacionSerial {

	Enumeration<?> listaPuertos;
	OutputStream streamSalida;
	InputStream streamEntrada;
	CommPortIdentifier portId;
	SerialPort serialport;
	int control;
	String puertoCom;
	String lectura;
	
public ComunicacionSerial(String puertoCom){
		
		
		this.puertoCom=puertoCom;
		listaPuertos = CommPortIdentifier.getPortIdentifiers();
		
		while (listaPuertos.hasMoreElements()) {
			
			 portId = (CommPortIdentifier) listaPuertos.nextElement(); 
			 /*System.out.println(portId.getName());*/ 
			 if (portId.getName().equalsIgnoreCase(puertoCom)) {
			 try {
			 serialport= (SerialPort)portId.open("ComunicacionSerial", 500);
			 System.out.println("Conectado a puerto "+puertoCom);
			 
			 } 
			 catch (Exception e) {
				 
			 }
			 }
			}
		
	}
	
public void escribirSerial(String mensajeSalida){
	
	 try{
		 streamSalida=serialport.getOutputStream(); 
		 streamSalida.write (mensajeSalida.getBytes());
		 System.out.println("Se ha enviado "+mensajeSalida);
		 } catch (Exception e) {
		 }
	}

public void escribir(int mensaje) throws IOException{
	
	try{
		OutputStream salida= serialport.getOutputStream();
		salida.write(mensaje);
	} catch(Exception e){
		
	}
}

public String leerSerial() throws IOException, InterruptedException{
	
	streamEntrada = serialport.getInputStream();
	try {
		 BufferedReader st = new BufferedReader(new InputStreamReader(streamEntrada));
		 if ((lectura = st.readLine())!=null){ 
		 //System.out.println("Se ha recibido "+lectura);
		 
		 }
		 else{
			 System.out.println("Sin respuesta");
		 }
		 } catch (Exception e) {
		 
		 }
	Thread.sleep(100);
	return lectura;
	}

public void cerrar() throws IOException{
	serialport.close();
	
	}

public void cerrarSalida() throws IOException{
	streamSalida.close();
	
	}

public void cerrarEntrada() throws IOException{
	streamEntrada.close();
	}
}

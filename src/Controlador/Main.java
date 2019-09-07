package Controlador;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import com.invertor.modbus.serial.SerialPortException;

import BD.BaseDeDatos;
import Procesador.ProcesadorVariables;

public class Main {

	
	
 public static void main(String[] args) throws SQLException, IOException, SerialPortException {
 
	 
	 Controlador controlador = new Controlador();
	 controlador.inicializacion();
	 controlador.thread();
	 
 	}

}
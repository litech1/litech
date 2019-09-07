package Variables;

import java.io.IOException;

import ComunicacionSerial.ComunicacionSerial;


public class InterfazVariables {
	
	Simulador simulador;
	ComunicacionSerial conexion;
	public InterfazVariables(int numeroVariables){

		simulador = new Simulador(numeroVariables);
		//conexion = new ComunicacionSerial("COM6");
	}
	
	public double getVariable(int posicionVariable) throws NumberFormatException, IOException, InterruptedException{
		
		return getVariableSimulada(posicionVariable);
		//return Double.parseDouble(conexion.leerSerial())/100;
	}
	
	public int getCantidadVariables(){
		
		return simulador.getCantidadVariables();
	}
	
	public double getVariableSimulada( int posicionVariable){
		
		return simulador.getVariable(posicionVariable);
	}
	
	public int getVariableSerial() throws NumberFormatException, IOException, InterruptedException{
		
		ComunicacionSerial conexion = new ComunicacionSerial("COM6");
		return Integer.parseInt(conexion.leerSerial());
		
	}

}

package Variables;
import java.util.Random;

public class Simulador {

	static int variable;
	static int cantidadVariables;
	static double[] arrayVariables = new double[4];
	static Random random = new Random();
	
	public Simulador(int cantidadVariables){
		
		this.cantidadVariables = cantidadVariables;
		arrayVariables = new double[cantidadVariables];
		
	}
	
	public static double getVariable(int numeroVariable){
		
		
		arrayVariables[numeroVariable] = random.nextDouble()*10;
		return arrayVariables[numeroVariable];
		
	}
	
	public int getCantidadVariables(){
		
		return cantidadVariables;
	}
	
}

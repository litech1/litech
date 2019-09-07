package Notificaciones;

import java.sql.ResultSet;
import java.sql.SQLException;

import BD.BaseDeDatos;

public class Notificacion {
	
	private String mensajeAlarma;
	private BaseDeDatos conexionBD = new BaseDeDatos();
	
	public Notificacion(String mensaje, String tipo) throws SQLException{
		
		ResultSet resultado = conexionBD.getQuery("select * from usuarios where notificar='si'");
		if(resultado.next()){
			
			resultado.last();
			
			String [] responsables = new String [resultado.getRow()];
			resultado.first();
			
			for(int i=0; i<responsables.length; i++){
				
				
				responsables[i] = resultado.getString(4);
				System.out.println(responsables[i]);
				resultado.next();
			}
			
			for(int i=0; i<responsables.length; i++){
				Email email = new Email();
				//email.enviarEmail(tipo, mensaje,responsables[i]);
				
			}
		}
	}
	
}


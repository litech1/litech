package BD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {

	public Prueba() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		BaseDeDatos conexion = new BaseDeDatos();
		ResultSet resultado = conexion.getQuery("select * from usuarios where rut='admin' and contraseña='1234'");
		resultado.next();
		System.out.println(resultado.getString(6));
	}

}

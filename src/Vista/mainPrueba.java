package Vista;

import java.io.IOException;
import java.sql.SQLException;

import com.invertor.modbus.serial.SerialPortException;

public class mainPrueba {

	public mainPrueba() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException, IOException, SerialPortException {
		// TODO Auto-generated method stub
		
		VentanaConfiguracion conf = new VentanaConfiguracion();
		conf.setVisible(true);

	}

}

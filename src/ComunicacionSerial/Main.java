package ComunicacionSerial;

import java.io.IOException;
import java.util.Scanner;

import com.invertor.modbus.serial.SerialPortException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SerialPortException  {
		
		
	Modbus mod = new Modbus("COM2");
	mod.setRegistro(1, 0, 12);

}
}
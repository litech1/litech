package ComunicacionSerial;
import com.invertor.modbus.ModbusMaster;
import com.invertor.modbus.ModbusMasterFactory;
import com.invertor.modbus.exception.ModbusIOException;
import com.invertor.modbus.exception.ModbusNumberException;
import com.invertor.modbus.exception.ModbusProtocolException;
import com.invertor.modbus.serial.SerialParameters;
import com.invertor.modbus.serial.SerialPort;
import com.invertor.modbus.serial.SerialPortException;


public class Modbus {
	
	private SerialParameters parametros;
	private ModbusMaster master;
	private String puertoSerial ;
	private int slaveId;
	private int direccion;
	private int cantidad;
	
	
public Modbus(String puertoSerial) {
	
	this.puertoSerial = puertoSerial;
	
	try{
	parametros = new SerialParameters();
	parametros.setDevice(this.puertoSerial);
	parametros.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
	parametros.setDataBits(8);
	parametros.setParity(SerialPort.Parity.NONE);
	parametros.setStopBits(1);
	
	master = ModbusMasterFactory.createModbusMasterRTU(parametros);
	master.connect();
	
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
		}
}

public int getRegistro(int slaveId, int direccion, int cantidad) throws ModbusIOException{
	
	this.slaveId = slaveId;
	this.direccion = direccion;
	this.cantidad = cantidad;
	
	try{
		
		int[] registros = master.readHoldingRegisters(slaveId, direccion, cantidad);
		for(int value : registros){
			//System.out.println("Dirección: "+ offset++ +" , Valor: "+value);
			return value;
		}
		
		
		
		}catch(RuntimeException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			
			try{
				master.disconnect();
				master.connect();
			}catch(ModbusIOException e1){
				e1.printStackTrace();
				master.disconnect();
			}
		}
		return 0;
}

public void setRegistro(int slaveId, int direccion, int valor){
	
	int [] arrayValores = new int[1];
	
	arrayValores[0] = valor;
		
		try {
			
			master.writeMultipleRegisters(slaveId, direccion, arrayValores);
			
		} catch (ModbusProtocolException | ModbusNumberException | ModbusIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

}



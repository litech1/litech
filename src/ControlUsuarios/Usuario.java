package ControlUsuarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import BD.BaseDeDatos;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String cargo;
	private String rut;
	private String contraseña;
	private String email;
	private String tipo;
	private boolean estado;
	private boolean notificar;
	
	static private BaseDeDatos conexionBD = new BaseDeDatos();
	
	public Usuario(){
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public String getRut() {
		return rut;
	}


	public void setRut(String rut) {
		this.rut = rut;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public boolean isEnabled() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public boolean isNotify(){
		return notificar;
	}
	
	public void setNotificado(boolean notificar){
		this.notificar = notificar;
	}
	
	public void guardarUsuario() throws SQLException{
		
		String estado;
		
		if (this.estado){
			estado = "1";
		}
		else{
			estado = "0";
		}
		
		conexionBD.setQuery("insert into usuarios (rut,nombre,apellido,email,contraseña,cargo,tipo,estado,notificar)"
				+ "values ('"+this.rut+"','"+this.nombre+"','"+this.apellido+"','"+this.email+""
						+ "','"+this.contraseña+"','"+this.cargo+"','"+this.tipo+"','"+estado +"')");
		
		guardarEvento("Usuario creado", rut);
	
	}
	

	public String validarUsuario(String rutUsuario, String contraseña) throws SQLException{
		
		ResultSet resultado = conexionBD.getQuery("select * from usuarios where rut='"+rutUsuario+"' and contraseña='"+contraseña+"'");
		
		if(resultado.next()){
			if(resultado.getBoolean(8)){
			switch(resultado.getString(7)){
			
			case "Administrador":
				return "Administrador";
			
			case "Usuario":
				return "Usuario";
				
			case "Supervisor":
				return "Supervisor";
				}
			}
			else{
				
				return "Usuario inhabilitado";
			}
			}
			
			return "Usuario no existe";
	}
	
	public String getNombreUsuario(String rutUsuario) throws SQLException{
		
		String nombreCompleto="";
		
		ResultSet resultado = conexionBD.getQuery("select * from usuarios where rut='"+rutUsuario+"'");
		if (resultado.next()){
			
			nombreCompleto = resultado.getString(2)+" "+resultado.getString(3);
		}
		
		return nombreCompleto;
	}
	
	public void buscarUsuario(String rutUsuario) throws SQLException{
		
		ResultSet resultado = conexionBD.getQuery("select * from usuarios where rut='"+rutUsuario+"'");
		if(resultado.next()){
			this.nombre=resultado.getString(2);
			this.apellido=resultado.getString(3);
			this.email=resultado.getString(4);
			this.cargo=resultado.getString(6);
			this.tipo=resultado.getString(7);
			this.estado=resultado.getBoolean(8);
			this.notificar=resultado.getBoolean(9);
		}
		
	}
	
	public boolean isUsuario(String rutUsuario) throws SQLException{
		
		ResultSet resultado = conexionBD.getQuery("select * from usuarios where rut='"+rutUsuario+"'");
		if(resultado.next()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void guardarEvento(String evento, String rutUsuario){
		
		Date date = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String fecha = formatoFecha.format(date);
		
		conexionBD.setQuery("insert into eventos (Evento,Fecha,Usuario) "
				+ "values ('"+evento+"','"+fecha+"','"+rutUsuario+"')");
		
	}
}

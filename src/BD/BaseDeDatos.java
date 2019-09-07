package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDatos {

 static private mySql mysql;
 
 public BaseDeDatos() {
  
	 conexion();
  
 	}
 
 public void conexion(){
	 
	 mysql = new mySql();
	 
 }
 
 public Connection getConnection(){
     return mySql.conn;
 	}
 
 public ResultSet getQuery(String _query){
	 
	 return mysql.getQueryMysql(_query);
 	}
 
 public void setQuery(String _query){

	 mysql.setQueryMysql(_query);
 	}
}
package BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySql {

 static private String _usuario="root";
 static private String _pwd= "root";
 static private String _bd="monitoreo";
 static String _url = "jdbc:mysql://localhost/"+_bd;
 static Connection conn = null;
 
 public mySql() {
  
   try{
     Class.forName("com.mysql.jdbc.Connection");
     conn = (Connection)DriverManager.getConnection(_url, _usuario, _pwd);
     if(conn != null)
     {
       System.out.println("Conexion a base de datos "+_url+" . . . Ok");
     }
   }
   catch(SQLException e)
   {
      System.out.println("Hubo un problema al intentar conecarse a la base de datos"+_url);
   }
   catch(ClassNotFoundException e)
   {
      System.out.println(e);
   }  
   catch (Exception e){
	   
	   System.out.println(e);
   }
 }
 
 public Connection getConnectionMysql(){
     return conn;
 }
 
 public ResultSet getQueryMysql(String _query)
 {
    Statement state = null;
    ResultSet resultado = null;
    try{
      state = (Statement) conn.createStatement();
      resultado = state.executeQuery(_query);
    }
    catch(SQLException e)
    {
      e.printStackTrace();
    }
    return resultado;
 }
 
 public void setQueryMysql(String _query){

    Statement state = null;
  
    try{   
      state=(Statement) conn.createStatement();
      state.execute(_query);

    }catch (SQLException e){
      e.printStackTrace();
    }
 }
}

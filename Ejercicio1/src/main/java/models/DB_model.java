package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.MysqlConnection;

public class DB_model {

	

	private static Connection conexion;
	public static  String cadenaConexion;
	//"jdbc:mysql://192.168.1.190:3306","remote","Saraysofia2010*"
	
	public static void main(String[] args) {
		
		startConnection();
	}
	
	public DB_model(String cadenaConexion) {
		this.cadenaConexion = cadenaConexion;
	}
	
	public static  void startConnection() {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(cadenaConexion);
			System.out.println("serverConnected");
			
			
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println(e);
		}
	}
	
	
	public void closeConnection() {
		try {
			conexion.close();
			System.out.println("Conexion cerrada");
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	

	public void useDB(String db_nombre) {
		String query;
		Statement st;
		
		try {
			st = conexion.createStatement();

			query = "USE " + db_nombre + ";";
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			//System.err.println(e);
			Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, e);
	
		}
	}

	public void insertData(String[] data, String table) {
		try {
			
			String query;
			Statement st = conexion.createStatement();
			
			query = "INSERT INTO "+ table +  " (nombre,apellido,direccion,dni) VALUES " + data[0]+data[1]+data[2]+data[3];
			
			st.executeUpdate(query);
			System.out.println("Datos introducidos en la tabla " + table +  ": correctamente");
			
			
		} catch (SQLException e) {
			Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, e);
		}
		
		
	}
	
	
	public ResultSet getData(String nombreTabla, String[] campos, String selectValue , String whereConsult) {
		
		try {
			
		if(selectValue ==  null)
			selectValue = "*";
		
		String query = "select " + selectValue + " from " + nombreTabla + " where " + whereConsult;
		Statement st = conexion.createStatement();

		ResultSet resultSet;
		
		resultSet = st.executeQuery(query);
		
		
		return resultSet;
		
		}catch (SQLException e) {
			Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}

		
	}
	
	public void deleteData(String campo, String value, String table) {
		
		try {
			String query = "Delete FROM " + table  + " where " + campo + " = " + value;
			Statement st = conexion.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	public void updateData(String table, String campo, String value,  String[] col, String[]newData ) {
		
		try {
			String query = "Update FROM " + table  +
					"set "  + col[0] + "=" + newData[0] +", " +
					col[1] + "=" + newData[1] +", " +
					col[2] + "=" + newData[2] +", " +
					col[3] + "=" + newData[3] +", " +
					col[4] + "=" + newData[4] +", " 
					+ " where " + campo + " = " + value;
			Statement st = conexion.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			Logger.getLogger(MysqlConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	
	
}

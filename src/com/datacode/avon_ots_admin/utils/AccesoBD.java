package com.datacode.avon_ots_admin.utils;

/**
 * Clase de m�todos de acceso a la BD
 * @author jorge.torner
 * @since 20-12-2011
 */
import java.sql.*;
import java.util.Properties;

public class AccesoBD {
	private static String archivoConfig = "AvonAdminApp.properties";
	
	/**
	 * Abre una conexi�n a la base de datos de OTS
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @return Connection Una conexi�n abierta
	 */
	static public Connection abrirConexionOTS() {
		Connection conexion = null;

		//Cargamos el archivo de propiedades para obtener datos de conexi�n y formar cadena
	
			String servidor = Utils.obtenerPropiedadArchivoConfig("servidor_OTS"); 
//			String puerto = accesoArch.ObtenerPropiedad("puerto_OTS");
			String bd = Utils.obtenerPropiedadArchivoConfig("bd_OTS");
			String usr = Utils.obtenerPropiedadArchivoConfig("usr_OTS");
			String pwd = Utils.obtenerPropiedadArchivoConfig("pwd_OTS");
			
			Properties info = new Properties();
			info.setProperty("user", usr);
			info.setProperty("password", pwd);
			//info.setProperty("language", "Spanish");

			//String cadenaCon = "jdbc:sqlserver://" + servidor + ";databaseName=" + bd + ";language=Spanish";
			String cadenaCon = "jdbc:sqlserver://" + servidor + ";databaseName=" + bd;
//			String cadenaCon = "jdbc:sqlserver://" + servidor + ":" + puerto + ";databaseName=" + bd;
			try {
				// Get connection
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//connection = DriverManager.getConnection("jdbc:sqlserver://qro-jat\\sqlexpress/ControlCotizaciones:1433", "sa", "datacode");
				//conexion = DriverManager.getConnection(cadenaCon, usr, pwd);
				conexion = DriverManager.getConnection(cadenaCon, info);

//				            if (conexion != null) {
//				            System.out.println("Successfully connected");
//				            // Meta data
//				            DatabaseMetaData meta = conexion.getMetaData();
//				            System.out.println("\nDriver Information");
//				            System.out.println("Driver Name: " + meta.getDriverName());
//				            System.out.println("Driver Version: " + meta.getDriverVersion());
//				            System.out.println("nDatabase Information ");
//				            System.out.println("Database Name: " + meta.getDatabaseProductName());
//				            System.out.println("Database Version: " + meta.getDatabaseProductVersion());
//				            }
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				InteractuarArchivos.guardaArchivoLog("Error al establecer conexi�n a la base de datos: " + ex.toString());
			} catch (SQLException ex) {
				ex.printStackTrace();
				InteractuarArchivos.guardaArchivoLog("Error al establecer conexi�n a la base de datos: " + ex.toString());
			}
        
        return conexion;
    }
	
	/**
	 * Abre una conexi�n a la base de datos de SOS
	 * @author dona.ugalde
	 * @since Enero-19-2016
	 * @return Connection Una conexion abierta
	 */
	static public Connection abrirConexionSOS() {
		Connection conexion = null;

		//Cargamos el archivo de propiedades para obtener datos de conexi�n y formar cadena
	
			String servidor = Utils.obtenerPropiedadArchivoConfig("servidor_SOS"); 
//			String puerto = accesoArch.ObtenerPropiedad("puerto_OTS");
			String bd = Utils.obtenerPropiedadArchivoConfig("bd_SOS");
			String usr = Utils.obtenerPropiedadArchivoConfig("usr_SOS");
			String pwd = Utils.obtenerPropiedadArchivoConfig("pwd_SOS");
			
			Properties info = new Properties();
			info.setProperty("user", usr);
			info.setProperty("password", pwd);
			//info.setProperty("language", "Spanish");

			//String cadenaCon = "jdbc:sqlserver://" + servidor + ";databaseName=" + bd + ";language=Spanish";
			String cadenaCon = "jdbc:sqlserver://" + servidor + ";databaseName=" + bd;
//			String cadenaCon = "jdbc:sqlserver://" + servidor + ":" + puerto + ";databaseName=" + bd;
			try {
				// Get connection
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//connection = DriverManager.getConnection("jdbc:sqlserver://qro-jat\\sqlexpress/ControlCotizaciones:1433", "sa", "datacode");
				//conexion = DriverManager.getConnection(cadenaCon, usr, pwd);
				conexion = DriverManager.getConnection(cadenaCon, info);

//				            if (conexion != null) {
//				            System.out.println("Successfully connected");
//				            // Meta data
//				            DatabaseMetaData meta = conexion.getMetaData();
//				            System.out.println("\nDriver Information");
//				            System.out.println("Driver Name: " + meta.getDriverName());
//				            System.out.println("Driver Version: " + meta.getDriverVersion());
//				            System.out.println("nDatabase Information ");
//				            System.out.println("Database Name: " + meta.getDatabaseProductName());
//				            System.out.println("Database Version: " + meta.getDatabaseProductVersion());
//				            }
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				InteractuarArchivos.guardaArchivoLog("Error al establecer conexi�n a la base de datos: " + ex.toString());
			} catch (SQLException ex) {
				ex.printStackTrace();
				InteractuarArchivos.guardaArchivoLog("Error al establecer conexi�n a la base de datos: " + ex.toString());
			}
        
        return conexion;
    }
	
	/**
	 * Cierra la conexi�n
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param con
	 * @return
	 */
	static public Connection cerrarConexion(Connection con) {
		try {
			if(con != null && !con.isClosed())
			{
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * Cierra un CallableStatement.
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param cs CallableStatement
	 * @return CallableStatement Regresa el CallableStatement cerrado.
	 */
	static public CallableStatement cerrarStatement(CallableStatement cs) {
		try {
			if(cs != null)
			{
				cs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cs;
	}

	/**
	 * M�todo para obtener el primer resultset de un Stored Procedure ignorando los Update, Insert o Delete que haya antes de la consulta final del SP.
	 * Se debe tomar en cuenta que el SP devuelva s�lo una consulta y sea la que se quiere obtener en c�digo, pues de haber m�s, se obtendr�a la primera que genera el SP en vez de la �ltima.
	 * �ste m�todo s�lo se debe usar con un SP que devuelva una consulta. Para los que no devuelven consultas se debe usar execute o executeUpdate del mismo objeto CallableStatement.
	 * @author jorge.torner
	 * @since 21-12-2011
	 * @param cs -Callable statement que invoca al SP con sus par�metros
	 * @return ResultSet Con el resultado de la primer consulta que tenga el SP
	 * @throws SQLException
	 */
	static public ResultSet executeRetrieveResultSet(CallableStatement cs) throws SQLException {
        if (cs == null) {
            throw new IllegalArgumentException("The callable statement specified was null.");
        }
        // Execute the statement...
        boolean resultSetFound;
        try {
            resultSetFound = cs.execute();
        }
        catch (Exception e) {
            SQLException sqle = new SQLException("Failed to execute the statement: " + e);
            sqle.initCause(e);
            throw sqle;
        }
        // Retrieve the first result set from the statement...
        try {
            while (!resultSetFound) {
                resultSetFound = cs.getMoreResults();
                // Check if there are no more results...
                if (!resultSetFound && cs.getUpdateCount() == -1) {
                    // No more result sets are available. Stop searching...
                    break;
                }
                // Else continue looking for a result set...
            }
            if (resultSetFound) {
                ResultSet rs = cs.getResultSet();
                if (rs == null) {
                    throw new IllegalStateException("Unexpectedly failed to retrieve a result set from statement.");
                }
                return rs;
            }
            else {
                throw new SQLException("Scanned all results returned by the statement but did not find a result set.");
            }
        }
        catch (Exception e) {
            SQLException sqle = new SQLException("Executed the statement but failed to retrieve a result set: " + e);
            sqle.initCause(e);
            throw sqle;
        }
    }
}

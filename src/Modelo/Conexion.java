package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class Conexion 
{
    Connection con;
    public Connection getConnection()
    {
        try
        { 
            //String myBD= "jdbc:mysql://189.143.85.216:3306/sistemaventa?serverTimezone=UTC";
            String myBD= "jdbc:mysql://192.168.1.68:3306/sistemaventa?serverTimezone=UTC";
            con=DriverManager.getConnection(myBD,"user2","datatest96");
            return con;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        return null;
    }
    
}

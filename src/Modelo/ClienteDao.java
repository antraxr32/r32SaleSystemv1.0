package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class ClienteDao 
{
    Conexion cn= new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegitrarCliente(Cliente cl) //Como su nombre lo indica, este apartado es para insertar clientes a la base de datos
    {
        String sql= "INSERT INTO clientes(rfc, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        
        try
        {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getRfc());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.execute();
            return true;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(SQLException e)
            {
                System.out.println(e.toString());
            }
        }
        
    }
    
    public List ListarCliente() //Esta parte es para listar los clientes en la tabla
    {
        List<Cliente> ListaCl= new ArrayList();
        String sql= "SELECT * FROM clientes";
        
        try
        {
            con= cn.getConnection();
            ps= con.prepareStatement(sql);
            rs= ps.executeQuery();
            
            while(rs.next())
            {
                Cliente cl=new Cliente();
                
                cl.setId(rs.getInt("id"));
                cl.setRfc(rs.getString("rfc"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                ListaCl.add(cl);   
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        return ListaCl;
    }
    
    public boolean EliminarCliente(int id)
    {
        String sql="DELETE FROM clientes WHERE id=?";
        
        try
        {
            ps=con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
        finally
        {
            try 
            {
                con.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarCliente(Cliente cl)
    {
        String sql="UPDATE clientes SET rfc=?, nombre=?, telefono=?, direccion=?, razon=? WHERE id=?";
        try
        {
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getRfc());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.setInt(6, cl.getId());
            ps.execute();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            return false;
        }
        finally
        {
            try
            {
                con.close();
            }
            catch(SQLException ex)
            {
                System.out.println(ex.toString());
            }
        }
    }
    
    public Cliente BuscarCliente(String rfc)
    {
        Cliente cl=new Cliente();
        String sql="SELECT * FROM clientes WHERE rfc= ?";
        
        try
        {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, rfc);
            rs=ps.executeQuery();
            
            if(rs.next())
            {
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon")); 
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        return cl;
    }
}

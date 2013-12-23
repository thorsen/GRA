
package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ruth_Cordon
 */

public class Norma {

    private static String url = "jdbc:sqlserver://192.168.1.53:1433";
    //private static String url = "jdbc:sqlserver://localhost";
    private static String user = "SQL_PwC";
    private static String pass = "Ru8865No";
    
    static{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver de la BD");
        }
    }

//--------------------------------------------------------------------------------------------//    
   
    // Devuelve el id de una norma. 0 si no existe
    public int getId(String nombre)throws SQLException{
        Connection con = null;       
        int id=0;
        try{
            con=DriverManager.getConnection(url,user,pass);             
            PreparedStatement ps = con.prepareStatement("Select Id_norma from Norma where Nombre=?");            
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();      
            if (rs.next()) {
                id=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();  
        }catch(SQLException e){            
            e.printStackTrace();
            throw e;  
        }finally{
            if (con!=null){
                con.close();
            }
        }
    return id;
    }

//--------------------------------------------------------------------------------------------//    
    
     // Devuelve el nombre de una norma
     public String getNombre(int norma)throws SQLException{
        Connection con = null;       
        String nombre="";
        try{
            con=DriverManager.getConnection(url,user,pass);             
            PreparedStatement ps = con.prepareStatement("Select Nombre from Norma where Id_norma=?");            
            ps.setInt(1, norma);
            ResultSet rs=ps.executeQuery();      
            if (rs.next()){
                nombre=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();  
        }catch(SQLException e){            
            e.printStackTrace();
            throw e;  
        }finally{
            if (con!=null){
                con.close();
            }
        }
    return nombre;
    }

//--------------------------------------------------------------------------------------------//    
     
    // Devuelve las normas  existentes en la base de datos vigentes
    public ArrayList getNormas()throws SQLException{
        Connection con = null;       
        ArrayList <String> normas=new ArrayList <String>();
        try{
            con=DriverManager.getConnection(url,user,pass);             
            PreparedStatement ps = con.prepareStatement("Select Distinct Nombre from Norma where Vigente=1");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                normas.add(rs.getString(1));
            }
            rs.close();
            ps.close();
            con.close();  
        }catch(SQLException e){            
            e.printStackTrace();
            throw e;  
        }finally{
            if (con!=null){
                con.close();
            }
        }
    return normas;
    }
    
//--------------------------------------------------------------------------------------------//    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruth Cordon
 */

public class Site {

  
    private static String url = "jdbc:sqlserver://" + Global.IP_SERVER_GCP;
    private static String user = Global.USER_SERVER_GCP;
    private static String pass = Global.PASS_SERVER_GCP;
    
    static{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver de la BD");
        }
    } 
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve el id de un site. Cero si no existe
    public int getId (String nombre)throws SQLException{
        Connection con = null;
        int id=0;
        try{            
            con=DriverManager.getConnection(url,user,pass);  
            PreparedStatement ps = con.prepareStatement("Select Id_site from Site where Nombre=?");   
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                id=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
       }catch (SQLException e){
                e.printStackTrace();
                throw e;
       }finally{      
              if (con!=null) con.close();        
       }
       return id;
    }

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve el nombre del site. Cadena vacia si no existe
    public static String getNombre (int site)throws SQLException{
        Connection con = null;
        String nombre="";
        try{            
            con=DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Nombre from Site where Id_site=?");   
            ps.setInt(1, site);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                nombre=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
        finally{
            try {
              if (con!=null) con.close();
            }catch (SQLException e){
               e.printStackTrace();
            }
        }
        return nombre;
    }

//--------------------------------------------------------------------------------------------//     
    
}


package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruth_Cordon
 */

public class Direccion {
    
     private static String url = "jdbc:sqlserver://192.168.1.165:1433";
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
    
    // Inserta la direccion de un ensayo. Devuelve true si ya existe direccion para el asunto.
    public boolean NuevaDireccion(int asunto, Double direccion)throws SQLException{
       Connection con = null;
       boolean b=false;
       try{            
          con=DriverManager.getConnection(url,user,pass);  
          
          PreparedStatement ps = con.prepareStatement("Select * from Direccion where Asunto=?"); 
          PreparedStatement ps2 = con.prepareStatement("Insert into Direccion(Asunto,Dirección) values (?,?)");   
          ps.setInt(1, asunto);
          ResultSet rs=ps.executeQuery();
          if (rs.next()){
              b=true;    
          }
          rs.close();
          ps.close();
          if (!b){
              ps2.setInt(1, asunto);
              ps2.setDouble(2, direccion); 
              ps2.execute();
          }
          ps2.close();            
          
          con.close();
      }catch (SQLException e){
           e.printStackTrace();
           throw e;
      }finally{
           if (con!=null) {
               con.close();
           }
      }
      return b;
    }
    
//--------------------------------------------------------------------------------------------//      

    // Devuelve la direccion de un asunto.
    public Double getDireccion(int asunto) throws SQLException{
        Connection con = null;
        Double direccion=null;
        try {
            con=DriverManager.getConnection(url,user,pass);          
            PreparedStatement ps= con.prepareStatement("Select Dirección from Direccion where Asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();      
            if (rs.next()){
                direccion=rs.getDouble(1);
                if (rs.wasNull()){
                    direccion=null;
                }
            } 
            rs.close();
            ps.close();
            con.close();     
        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) con.close();
        }
        return direccion;
    }

//--------------------------------------------------------------------------------------------//      
 
    // Devuelve el sector dada una dirección y una amplitud.
    public double[] getSector(double direccion,double amplitud) {
        double[] sector=new double[2];
        double min=direccion-amplitud;
        if (min<0){min=min+360;}
        double max=direccion+amplitud;
        if (max>=360){max=max-360;}
        sector[0]=min;sector[1]=max;
        return sector;
    }

//--------------------------------------------------------------------------------------------//      
    
}

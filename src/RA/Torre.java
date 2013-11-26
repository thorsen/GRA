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

public class Torre {
    
    private static String url = "jdbc:sqlserver://192.168.1.165:1433";
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
    
    // Inserta una torre en un asunto. Devuelve true si ya existe torre para el asunto.
    public boolean NuevaTorre(int asunto,String[] torre)throws SQLException{
       Connection con = null;
       boolean b=false;
       try{  
          con=DriverManager.getConnection(url,user,pass);  
      
          PreparedStatement ps_consulta = con.prepareStatement("Select * from Torre where Asunto=? and Tipo=?"); 
          PreparedStatement ps_insercion = con.prepareStatement("Insert into Torre values (?,?,?,?,?)");   
          ps_consulta.setInt(1, asunto);
          ps_consulta.setString(2, torre[0]);
          ResultSet rs=ps_consulta.executeQuery();
          if (rs.next()){
              b=true;    
          }
          rs.close();
          ps_consulta.close();
          if (!b){
                ps_insercion.setInt(1, asunto);
                ps_insercion.setString(2, torre[0]); // Tipo
                ps_insercion.setDouble(3, Double.parseDouble(torre[1])); // x
                ps_insercion.setDouble(4, Double.parseDouble(torre[2])); // y
                ps_insercion.setDouble(5, Double.parseDouble(torre[3])); // z
                ps_insercion.execute();              
                System.out.println("Insert into Torre values ("+asunto+","+torre[0]+","+torre[1]+","+torre[2]+","+torre[3]+")");
          }
          
          ps_insercion.close();            
          con.close();
      }catch (SQLException e){
           e.printStackTrace();
           throw e;
      }
      finally{
           if (con!=null) {con.close();}
      }
      return b;
    }
    
//--------------------------------------------------------------------------------------------//      

    // Devuelve una torre de un asunto [x,y,z].
    public String [] getTorre(int asunto,String tipo) throws SQLException{
        Connection con = null;
        String [] torre=new String [3];
        torre[0]="";torre[1]="";torre[2]="";
        try {
            con=DriverManager.getConnection(url,user,pass);     
            
            PreparedStatement ps= con.prepareStatement("Select * from Torre where Asunto=? and Tipo=?");   
            ps.setInt(1, asunto);
            ps.setString(2, tipo);
            ResultSet rs=ps.executeQuery();      
            if (rs.next()){
                torre[0]=String.valueOf(rs.getDouble(3)); // X
                torre[1]=String.valueOf(rs.getDouble(4)); // Y
                torre[2]=String.valueOf(rs.getDouble(5)); // Z
            } 
            rs.close();
            ps.close();
            con.close();     
        }catch(SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            if (con!=null) {con.close();}
        }
        return torre;
    }

//--------------------------------------------------------------------------------------------//      
 
}
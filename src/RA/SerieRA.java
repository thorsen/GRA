
package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ruth Cordon
 */
public class SerieRA {
    
    private static String url = "jdbc:sqlserver://" + Global.IP_SERVER_GCP;
    //private static String url = "jdbc:sqlserver://localhost";
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
 
    // Devuelve la variable de una serie de ruido. null si no existe
    public String getVariable(int serie)throws SQLException{
         Connection con = null;       
         String v=null;
         try{
             con = DriverManager.getConnection(url,user,pass);           
             PreparedStatement ps = con.prepareStatement("Select Variable.Variable from Variable,SerieRA where SerieRA.Variable=Variable.Id_variable and SerieRA.Id_serie=?");          
             ps.setInt(1, serie); 
             ResultSet rs=ps.executeQuery();
             if (rs.next()){
                 v=rs.getString(1);
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
        return v;
     }
 
//--------------------------------------------------------------------------------------------//     
  
    // Devuelve el codigo de una serie de ruido. Null si no existe
    public String getCodigo(int serie)throws SQLException{
       Connection con = null;       
       String Codigo=null;
       try{
          con = DriverManager.getConnection(url,user,pass);         
          PreparedStatement ps = con.prepareStatement("Select Codigo from SerieRA where Id_serie=?");          
          ps.setInt(1, serie);
          ResultSet rs=ps.executeQuery();                               
          if (rs.next()){
              Codigo=rs.getString(1);
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
      return Codigo;
    } 
    
//--------------------------------------------------------------------------------------------//         

     // Devuelve el codigo de una serie de ruido. Null si no existe
    public String getCodigo(String descripcion)throws SQLException{
       Connection con = null;       
       String Codigo=null;
       try{
          con = DriverManager.getConnection(url,user,pass);         
          PreparedStatement ps = con.prepareStatement("Select Codigo from SerieRA where Descripción=?");          
          ps.setString(1, descripcion);
          ResultSet rs=ps.executeQuery();                               
          if (rs.next()){
              Codigo=rs.getString(1);
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
      return Codigo;
    } 
    
//--------------------------------------------------------------------------------------------//         
    
    // Devuelve la serie de un codigo de RA. 0 si no existe
    public int getSerie(String codigo)throws SQLException{
       Connection con = null;       
       int serie=0;
       try{
          con = DriverManager.getConnection(url,user,pass);         
          PreparedStatement ps = con.prepareStatement("Select Id_serie from SerieRA where Codigo=?");          
          ps.setString(1, codigo);
          ResultSet rs=ps.executeQuery();                               
          if (rs.next()){
              serie=rs.getInt(1);
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
      return serie;
    } 
    
//--------------------------------------------------------------------------------------------//         
    
    // Devuelve las ponderaciones de un conjunto de codigos
    public ArrayList<Double> getPonderaciones(ArrayList<String> codigos) throws SQLException{
        Connection con = null;       
        ArrayList<Double> ponderaciones=new ArrayList<Double>();
        try{
            con = DriverManager.getConnection(url,user,pass);          
            String sql="Select Valor from Ponderacion where Serie=?";
            PreparedStatement ps=con.prepareStatement(sql);
            int n=codigos.size();
            String salida="";
            for (int i=0;i<n;i++){
                int serie=this.getSerie(codigos.get(i));
                ps.setInt(1, serie);
                ResultSet rs=ps.executeQuery();
                if (rs.next()){
                    ponderaciones.add(rs.getDouble(1));     
                    salida=salida+" "+ponderaciones.get(i);
                }                 
                rs.close();
            }
            System.out.println(salida);
            ps.close();
            con.close();
            
        } catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
             if (con!=null){
                 con.close();
             }
        }
        return ponderaciones;
    }
    
//--------------------------------------------------------------------------------------------//       

    // Devuelve las bandas de octava de un conjunto de codigos
    public ArrayList<String> getBandasOctava(ArrayList<String> codigos) throws SQLException{
           
        ArrayList<String> bandas=new ArrayList<String>();
        int n=codigos.size();
        String salida="";
        for (int i=0;i<n;i++){
            if (codigos.get(i).contains("Hz")){
                bandas.add(codigos.get(i));
                salida=salida+" "+codigos.get(i);
            }
        }     
        System.out.println(salida);
        return bandas;
    }
    
//--------------------------------------------------------------------------------------------//       

    // Devuelve las posibles series (descripción) de un tipo
    public ArrayList getSeriesRA(int tipo)throws SQLException{
        Connection con = null;       
        ArrayList <String> series=new ArrayList <String>();
        try{
           con = DriverManager.getConnection(url,user,pass);            
           PreparedStatement ps = con.prepareStatement("Select Descripción from SerieRA where Tipo=? order by Id_serie");   
           ps.setInt(1, tipo);
           ResultSet rs=ps.executeQuery();         
           while (rs.next()){
               series.add(rs.getString(1));
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
         return series;
    }  
 
//--------------------------------------------------------------------------------------------//  

    // Devuelve la descripcion de una serie de ruido. Null si no existe
    public String getDescripcion(int serie)throws SQLException{
       Connection con = null;       
       String descripcion=null;
       try{
          con = DriverManager.getConnection(url,user,pass);         
          PreparedStatement ps = con.prepareStatement("Select Descripción from SerieRA where Id_serie=?");          
          ps.setInt(1, serie);
          ResultSet rs=ps.executeQuery();                               
          if (rs.next()){
              descripcion=rs.getString(1);
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
      return descripcion;
    } 
    
//--------------------------------------------------------------------------------------------//         
}

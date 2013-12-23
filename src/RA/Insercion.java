
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

public class Insercion {

    private static String url = "jdbc:sqlserver://192.168.1.53:1433";
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
    
    public boolean NuevaInsercion(int asunto,int site,int fase,String fichero,String Fini,String Ffin,String fecha,int usuario,int RF) throws SQLException{ 
         Connection con=null; 
         boolean ok=false;
         try {
            con=DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select max(Nº) from Insercion where asunto=? and Site=?");       
            ps.setInt(1, asunto);
            ps.setInt(2, site);
            ResultSet rs=ps.executeQuery(); 
            int n=0;             
            if (rs.next()){ // Se comprueba que existe
               n=rs.getInt(1);
            }
            rs.close();
            n=n+1;
            String sqli="Insert into Insercion values (?,?,?,?,?,?,?,?,?,?)";
            String sql="Insert into Insercion values ("+asunto+","+site+","+n+","+fichero+","+Fini+","+Ffin+","+fecha+","+usuario+",";
            
            ps = con.prepareStatement(sqli);  
            ps.setInt(1, asunto);
            ps.setInt(2, site);
            ps.setInt(3, n);
            ps.setInt(4, fase);
            ps.setString(5, fichero);
            ps.setString(6,Fini);
            ps.setString(7,Ffin);
            ps.setString(8,fecha);
            ps.setInt(9, usuario);
            if (fase!=5){ // Ruido
                ps.setNull(10, java.sql.Types.INTEGER);
                sql=sql+"null)";
            } else{
                ps.setInt(10, RF);
                sql=sql+RF+")";
            }
            ps.execute(); 
            ps.close();
            con.close();
            System.out.println(sql);
            ok=true;
         } catch (SQLException e){
            e.printStackTrace();
            throw e;
         }  finally{
            if (con!=null){
                con.close();
            }
         }
         return ok;
    }
 
//--------------------------------------------------------------------------------------------//               
    
    // Devuelve las inserciones realizadas en un asunto
    public ArrayList getInsercion(int asunto) throws SQLException{ 
         Site S=new Site();
         Responsable R=new Responsable();
         Connection con=null; 
         ArrayList<ArrayList> insercion=new ArrayList<ArrayList>();
         try {
            con=DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Fini,Ffin,Ruta,Fase,Site,Fecha,Responsable,RF from Insercion where asunto=?");       
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();   
            while (rs.next()){ // Se comprueba que existe
               ArrayList dato=new ArrayList();
               dato.add(rs.getString(1));
               dato.add(rs.getString(2));
               dato.add(rs.getString(3));
               int fase=rs.getInt(4);
               if (fase==1){
                   dato.add("SC");
               } else{
                   if (fase==2){
                       dato.add("PC");
                   } else{
                        if (fase==3){
                            dato.add("PC_ME");
                        } else{
                            if (fase==4){
                                dato.add("DR_ME");
                            } else{
                                dato.add("RA");
                            }
                        }
                   }
                       
              }
               
               int site=rs.getInt(5);
               dato.add(S.getNombre(site));
               dato.add(rs.getString(6));
               int responsable=rs.getInt(7);
               dato.add(R.getNombre(responsable));
               int rf=rs.getInt(8);
               if (!rs.wasNull()){dato.add(rf);}
               insercion.add(dato);
            }
            rs.close();
            ps.close();
            con.close();
         } catch (SQLException e){
            e.printStackTrace();
            throw e;
         }  finally{
            if (con!=null){
                con.close();
            }
         }
         return insercion;
    }
    
//--------------------------------------------------------------------------------------------//  
    
    // Devuelve las inserciones realizadas en un asunto y un site
    public ArrayList getInsercion(int asunto,int site) throws SQLException{ 
         
         Connection con=null; 
         ArrayList<String[]> insercion=new ArrayList<String[]>();
         
         try {
            
             con=DriverManager.getConnection(url,user,pass); 
             
             String sql="Select Ruta,RF from Insercion where Asunto=? and Site=? order By Nº";
             PreparedStatement ps = con.prepareStatement(sql);       
             ps.setInt(1, asunto);
             ps.setInt(2, site);
             System.out.println("Select Ruta,RF from Insercion where Asunto="+asunto+" and Site="+site+" order By Nº");
             ResultSet rs=ps.executeQuery();   
             while (rs.next()){
                 String[] dato=new String[2];
                 String ruta=rs.getString(1);
                 dato[0]=ruta.substring(ruta.lastIndexOf("\\")+1);
                 dato[1]=""+rs.getBoolean(2);
                 insercion.add(dato);
            }
            rs.close();
            ps.close();
            con.close();
         } catch (SQLException e){
            e.printStackTrace();
            throw e;
         }  finally{
            if (con!=null){
                con.close();
            }
         }
         return insercion;
    }
    
}


package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ruth_Cordon
 */

public class Parque {
    
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
    
    // Inserta un nuevo parque en la base de dtos incluyendo sus posiciones. Devuelve true si el parque existe
    public boolean NuevoParque(String nombre, String poblacion, String provincia, String pais, String datum, ArrayList<String []> Pos) throws SQLException{
        Connection con = null;
        boolean b=false; // No existe el parque
        try{            
          con = DriverManager.getConnection(url,user,pass);  
          con.setAutoCommit(false);
          PreparedStatement ps=con.prepareStatement("Select Id_parque,Nombre from Parque order by Id_parque");
          ResultSet rs=ps.executeQuery();
          int Id=0;             
          while (rs.next()){ // Se comprueba que existe
             Id=rs.getInt(1);
             if (rs.getString(2).equals(nombre)){
                 b=true; // Existe parque
             }
          }
          rs.close();
          ps.close();
          nombre=nombre.replace("'","");
          if (!b){ // No existe
              Id=Id+1;
              String sqli="Insert into Parque values ("+Id+",'"+nombre+"',";
              if (poblacion==null){
                sqli=sqli+"null,";
              }else {
                sqli=sqli+"'"+poblacion+"',";
              }
              if (provincia==null){
                sqli=sqli+"null,";
              }else {
                sqli=sqli+"'"+provincia+"',";
              }
              if (pais==null){
                sqli=sqli+"null,";
              }else {
                sqli=sqli+"'"+pais+"',";
              }
              if (datum==null){
                sqli=sqli+"null)";
              }else {
                sqli=sqli+"'"+datum+"')";
              }
              Statement stmt=con.createStatement();
              System.out.println(sqli);
              stmt.executeUpdate(sqli);
             
              stmt.close();
              
              // Se inserta en posicion
              String sqli2="Insert into Posicion values (?,?,?,?,?,?,?,?)";
              PreparedStatement ps2=con.prepareStatement(sqli2);
              ps2.setInt(1, Id);
              
              for (int j=0;j<Pos.size();j++){
                  ps2.setString(2, Pos.get(j)[0]);
                  ps2.setDouble(3, Double.parseDouble(Pos.get(j)[1]));
                  ps2.setDouble(4, Double.parseDouble(Pos.get(j)[2]));
                  ps2.setDouble(5, Double.parseDouble(Pos.get(j)[3]));
                  ps2.setDouble(6, Double.parseDouble(Pos.get(j)[4]));
                  ps2.setDouble(7, Double.parseDouble(Pos.get(j)[5]));
                  ps2.setDouble(8, j+1);
                  ps2.execute();  
                  
             }
             ps2.close();   
          }
          con.commit();
          con.close();
          }catch (SQLException e){
               e.printStackTrace();
               throw e;
          }
          finally{
            try {
              if (con!=null) con.close();
            } catch (SQLException e){
               e.printStackTrace();
            }
         }
        return b;
    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve el nombre de un parque. Cadena vacía si no existe
    public String getNombre(int pe)throws SQLException{
        Connection con = null;       
        String nombre="";
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select Nombre from Parque where Id_parque=?");            
            ps.setInt(1, pe);
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
   
    // Devuelve el identificador de parque. 0 si no existe
    public int getId(String nombre)throws SQLException{
        Connection con = null;       
        int id=0;
        try{
           con = DriverManager.getConnection(url,user,pass);           
           PreparedStatement ps = con.prepareStatement("Select Id_parque from Parque where Nombre=?");            
           ps.setString(1, nombre);
           ResultSet rs=ps.executeQuery();                       
           if (rs.next()){
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

    // Devuelve los parque existentes en la BD
    public ArrayList getParques()throws SQLException{
        Connection con = null;       
        ArrayList <String> parques=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Distinct Nombre from Parque");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                parques.add(rs.getString(1));
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
    return parques;
    }

//--------------------------------------------------------------------------------------------//
  
    // Devuelve los parque asociados a asuntos de un responsable 
    public ArrayList getParques(int responsable)throws SQLException{
        Connection con = null;       
        ArrayList <String> parques=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select distinct Parque.Nombre from Parque,Asunto where Asunto.PE=Parque.Id_parque and Asunto.responsable=?");            
            ps.setInt(1, responsable);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                parques.add(rs.getString(1));
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
    return parques;
    }

//--------------------------------------------------------------------------------------------//
    // Devuelve los codigos de posiciones de un parque
     public ArrayList getNPosiciones(int PE)throws SQLException{
            Connection con = null;       
            ArrayList <String> posiciones=new ArrayList <String>();
            try{
                con = DriverManager.getConnection(url,user,pass);           
                PreparedStatement ps = con.prepareStatement("Select Codigo from Posicion where PE=?");            
                ps.setInt(1, PE);
                ResultSet rs=ps.executeQuery();                       
                while (rs.next()){
                    posiciones.add(rs.getString(1));
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
        return posiciones;
    }
 
//--------------------------------------------------------------------------------------------//

     // Devuelves las posiciones de un parque ordenadas por X (Codigo,X,Y,Z,)
    public ArrayList <String []> getPosiciones(int PE)throws SQLException{
        Connection con = null;       
        ArrayList <String []> posiciones=new ArrayList <String []>();
        try{
                con = DriverManager.getConnection(url,user,pass);        
                PreparedStatement ps = con.prepareStatement("Select * from Posicion where PE=? order by X");            
                ps.setInt(1, PE);
                ResultSet rs=ps.executeQuery();                       
                while (rs.next()){
                    String [] linea=new String [6];
                    linea [0]=rs.getString(2); // Codigo
                    linea [1]=""+rs.getDouble(3); // X
                    linea [2]=""+rs.getDouble(4); // Y
                    linea[3]="";
                    double z=rs.getDouble(5); //Z
                    if (!rs.wasNull()){
                        linea [3]=""+z;
                    }
                    linea [4]=""+rs.getDouble(6); // Dn
                    linea [5]=""+rs.getDouble(7); // Hb
                    posiciones.add(linea);  
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
            return posiciones;
        }

//--------------------------------------------------------------------------------------------//

    // Devuleve los datos asociados a un parque
     public ArrayList getParque(int parque)throws SQLException{
       Connection con = null;
       ArrayList <String> pe=new ArrayList <String>();
       try{            
           con = DriverManager.getConnection(url,user,pass);  
           PreparedStatement ps = con.prepareStatement("Select * from parque where Id_parque=?");   
           ps.setInt(1, parque);
           ResultSet rs=ps.executeQuery(); 
           if (rs.next()){
               pe.add(0, String.valueOf(rs.getInt(1)));
               pe.add(1, rs.getString(2)); // nombre
               pe.add(2, rs.getString(3)); // poblacion
               pe.add(3, rs.getString(4)); // provincia
               pe.add(4, rs.getString(5)); // pais
               pe.add(5, rs.getString(6)); // datum  
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
       return pe;
    }

//--------------------------------------------------------------------------------------------//
   
 // Devuelve la poblacion de un parque. Cadena vacía si no existe
    public String getPoblacion(int pe)throws SQLException{
        Connection con = null;       
        String poblacion="";
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select Población from Parque where Id_parque=?");            
            ps.setInt(1, pe);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                poblacion=rs.getString(1);
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
    return poblacion;
    }
    
//--------------------------------------------------------------------------------------------//    
   
 // Devuelve la provincia de un parque. Cadena vacía si no existe
    public String getProvincia(int pe)throws SQLException{
        Connection con = null;       
        String provincia="";
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select Provincia from Parque where Id_parque=?");            
            ps.setInt(1, pe);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                provincia=rs.getString(1);
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
    return provincia;
    }
    
//--------------------------------------------------------------------------------------------//    

 // Devuelve las coordenadas de una posicion en un parque
    public double[] getCoordenadas(int pe,String posicion)throws SQLException{
        Connection con = null;       
        double[] coordenadas=new double[3];
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select x,y,z from Parque,Posicion where Parque.Id_parque=Posicion.PE and Parque.Id_parque=? and Posicion.Codigo=?");            
            ps.setInt(1, pe);
            ps.setString(2, posicion);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                coordenadas[0]=rs.getDouble(1);
                coordenadas[1]=rs.getDouble(2);
                coordenadas[2]=rs.getDouble(3);
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
    return coordenadas;
    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve la Hb de una posicion
    public double getHb(int pe,String posicion)throws SQLException{
        Connection con = null;       
        double hb=0;
        try{
            con = DriverManager.getConnection(url,user,pass);      
            System.out.println("Posicion "+posicion);
            PreparedStatement ps=con.prepareStatement("Select Posicion.Hb from Parque,Posicion where Parque.Id_parque=Posicion.PE and Parque.Id_parque=? and Posicion.Codigo=?");            
            ps.setInt(1, pe);
            ps.setString(2, posicion);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                System.out.println("Eo");
                hb=rs.getDouble(1);
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
    return hb;
    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve la Hb de una posicion
    public double getDn(int pe,String posicion)throws SQLException{
        Connection con = null;       
        double dn=0;
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select Dn from Parque,Posicion where Parque.Id_parque=Posicion.PE and Parque.Id_parque=? and Posicion.Codigo=?");            
            ps.setInt(1, pe);
            ps.setString(2, posicion);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                dn=rs.getDouble(1);
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
    return dn;
    }
    
//--------------------------------------------------------------------------------------------//    
   
    // Devuelve el nº de una posicion en un parque
    public int NPosicion(int pe,String posicion)throws SQLException{
        Connection con = null;       
        int npos=0;
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select NPos from Parque,Posicion where Parque.Id_parque=Posicion.PE and Parque.Id_parque=? and Posicion.Codigo=?");            
            ps.setInt(1, pe);
            ps.setString(2, posicion);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                npos=rs.getInt(1);
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
    return npos;
    }
    
//--------------------------------------------------------------------------------------------//    
    
     // Devuelve si un codigo es posicion de un parque
    public boolean isPosicion(int pe,String posicion)throws SQLException{
        Connection con = null;       
        boolean is=false;
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select * from Parque,Posicion where Parque.Id_parque=Posicion.PE and Parque.Id_parque=? and Posicion.Codigo=?");            
            ps.setInt(1, pe);
            ps.setString(2, posicion);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
               is=true;
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
    return is;
    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve el datum de un parque. Cadena vacía si no existe
    public String getDatum(int pe)throws SQLException{
        Connection con = null;       
        String datum="";
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps=con.prepareStatement("Select Datum from Parque where Id_parque=?");            
            ps.setInt(1, pe);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                datum=rs.getString(1);
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
    return datum;
    }
    
//--------------------------------------------------------------------------------------------//    

    // Borra un parque eólico
    public boolean BorrarParque(int pe) throws SQLException {
        Connection con = null;             
        ArrayList<String> sql=new ArrayList<String>();
        boolean ok=false;
        try{     
            
            con = DriverManager.getConnection(url, user, pass);         
            con.setAutoCommit(false);
            
            sql.add("Delete from Posicion where PE=?");
            sql.add("Delete from Parque where Id_parque=?");
           
            for (int i=0;i<sql.size();i++){
                PreparedStatement ps = con.prepareStatement(sql.get(i));   
                ps.setInt(1, pe);
                ps.execute();
                System.out.println(sql.get(i));
                ps.close();
            }
            con.commit();
            con.close();
            ok=true;
        }catch(SQLException e){            
            e.printStackTrace(); 
            throw e;
        }finally{
            if (con!=null){con.close();}
        }
        return ok;
    } 
    
//--------------------------------------------------------------------------------------------//    
}

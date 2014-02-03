
package RA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * @author Ruth_Cordon
 */

public class Aerogenerador {
    
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
    
    // Introduce un nuevo aerogenerador en la bd. Devuelve true si el aerogenerador ya existia
     public boolean NuevoAerogenerador(String modelo, String fabricante, double Hb, double Dn, double Pnominal, double Vin, double Vcut, int lineas, int regulacion, double Vn)throws SQLException{
        Connection con = null;
        boolean b=false;
        try{            
            con=DriverManager.getConnection(url,user,pass); 
            con.setAutoCommit(false);
            // Se inserta el aero
            PreparedStatement ps = con.prepareStatement("Select Id_aero,Modelo,Hb from Aerogenerador order by Id_aero");   
            ResultSet rs=ps.executeQuery();
            int Id=0;             
            while (rs.next()){ // Se comprueba si existe
                Id=rs.getInt(1);
                if (rs.getString(2).toUpperCase().equals(modelo.toUpperCase())&&(rs.getDouble(3)==Hb)){
                    b=true;                
                }
            }
            rs.close();
            ps.close();
            if (!b){
                Id=Id+1;
                String sqli="Insert into Aerogenerador values ("+Id+",'"+modelo+"','"+Hb+"',";
                if (fabricante==null){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+fabricante+"',";                    
                }
                sqli=sqli+"'"+Dn+"',";
                sqli=sqli+"'"+Pnominal+"',";                    
                sqli=sqli+"'"+Vin+"',";                    
                sqli=sqli+"'"+Vcut+"',";                    
                sqli=sqli+lineas+",";  
                sqli=sqli+regulacion+",";
                if (Vn==0){
                   sqli=sqli+"null,";
               }else{
                  sqli=sqli+Vn+",";              
               }
                sqli=sqli+"0)";
                Statement stmt=con.createStatement();
                stmt.executeUpdate(sqli);  
                stmt.close();
                
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
    
     // Devuelve el id de un aero. 0 si no existe   
     public int getId(String modelo, double hb)throws SQLException{
        Connection con = null;       
        int id=0;
        try{
           con = DriverManager.getConnection(url,user,pass);           
           PreparedStatement ps = con.prepareStatement("Select * from Aerogenerador where Modelo=? and Hb=?");          
           ps.setString(1, modelo);
           ps.setDouble(2, hb);
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
     
    // Devueleve el modelo de un aero. Cadena vacia no no existe
     public String getModelo(int aero)throws SQLException{
        Connection con = null;
        String modelo="";
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Modelo from Aerogenerador where Id_Aero=?");   
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                    modelo=rs.getString(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
              if (con!=null) con.close();
            }catch (SQLException e){
               e.printStackTrace();
            }
        }
          return (modelo);
        }
     
//--------------------------------------------------------------------------------------------//
     
    // Devuelve Hb del aerogenerador. Cero si no existe
    public double getHb(int aero)throws SQLException{
        Connection con = null;
        double Hb=0;
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Hb from Aerogenerador where Id_aero=?");             
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                Hb=rs.getDouble(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (con!=null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return Hb;
    }

//--------------------------------------------------------------------------------------------//
    
     // Devuelve el diámetro de rotor de un aero. 0 si no existe
     public double getDn(int aero)throws SQLException{
        Connection con = null;       
        double dn=0;
        try{
           con = DriverManager.getConnection(url,user,pass);             
           PreparedStatement ps = con.prepareStatement("Select Dn from Aerogenerador where Id_aero=?");
           ps.setInt(1, aero);
           ResultSet rs=ps.executeQuery();                       
           if (rs.next()){
               dn=rs.getDouble(1);
           }
           rs.close();
           ps.close();
           con.close();  
        }catch(SQLException e){            
           e.printStackTrace(); 
        }finally{
           if (con!=null){
              con.close();
           }
        }
        return dn;
     }
     
//--------------------------------------------------------------------------------------------//
  
     // Devuelve la Potencia Nominal de un aero. 0 si no existe
     public double getPn(int aero)throws SQLException{
        Connection con = null;       
        double pn=0;
        try{
           con = DriverManager.getConnection(url,user,pass);             
           PreparedStatement ps = con.prepareStatement("Select Pnominal from Aerogenerador where Id_aero=?");
           ps.setInt(1, aero);
           ResultSet rs=ps.executeQuery();                       
           if (rs.next()){
               pn=rs.getDouble(1);
           }
           rs.close();
           ps.close();
           con.close();  
        }catch(SQLException e){            
           e.printStackTrace(); 
        }finally{
           if (con!=null){
              con.close();
           }
        }
        return pn;
     }
     
//--------------------------------------------------------------------------------------------//
     
     // Devuelve la velocidad de arranque Vin del aerogenerador
     public double getVin(int aero)throws SQLException{
        Connection con = null;
        double vin=0;
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Vin from Aerogenerador where Id_aero=?");          
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                vin=rs.getDouble(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (con!=null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
      return (vin);
     }
    
//--------------------------------------------------------------------------------------------//
     
    // Devuelve la velocidad de corte Vcut del aerogenerador
    public double getVcut(int aero)throws SQLException{
        Connection con = null;
        double vcut=0;
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Vcut from Aerogenerador where Id_aero=?");              
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                vcut=rs.getDouble(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (con!=null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
      return (vcut);
     }

//--------------------------------------------------------------------------------------------//
    
    // Devuelve el número de líneas de evacuacion del aerogenerador
    public int getLineas(int aero)throws SQLException{
        Connection con = null;
        int lineas=0;
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Lineas from Aerogenerador where Id_Aero=?");          
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                lineas=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try {
              if (con!=null) con.close();
            }catch (SQLException e){
               e.printStackTrace();
            }
        }
        return (lineas);
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Devuelve un vector con los modelos de aerogenerador existentes en la base de datos
    public ArrayList getModelos()throws SQLException{
        Connection con = null;       
        ArrayList <String> modelos=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Distinct Modelo from Aerogenerador");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                modelos.add(rs.getString(1));
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
        return modelos;
    }
    
//--------------------------------------------------------------------------------------------//    
  
    // Devuelve las distintas alturas de buje de un modelo
    public ArrayList getHbs(String mod)throws SQLException{
        Connection con = null;
        ArrayList <String> Hb=new ArrayList <String>();
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Hb from Aerogenerador where Modelo=?");   
            ps.setString(1, mod);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                Hb.add(String.valueOf(rs.getDouble(1)));
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            try {
                if (con!=null) con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return (Hb);
    }
      
//--------------------------------------------------------------------------------------------//    
    
    // Devuleve todos los datos de un aero
    public ArrayList getAero(int aero)throws SQLException{
        Connection con = null;
        ArrayList <String> Datos=new ArrayList <String>();
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select * from Aerogenerador where Id_aero=?");   
            ps.setInt(1, aero);
            ResultSet rs=ps.executeQuery(); 
            if (rs.next()){
                Datos.add(0, String.valueOf(rs.getInt(1)));
                Datos.add(1,rs.getString(2)); // modelo
                Datos.add(2,String.valueOf(rs.getDouble(3))); // hb
                Datos.add(3,rs.getString(4)); // fabricante
                Datos.add(4,String.valueOf(rs.getDouble(5))); // Dn
                Datos.add(5,String.valueOf(rs.getDouble(6)));  // Pn  
                Datos.add(6,String.valueOf(rs.getDouble(7)));    // Vin
                Datos.add(7,String.valueOf(rs.getDouble(8)));  // Vcut  
                Datos.add(8,String.valueOf(rs.getInt(9))); // lineas
                Datos.add(9,String.valueOf(rs.getInt(10))); // Reg
                double Vn=rs.getDouble(11);
                if (!rs.wasNull()){
                    Datos.add(10,String.valueOf(Vn)); // Vn
                } else{
                    Datos.add(10,null);
                }
                
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
      return (Datos);
    }
        
//--------------------------------------------------------------------------------------------//        
      
     // Devuelve el tipo de control de potencia de un modelo de aerogenerador
     public int getRegulacion(int aero)throws SQLException{
        Connection con = null;
       int reg=0;
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select Regulacion from Aerogenerador where Id_aero=?");   
            ps.setInt(1, aero); 
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                reg=rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try {
              if (con!=null) con.close();
            }catch (SQLException e){
               e.printStackTrace();
            }
        }
        return (reg);
    }

//--------------------------------------------------------------------------------------------//
    
     // Devuelve una curva de potencia a densidad 1 convertida a densidad 2
     public ArrayList <double[]> ConvertirCP(ArrayList <double[]> CP_IN,double densidad1,double densidad2)throws Exception{
        ArrayList <double []> CP_OUT=new ArrayList <double []>();
        try{       
            int n=CP_IN.size();
            double[] dato=new double[2];
            double v_in = CP_IN.get(0)[0];
            double v_out = CP_IN.get(n-1)[0];
            for (int i=0;i<n;i++){
                dato=new double[2];
                double v=CP_IN.get(i)[0];
                double x1=0;double x2=0;double y1=0;double y2=0;
                double v_ajustada=v * java.lang.Math.pow((densidad2 / densidad1) , 0.333333333333333);
                if (v_ajustada<v_in){
                    x1=CP_IN.get(0)[0];
                    x2=CP_IN.get(1)[0];
                    y1=CP_IN.get(0)[1];
                    y2=CP_IN.get(1)[1];
                } else{
                    if (v_ajustada>v_out){
                        x1=CP_IN.get(n-2)[0];
                        x2=CP_IN.get(n-1)[0];
                        y1=CP_IN.get(n-2)[1];
                        y2=CP_IN.get(n-1)[1];
                    } else{
                        boolean encontrado=false;
                        int j=1;
                        while ((j<n) && (!encontrado)){
                            if (CP_IN.get(j)[0]>v_ajustada){
                                x1 = CP_IN.get(j - 1)[0];
                                x2 = CP_IN.get(j)[0];
                                y1 = CP_IN.get(j - 1)[1];
                                y2 = CP_IN.get(j)[1];
                                encontrado=true;
                            }
                            j++;
                        }
                      
                    }
                }
                double pendiente = (y2 - y1) / (x2 - x1);
                double origen = ((y1 + y2) - (pendiente * (x1 + x2))) / 2;
                double p_ajustada = v_ajustada * pendiente + origen;
                dato[0]=v;
                dato[1]=p_ajustada;
                CP_OUT.add(dato);
                
            }
           
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        
        return (CP_OUT);
    }
 
//--------------------------------------------------------------------------------------------// 
    
}

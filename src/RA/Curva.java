
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

public class Curva {
    
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
    
     // Devuelve la curva de un asunto a una densidad
     public ArrayList <double[]> getCurva(int asunto,double densidad)throws SQLException{
        
        Connection con = null;
        ArrayList <double []> curva=new ArrayList <double []>();
        
        try{            
            con = DriverManager.getConnection(url,user,pass); 
            PreparedStatement ps = con.prepareStatement("Select V,P from Curva where Asunto=? and Densidad=? order by V");   
            ps.setInt(1, asunto); 
            ps.setDouble(2, densidad);
            ResultSet rs=ps.executeQuery();
            
            while (rs.next()) {
                double [] linea=new double [2];
                linea[0]=rs.getDouble(1);
                linea[1]=rs.getDouble(2);
                curva.add(linea);
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
        return (curva);
    }
     
//--------------------------------------------------------------------------------------------//    

     // Devuelve la ct de un asunto a una densidad junto con el respectivo coeficiente de potencia 
     public ArrayList <double[]> getCurva_CP(int asunto,double densidad)throws SQLException{
       
        Connection con = null;
        ArrayList <double []> curva=new ArrayList <double []>();
        try{ 
            Aerogenerador AG=new Aerogenerador();
            Asunto A=new Asunto();
            
            // Se obtiene el aero
            int aero=A.getAero(asunto);
            double dn=AG.getDn(aero);
            
            con = DriverManager.getConnection(url,user,pass); 
            
            PreparedStatement ps = con.prepareStatement("Select V,P from Curva where Asunto=? and Densidad=? order by V");   
            ps.setInt(1, asunto); 
            ps.setDouble(2, densidad);
            ResultSet rs=ps.executeQuery();
            double [] linea=new double [3];
            while (rs.next()) {
                linea=new double [3];
                linea[0]=rs.getDouble(1);
                linea[1]=rs.getDouble(2);
                linea[2]=0;
                if ((linea[0]!=0) && (dn!=0)){
                    linea[2]=1000*linea[1]/(0.5*densidad*(java.lang.Math.PI*java.lang.Math.pow(dn/2,2))*java.lang.Math.pow(linea[0],3)); // Cp
                }
                curva.add(linea);
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
        return (curva);
    }
 
//--------------------------------------------------------------------------------------------// 
     
    // Introduce una nueva curva de potencia asociada a un asunto. Devuelve true si la curva ya existia
    public boolean NuevaCP(int asunto, double densidad, ArrayList <double []> curva)throws SQLException{
        
        Connection con = null;
        boolean b=false;
        
        try{            
            con=DriverManager.getConnection(url,user,pass); 
           
            // Se comprueba si existe
            PreparedStatement ps = con.prepareStatement("Select distinct densidad from Curva where Asunto=?");   
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();        
            while (rs.next()){ 
                if (rs.getDouble(1)==densidad){
                    b=true;                
                }
            }
            rs.close();
            ps.close();
            if (!b){
                String sql="Insert into Curva values (?,?,?,?)";
                PreparedStatement ps2=con.prepareStatement(sql); 
                int n=curva.size();
                for (int j=0;j<n;j++){
                     ps2.setInt(1, asunto);
                     ps2.setDouble(2, densidad);
                     ps2.setDouble(3, curva.get(j)[0]);
                     ps2.setDouble(4, curva.get(j)[1]);
                     System.out.println("Insert into Curva values ("+asunto+","+densidad+","+curva.get(j)[0]+","+curva.get(j)[1]+")");
                     ps2.execute();  
                }
                ps2.close();
            }
   
            con.close();
       }catch (SQLException e){
              e.printStackTrace();  
              throw e;
       }finally{
            if (con!=null) con.close();
        }
        return b;
    }
     
//--------------------------------------------------------------------------------------------//     
   
    // Devuelve la velocidad de completitud para una curva (1.5*velocidad al 85%Pn)
    public double getVcompleta (ArrayList<double[]> curva) {
        
        double vcompleta=0;     
        double pn=curva.get(curva.size()-1)[1];
        double pot=pn*85/100;
          
        // Se busca el bin del 95%Pn
        int i=0;      
        double v1=0;
        double v0=0;
        double p1=0;
        double p0=0;
        boolean encontrado=false;
        int n_bines=curva.size();
        while (i<n_bines && !encontrado){
            if (curva.get(i)[1]>=pot){
                encontrado=true;
                v1=curva.get(i)[0];
                v0=curva.get(i-1)[0];
                p1=curva.get(i)[1];
                p0=curva.get(i-1)[1];   
            }
            i++;
        }
        if (encontrado) {
            vcompleta=1.5*((v1-v0)*(pot-p0)/(p1-p0)+v0);              
        }
     
        return (vcompleta);
     }

//--------------------------------------------------------------------------------------------//

     // Devuelve la velocidad de completitud de fgw para una curva (1.3*velocidad al 85%Pn)
    public double getVfgw(ArrayList<double[]> curva){
        
        double vcompleta=0;
        double pn=0;
        if (curva.size()!=0){
            pn=curva.get(curva.size()-1)[1];
        }
        double pot=pn*85/100;
          
        // Se busca el bin del 95%Pn
        int i=0;      
        double v1=0;
        double v0=0;
        double p1=0;
        double p0=0;
        boolean encontrado=false;
        int n_bines=curva.size();
        while (i<n_bines && !encontrado){
            if (curva.get(i)[1]>=pot){
                encontrado=true;
                v1=curva.get(i)[0];
                v0=curva.get(i-1)[0];
                p1=curva.get(i)[1];
                p0=curva.get(i-1)[1];   
            }
            i++;
        }
        if (encontrado) {
            vcompleta=1.3*((v1-v0)*(pot-p0)/(p1-p0)+v0);              
        }
     
        return (vcompleta);
     }

//--------------------------------------------------------------------------------------------//
      
    // Devuelve un vector con las posibles densidades de curva de potencia 
    public ArrayList getDensidades(int asunto)throws SQLException{
        Connection con = null;       
        ArrayList <Double> densidad=new ArrayList <Double>();
        try{
            con = DriverManager.getConnection(url,user,pass);        
            PreparedStatement ps = con.prepareStatement("Select Distinct Densidad from Curva where Asunto=?");                 
            ps.setInt(1, asunto);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                densidad.add(rs.getDouble(1));
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
        return densidad;
    }
    
}

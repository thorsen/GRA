
package RA;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ruth_Cordon
 */

public class Responsable {

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
    
    // Añede un nuevo responsable a la BD. Devuelve true si ya existe
    public boolean NuevoResponsable(String nombre, String ape1, String ape2,String usuario, String contraseña, String mail,int rol)throws SQLException{
        Connection con = null;
        boolean b=false;
        try{            
            con=DriverManager.getConnection(url,user,pass); 
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("Select * from Responsable");   
            ResultSet rs=ps.executeQuery();
            int Id=0;             
            while (rs.next()){ // Se comprueba si existe
                Id=rs.getInt(1);
                if (rs.getString(2).toUpperCase().equals(nombre.toUpperCase())&&(rs.getString(3).toUpperCase().equals(ape1.toUpperCase()))){
                    b=true;                
                }
            }
            rs.close();
            ps.close();
            if (b==false){
                Statement stmt=con.createStatement();
                Id=Id+1;
                String sqli="Insert into Responsable values ("+Id+",'"
                        +nombre+"',";
                sqli=sqli+"'"+ape1+"',";
                if (ape2==null){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+ape2+"',";                    
                }  
                sqli=sqli+"'"+usuario+"',"; 
                sqli=sqli+"'"+contraseña+"',"; 
                sqli=sqli+"'"+mail+"',"; 
                sqli=sqli+"1,"; 
                sqli=sqli+rol+")"; 
                stmt.executeUpdate(sqli); 
                stmt.close();  
            }          
            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
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
    
    // Modifica el estado de activación de un responsable
    public boolean setResponsable(int res,boolean activo) throws SQLException, IOException {
        Connection con = null;
        boolean b=false;
        try{
            con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            FileWriter fw= new FileWriter("\\\\B2solar\\Datos\\Curva\\Usuarios\\update.log",true);
            fw.write("--------------------------------------------------------------------------------\n");
            fw.write("\n");
            Date hoy= new Date();
            SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy hh:mm");
            String formatohoy=formato.format(hoy);
            fw.write("Proceso de Modificación(Activacion/Desactivación de usuario) a: "+formatohoy+"\n");
            fw.write("Estado previo:\n");
            boolean act=this.isActivo(res);
            fw.write("Activo= ");
            if (act){
                fw.write("SI \n");
            } else {
                fw.write("NO \n");
            }
            Statement stmt = con.createStatement();
            String sql="Update Responsable set Activo=";
            if (activo){
                sql=sql+"1";
            }else{
                sql=sql+"0";                 
            }
            sql=sql+" where Id_responsable="+res;
            System.out.println(sql);  
            int n=stmt.executeUpdate(sql); 
            if (n==1){
                 b=true;
            }
            stmt.close();
            con.commit();
            fw.write("Intrucción:\n");
            fw.write(sql);
            fw.write("\n");
            fw.write("Fin de Modificación\n");
            fw.close();
            con.close();            
       }catch(SQLException e){            
            e.printStackTrace(); 
            throw e;
       }finally{             
            if (con!=null){con.close();}
       }
       return b;        
    }   

//--------------------------------------------------------------------------------------------//       
    
    // Devuelve el identificador de un responsable. 0 si no existe
    public int getId(String nombre, String ape1)throws SQLException{
        Connection con = null;       
        int id=0;
        try{
            con=DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Id_responsable from Responsable where Nombre=? and Apellido1=? order by Id_responsable");            
            ps.setString(1,nombre);
            ps.setString(2,ape1);
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
    
    // Devuelve el nombre y apellidos de un responsable. Cadena vacia si no existe
    public String getNombre(int responsable)throws SQLException{
        Connection con = null;       
        String nombre="";
        try{
            con=DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Nombre,Apellido1,Apellido2 from Responsable where Id_responsable=?");            
            ps.setInt(1, responsable);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                nombre=rs.getString(1)+" ";
                nombre=nombre+rs.getString(2)+" ";
                String ape2=rs.getString(3);
                if (!rs.wasNull()){
                    nombre=nombre+ape2;
                }
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
    
    // Devuelve el mail del responsable. Vacio si no existe
    public String getMail(int responsable)throws SQLException{
        Connection con = null;       
        String mail="";
        try{
            con=DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Mail from Responsable where Id_responsable=?");            
            ps.setInt(1, responsable);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                mail=rs.getString(1);     
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
        return mail.trim();
    }
  
//--------------------------------------------------------------------------------------------//       
    
     // Devuelve si un responsable está activo
    public boolean isActivo(int responsable)throws SQLException{
        Connection con = null;       
        boolean activo=false;
        try{
            con=DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Activo from Responsable where Id_responsable=?");            
            ps.setInt(1, responsable);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                activo=rs.getBoolean(1);     
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
        return activo;
    }
    
//--------------------------------------------------------------------------------------------//           
    
    // Vector con los responsables de ensayo. Nombre Ape1 Ape2
    public ArrayList getResponsables()throws SQLException{
        Connection con = null;       
        ArrayList <String> nombres=new ArrayList <String>(); 
        try{
            con=DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Nombre, Apellido1, Apellido2 from Responsable");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                String nombre=rs.getString(1);
                String ape1=rs.getString(2);
                String ape2=rs.getString(3);
                if (rs.wasNull()){
                    ape2="";
                } 
                nombres.add(nombre+" "+ape1+" "+ape2);
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
        return nombres;
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Vector con los responsables de ensayo en activo
    public ArrayList getResponsablesActivo()throws SQLException{
        Connection con = null;       
        ArrayList <String> Nombres=new ArrayList <String>(); 
        try{
            con=DriverManager.getConnection(url,user,pass);           
            PreparedStatement ps = con.prepareStatement("Select Nombre, Apellido1, Apellido2 from Responsable where Activo=1");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                String nombre=rs.getString(1);
                String ape1=rs.getString(2);
                String ape2=rs.getString(3);
                if (rs.wasNull()){
                    ape2="";
                } 
                Nombres.add(nombre+" "+ape1+" "+ape2);
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
        return Nombres;
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Devuelve el rol de un responsable
    public int getRol(int responsable)throws SQLException{
        Connection con = null;       
        int rol=0;
        try{
            con=DriverManager.getConnection(url,user,pass);           
            PreparedStatement ps = con.prepareStatement("Select Rol from Responsable where Id_responsable=?");            
            ps.setInt(1, responsable);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                rol=rs.getInt(1);
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
        return rol;
    }
    
//--------------------------------------------------------------------------------------------//
    
}

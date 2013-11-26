
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

public class Cliente {

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
    
    //Inserta un nuevo cliente en la base de datos. Devuleve true si el cliente ya existía
    public boolean NuevoCliente(String nombre, String CIF, String direccion, String telefono,  String fax, String web,ArrayList<String[]> contactos )throws SQLException{
        Connection con = null;
        boolean b=false;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("Select Id_cliente,Nombre,CIF from Cliente order by Id_cliente");   
            ResultSet rs=ps.executeQuery();
            int Id=0;             
            while (rs.next()){ // Se comprueba si existe
                Id=rs.getInt(1);
                String name=rs.getString(2).toUpperCase();
                if ((name.equals(nombre.toUpperCase())) | (rs.getString(3).equals(CIF))){
                    b=true;                
                }
            }
            rs.close();
            ps.close();
            nombre=nombre.replace("'","");
            if (direccion!=null){
                direccion=direccion.replace("'","");
            }
            if (b==false){
                Statement stmt=con.createStatement();
                Id=Id+1;
                String sqli="Insert into Cliente values ("+Id+",";
                sqli=sqli+"'"+nombre+"',";
                sqli=sqli+"'"+CIF+"',";
                if (direccion==null){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+direccion+"',";                    
                }
                if (telefono==null){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+telefono+"',";                    
                }
                if (fax==null){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+fax+"',";                    
                }
                if (web==null){
                    sqli=sqli+"null)";
                }else{
                    sqli=sqli+"'"+web+"')";                    
                }
                System.out.println(sqli);
                stmt.executeUpdate(sqli); 
                PreparedStatement pss = con.prepareStatement("Select max(Id_contacto) from Contacto");   
                ResultSet rss=pss.executeQuery();
                int Idc=0;             
                if (rss.next()){ 
                    Idc=rss.getInt(1);
                }
                rss.close();
                pss.close();
                Idc=Idc+1;
                for (int i=0;i<contactos.size();i++){
                    String sqli2="Insert into Contacto values ("+(Idc+i)+",";
                    sqli2=sqli2+"'"+contactos.get(i)[0]+"',";
                    sqli2=sqli2+Id+",";
                    sqli2=sqli2+"'"+contactos.get(i)[1]+"',";
                    if (contactos.get(i)[2]==null){
                        sqli2=sqli2+"null,";
                    }else{
                        sqli2=sqli2+"'"+contactos.get(i)[2]+"',";                    
                    }
                    sqli2=sqli2+"null,";          
                    sqli2=sqli2+"null,0,0)";
                    System.out.println(sqli2);
                    stmt.executeUpdate(sqli2); 
                }
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

    // Devuelve el id de cliente a través del nombre. 0 si no existe
    public int getId(String nombre)throws SQLException{
        Connection con = null;       
        int id=0;
        try{
            con = DriverManager.getConnection(url,user,pass);            
            PreparedStatement ps = con.prepareStatement("Select Id_cliente from Cliente where Nombre=?");            
            ps.setString(1,nombre);
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
    
    // Devuelve el nombre de un cliente. Cadena vacia si no existe.
    public String getNombre(int cliente)throws SQLException{
        Connection con = null;       
        String nombre="";
        try{
            con = DriverManager.getConnection(url, user, pass);            
            PreparedStatement ps = con.prepareStatement("Select Nombre from Cliente where Id_cliente=?");            
            ps.setInt(1, cliente);
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
    
    // Devuelve la lista de clientes existentes en la base de datos
    public ArrayList getClientes()throws SQLException{
        Connection con = null;       
        ArrayList <String> clientes=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url, user, pass);     
            PreparedStatement ps = con.prepareStatement("Select Distinct Nombre from Cliente");            
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                clientes.add(rs.getString(1));
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
    return clientes;
    }
    
//--------------------------------------------------------------------------------------------//   
       
     // Devuelve la direccion de un cliente. Cadena vacia si no existe.
    public String getDireccion(int cliente)throws SQLException{
        Connection con = null;       
        String direccion="";
        try{
            con = DriverManager.getConnection(url, user, pass);            
            PreparedStatement ps = con.prepareStatement("Select Direccion from Cliente where Id_cliente=?");            
            ps.setInt(1, cliente);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                direccion=rs.getString(1);
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
    return direccion;
    }
    
//--------------------------------------------------------------------------------------------//   
    
    // Devuelve el CIF de un cliente. Cadena vacia si no existe.
    public String getCIF(int cliente)throws SQLException{
        Connection con = null;       
        String cif="";
        try{
            con = DriverManager.getConnection(url, user, pass);            
            PreparedStatement ps = con.prepareStatement("Select CIF from Cliente where Id_cliente=?");            
            ps.setInt(1, cliente);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                cif=rs.getString(1);
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
    return cif;
    }
    
//--------------------------------------------------------------------------------------------//   

    // Devuelve todos los campos de un cliente.
    public String[] getCliente(int cliente) throws SQLException {
       Connection con = null;             
       String[] datos=new String[7];
        try{            
            con = DriverManager.getConnection(url, user, pass);         
            PreparedStatement ps = con.prepareStatement("Select * from Cliente where Id_Cliente=?");            
            ps.setInt(1, cliente);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                datos[0]=String.valueOf(rs.getInt(1)); // Id
                datos[1]=rs.getString(2); // Nombre
                datos[2]=rs.getString(3); // CIF
                datos[3]=rs.getString(4); // Direccion
                datos[4]=rs.getString(5); // Telefono
                datos[5]=rs.getString(6); // Fax
                datos[6]=rs.getString(7); // Web
            }
            rs.close();
            ps.close();
            con.close();
        }catch(SQLException e){            
            e.printStackTrace();            
        }finally{
            if (con!=null){con.close();}   
        }
        return datos;
    }

//--------------------------------------------------------------------------------------------//    

    // Inserta un nuevo contacto de un cliente en la base de datos. Devuleve true si el contacto ya existía
    public boolean NuevoContacto(String nombre, int cliente, String mail, String telefono,boolean interno)throws SQLException{
        Connection con = null;
        boolean b=false;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("Select Id_contacto,Nombre from Contacto order by Id_contacto");   
            ResultSet rs=ps.executeQuery();
            int Id=0;             
            while (rs.next()){ // Se comprueba si existe
                Id=rs.getInt(1);
                String name=rs.getString(2).toUpperCase();
                if (name.equals(nombre.toUpperCase())) {
                    b=true;                
                }
            }
            rs.close();
            ps.close();
            nombre.replace("'","");
            if (b==false){
                Statement stmt=con.createStatement();
                Id=Id+1;
                String sqli="Insert into Contacto values ("+Id+",";
                sqli=sqli+"'"+nombre+"',";
                sqli=sqli+cliente+",";
                sqli=sqli+"'"+mail+"',";
                if (telefono.equals("")){
                    sqli=sqli+"null,";
                }else{
                    sqli=sqli+"'"+telefono+"',";                    
                }
                sqli=sqli+"null,null,0,";
                if (interno){
                    sqli=sqli+"1,1)";
                } else{
                    sqli=sqli+"0,1)";
                }
                System.out.println(sqli);
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
    
    // Devuelve la lista de contactos asociados a un cliente en la base de datos
    public ArrayList getContactos(int cliente,boolean activo)throws SQLException{
        Connection con = null;       
        ArrayList <String> contactos=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url, user, pass);     
            String sql="Select Distinct Nombre from Contacto where Cliente=?";
            if (activo){
                sql=sql+" and Activo=1";
            }
            System.out.println(sql);
            PreparedStatement ps = con.prepareStatement(sql);         
            ps.setInt(1, cliente);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                contactos.add(rs.getString(1));
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
    return contactos;
    }
    
//--------------------------------------------------------------------------------------------//   
    
    // Devuelve los datos de un contacto
    public ArrayList getContacto(int contacto)throws SQLException{
        Connection con = null;       
        ArrayList <String> datos=new ArrayList <String>();
        try{
            con = DriverManager.getConnection(url, user, pass);     
            PreparedStatement ps = con.prepareStatement("Select * from Contacto where Id_contacto=?");            
            ps.setInt(1, contacto);
            ResultSet rs=ps.executeQuery();                       
            while (rs.next()){
                datos.add(0,String.valueOf(rs.getInt(1))); // Id
                datos.add(1,rs.getString(2)); // Nombre
                datos.add(2,String.valueOf(rs.getInt(3))); // Cliente
                datos.add(3,rs.getString(4)); // Mail
                datos.add(4,rs.getString(5)); // Telefono
                datos.add(5,rs.getString(6)); // Usuario
                datos.add(6,rs.getString(7)); // Contraseña
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
    return datos;
    }
    
//--------------------------------------------------------------------------------------------//   
    
    // Devuelve el identificador de contacto
    public int getIdContacto(String nombre)throws SQLException{
        Connection con = null;       
        int contacto=0;
        try{
            con = DriverManager.getConnection(url, user, pass);     
            PreparedStatement ps = con.prepareStatement("Select Id_contacto from Contacto where Nombre=?");            
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();                       
            if (rs.next()){
                contacto=rs.getInt(1);
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
    return contacto;
    }
    
//--------------------------------------------------------------------------------------------//   

    // Devuelve el mail de la persona de contacto . Null si no existe
    public String getMail(int contacto)throws SQLException{
        Connection con = null;
        String mail=null;
        try{            
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = con.prepareStatement("Select Mail from Contacto where Id_contacto=?"); 
            ps.setInt(1, contacto);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                mail=rs.getString(1);      
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
        return (mail);
    }
    
//--------------------------------------------------------------------------------------------//

// Alta web de contacto
public boolean getAlta(int contacto,String usuario, String contraseña) throws SQLException {
    Connection con = null;
    boolean b=false;
    try{
        con = DriverManager.getConnection(url, user, pass);
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        String sql="Update Contacto set Usuario='"+usuario+"',contraseña='"+contraseña+"',Web=1";
            sql=sql+" where Id_contacto="+contacto;
            System.out.println(sql);
            stmt.executeUpdate(sql); 
            b=true;
            stmt.close();
            con.commit();
            con.close();            
        }catch(SQLException e){            
            e.printStackTrace();
        }finally{             
            if (con!=null){con.close();}    
        }
        return b;        
    }    
    
//--------------------------------------------------------------------------------------------//    

  // Devuelve si un contacto esta o no dado de alta en la web
  public boolean getAlta(int contacto) throws SQLException {
    Connection con = null;
    boolean web=false;
    try{
        con = DriverManager.getConnection(url, user, pass);
        PreparedStatement ps = con.prepareStatement("Select Web from Contacto where Id_contacto=?");
        ps.setInt(1, contacto);
        ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                web=rs.getBoolean(1);      
            }
            rs.close();
            ps.close();
            con.close(); 
        }catch(SQLException e){            
            e.printStackTrace();
        }finally{             
            if (con!=null){con.close();}    
        }
        return web;        
    }    
    
//--------------------------------------------------------------------------------------------//      
}


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

public class Login {
    
    public static String usuario;
    public static String contraseña;
    public static int rol;
    private static int id;
    
    private static String url = "jdbc:sqlserver://" + Global.IP_SERVER_GCP;
    //private static String url = "jdbc:sqlserver://localhost";
    private static String user = Global.USER_SERVER_GCP;
    private static String pass = Global.PASS_SERVER_GCP;
    
    static{
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); 
        }catch(ClassNotFoundException e){
            System.out.println("No puedo cargar el driver ODBC de la BD");
        }
    }
    
//--------------------------------------------------------------------------------------------//
    
    // Crea un usuario
    public Login(String u,String c){
        usuario=u;
        contraseña=c;

    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Valida un usuario
    public boolean IsValido() throws SQLException{
        boolean b=false;
        Connection con = null; 
        try{
            con = DriverManager.getConnection(url,user,pass);
            String sql="Select * from Responsable where Activo=1";  
            PreparedStatement ps = con.prepareStatement(sql);     
            ResultSet rs=ps.executeQuery();
            while ((rs.next())& (!b)){
                int aux=rs.getInt(1);
                if ((rs.getString(5).equals(usuario))&&(rs.getString(6).equals(contraseña))){
                    rol=rs.getInt(9);
                    id=aux;
                    b=true;  
                } else {
                    b=false;
                }
            }
            rs.close();
            ps.close();  
            con.close();    
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                if (con!=null) con.close();  
            }catch (SQLException e){
                e.printStackTrace();
            }
             
        }
        return b;
    }
    
//--------------------------------------------------------------------------------------------//    
    public static String getUsuario(){return usuario;} // Devuelve el usuario del usuario
    public static String getContraseña(){return contraseña;} // Devuelve contraseña de usuario
    public static int getRol(){return rol;} // Devuelve rol de usuario
    public static int getId(){return id;} // Devuelve Id de usuario
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RA;

import RA.FechaRA;
import RA.FicheroRA;
import RA.ConfiguracionRA;
import RA.DatosRA;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;




public class Main {
    
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
    
         //funcion para encriptar contraseña.
    private static String hash(String clear) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        StringBuffer h = new StringBuffer(size);

        for (int i=0;i<size;i++)
        {
            int u = b[i]&255;
            if (u<16)
            {
                h.append("0"+Integer.toHexString(u));
            }
            else
            {
                h.append(Integer.toHexString(u));
            }
        }

        return h.toString();
    }

    public static String encriptarMD5(String palabra)
    {
        String pe = "";

        try
        {
            pe = hash(palabra);
        }
        catch (Exception e)
        {
            throw new Error("Error: Al encriptar el password");
        }
        return pe;
    }
    
public static void main(String[] args) throws Exception {
  
   
    FicheroRA FI=new FicheroRA();
    FechaRA F=new FechaRA();
    DatosRA D=new DatosRA();
    ConfiguracionRA C=new ConfiguracionRA();
    try  { 
        
       System.out.println(F.sumaFecha("27072010 125422.86",60.16));
      //  C.getNumeroConfiguracion(146,101, "29072010 114320.00");
//        FI.LeerCanales("Z:\\RUTH\\PUBLICA\\SLM\\SLM.txt");
//        String fecha=FI.LeerFecha("Z:\\RUTH\\PUBLICA\\SLM\\SLM.txt");
//       
//        ArrayList<ArrayList<String>> datos=FI.LeerSPL("Z:\\RUTH\\PUBLICA\\SLM\\SLM.txt", fecha, 8);
    //            D.insercionSPL(146, 101, 0, datos);
//    }catch (SQLException e)   { 
//        e.printStackTrace();
//      
    }catch (Exception e)   { 
        e.printStackTrace();
    }  

}
}

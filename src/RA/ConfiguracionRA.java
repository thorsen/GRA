
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

public class ConfiguracionRA {

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

    // Devuelve la descripcion de tabla de un asunto (codigos)
    public ArrayList getDescripcion(int asunto, String tabla) throws SQLException {
      Connection con = null;             
      ArrayList <String> codigos=new ArrayList <String>();
      try{            
          con = DriverManager.getConnection(url, user, pass);   
          
          String sql="Select SerieRA.Codigo from "+tabla+",SerieRA where "+tabla+".Asunto=? and "+tabla+".Serie=SerieRA.Id_serie";
          PreparedStatement ps = con.prepareStatement(sql);  
          ps.setInt(1, asunto);
          ResultSet rs=ps.executeQuery();                       
          while (rs.next()){
               codigos.add(rs.getString(1));
          }
          rs.close();
          ps.close();
          con.close();
      }catch(SQLException e){            
           e.printStackTrace();
           throw e;             
      }finally{
          if (con!=null){con.close();}
      }     
      return codigos;
    } 

//--------------------------------------------------------------------------------------------//    

    // Devuelve el numero de configuracion de un site válido para una fecha. Si no existe devuelve -1
    public int getNumeroConfiguracion(int asunto, int site,String fecha)throws SQLException{
        
        Connection con = null;
        int numero=0;
        
        try{
            FechaRA F=new FechaRA();
            
            con = DriverManager.getConnection(url,user,pass); 
            
            // Se busca la configuración de este site para esa fecha
            String sql="Select * from ConfiguracionRA where asunto=? and Site=? order by Nconfig";
            PreparedStatement ps = con.prepareStatement(sql);   
            ps.setInt(1, asunto); ps.setInt(2, site);
            ResultSet rs=ps.executeQuery();
           
            String fini="";
            String ffin="";
            
            boolean encontrado=false; // estado de la búsqueda del nº de configuración
            while ((rs.next()) && (encontrado==false)){
                numero=rs.getInt(3); 
                fini=rs.getString(4);
                ffin=rs.getString(5);
                if (rs.wasNull()){
                    ffin=null;
                }
                if (F.ComparaFechas(fini,fecha)<=0){
                    if ((ffin==null) || (F.ComparaFechas(fecha,ffin)<=0)){
                        encontrado=true;
                    }
                }
            }
            if (encontrado==false){
                numero=-1;
            }
            System.out.println("Nº de configuración "+numero);
            rs.close();
            ps.close();
            con.close();
         }catch (SQLException e){
            e.printStackTrace();
            throw e;
         }finally{
            if (con!=null) con.close();
        }
        return numero;
    }

    
 //--------------------------------------------------------------------------------------------//    

    // Devuelve las lineas de una configuración de ensayo de ruido
    public ArrayList getLineas(int asunto, int site, int n) throws SQLException {
      
        Connection con = null;             
        ArrayList <String []> lineas=new ArrayList <String []>();
        
        try{            
            
            con = DriverManager.getConnection(url, user, pass);          
            
            PreparedStatement ps = con.prepareStatement("Select * from LineaConfiguracionRA where Asunto=? and Site=? and Nconfig=? order by Serie");  
            ps.setInt(1, asunto);
            ps.setInt(2, site);
            ps.setInt(3, n);
            ResultSet rs=ps.executeQuery();                       

            String [] Linea=new String [10];
            while (rs.next()){
                
                Linea=new String [10];
                Linea [0]=String.valueOf(rs.getInt(4)); // Serie
                String equipo=rs.getString(5); // Equipo
                if (rs.wasNull()) {
                    Linea [1]=null;
                }else {
                    Linea [1]=equipo;
                }
                Linea [2]=rs.getString(6); // Canal
                double c=rs.getDouble(7); // Cota
                if (rs.wasNull()) {
                    Linea [3]=null;
                }else {
                    Linea [3]=String.valueOf(c);
                }
                String cer=rs.getString(8);
                if (cer.equals("-")){
                     Linea [4]=null;// Certificado
                     Linea [5]=null;// Slope
                     Linea [6]=null;// Offset
                } else {
                     Linea [4]=cer;
                     Linea [5]=String.valueOf(rs.getDouble(9));
                     Linea [6]=String.valueOf(rs.getDouble(10));
                }
                Linea [7]=String.valueOf(rs.getDouble(11));// Slopep
                Linea [8]=String.valueOf(rs.getDouble(12));// Offsetp
                double o=rs.getDouble(13);// Orientacion
                if (rs.wasNull()) {
                    Linea [9]=null;
                }else {
                    Linea [9]=String.valueOf(o);
                }
                lineas.add(Linea);

          }
          rs.close();
          ps.close();
          con.close();
      }catch(SQLException e){            
           e.printStackTrace();
           throw e;             
      }finally{
           if (con!=null){con.close();}
      }     
      return lineas;
    } 

//--------------------------------------------------------------------------------------------//       
    
    // Devuelve la declinacion magnetica de una configuracion en modo numerico
    public double getDeclinacion(int asunto, int site, int numero) throws SQLException {
        Connection con = null;             
        double declinacion=0;
        try{            
            
            con = DriverManager.getConnection(url, user, pass);        
            
            PreparedStatement ps = con.prepareStatement("Select Angulo,Posicion from DeclinacionRA where Asunto=? and Site=? and Nconfig=?");  
            ps.setInt(1, asunto);
            ps.setInt(2, site);
            ps.setInt(3, numero);
            
            ResultSet rs=ps.executeQuery();  
            String[] curva=new String[2];
            if (rs.next()){
                curva[0]=""+rs.getDouble(1);
                curva[1]=rs.getString(2);
            }
            rs.close();
            ps.close();
            con.close();
            
            if ((curva[0]!=null) && (curva[1]!=null)){
                declinacion=Double.parseDouble(curva[0]);           
                if (curva[1].equals("W")){
                    declinacion=declinacion*(-1);
                }
            }
       }catch(SQLException e){            
            e.printStackTrace();
            throw e;             
       }finally{
            if (con!=null){con.close();}        
       }      
       return declinacion;
    }
    
//--------------------------------------------------------------------------------------------//    
    
    // Devuelve la cota de una serie para una configuración 
    public double getcota(int asunto, int site, int nconfig, int serie)throws SQLException{
        
        Connection con = null;
        double cota=-1;
        
        try{
            con = DriverManager.getConnection(url,user,pass); 
            
            PreparedStatement ps = con.prepareStatement("Select cota from LineaConfiguracionRA where Asunto=? and Site=? and NConfig=? and Serie=?");   
            ps.setInt(1, asunto); ps.setInt(2, site); ps.setInt(3, nconfig); ps.setInt(4, serie);
            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                cota=rs.getDouble(1);
            }
            rs.close();
            ps.close();
            con.close();
         } catch (SQLException e){
            e.printStackTrace();
            throw e;
         } finally{        
           if (con!=null) con.close();           
        }
        return cota;
    }

//--------------------------------------------------------------------------------------------//    
    
     // Devuelve la matriz de cotas de la serie VZ para cada configuracion (inicio,fin,cota) 
    public ArrayList<double[]> getCotas(int asunto, int site, int serie)throws SQLException{
        
        Connection con = null;
        ArrayList<double[]> cotas=new ArrayList<double[]>();
        
        try{
            DatosRA D=new DatosRA();
            
            String tabla="DatosSPL";
            if (site==105){tabla="DatosOCT";}
            con = DriverManager.getConnection(url,user,pass); 
            String sql="Select ConfiguracionRA.F_inicio,ConfiguracionRA.F_fin,LineaconfiguracionRA.Cota " +
                    "from LineaconfiguracionRA,ConfiguracionRA " +
                    "where ConfiguracionRA.Asunto=LineaConfiguracionRA.Asunto and " +
                    "ConfiguracionRA.Site=LineaConfiguracionRA.Site and " +
                    "ConfiguracionRA.NConfig=LineaCOnfiguracionRA.NConfig and " +
                    "ConfiguracionRA.asunto=? and " +
                    "ConfiguracionRA.site=? and " +
                    "LineaConfiguracionRA.serie=?";
            PreparedStatement ps = con.prepareStatement(sql);   
            ps.setInt(1, asunto); ps.setInt(2, site);ps.setInt(3, serie);
            ResultSet rs=ps.executeQuery();
  
            while (rs.next()) {
                double[] dato=new double[3];
                String fini=rs.getString(1);
                
                dato[0]=D.getId(asunto, fini, tabla);
                String ffin=rs.getString(2);
                if (rs.wasNull()){
                   dato[1]=10000000;
                } else{
                   dato[1]=D.getId(asunto, ffin, tabla);
                }
                dato[2]=rs.getDouble(3);
                System.out.println(dato[0]+" "+dato[1]+" "+dato[2]);
                cotas.add(dato);
            }
            rs.close();
            ps.close();
            con.close();
            
         } catch (SQLException e){
            e.printStackTrace();
            throw e;
         } finally{        
           if (con!=null) con.close();           
        }
        return cotas;
    }

//--------------------------------------------------------------------------------------------//    
    
    // Devuelve los codigos de un tipo de un asunto (codigos)
    public ArrayList getDescripcion(int asunto,String tabla,int tipo) throws SQLException {
      Connection con = null;             
      ArrayList <String> codigos=new ArrayList <String>();
      try{            
          con = DriverManager.getConnection(url, user, pass);   
          
          String sql="Select SerieRA.Codigo from "+tabla+",SerieRA where "+tabla+".Asunto=? and SerieRA.Tipo=? and "+tabla+".Serie=SerieRA.Id_serie ";
          PreparedStatement ps = con.prepareStatement(sql);  
          ps.setInt(1, asunto);
          ps.setInt(2, tipo);
          ResultSet rs=ps.executeQuery();                       
          while (rs.next()){
               codigos.add(rs.getString(1));
          }
          rs.close();
          ps.close();
          con.close();
      }catch(SQLException e){            
           e.printStackTrace();
           throw e;             
      }finally{
          if (con!=null){con.close();}
      }     
      return codigos;
    } 

//--------------------------------------------------------------------------------------------//    

 // Inserta una nueva descripcion para un asunto y crea las tablas de datos asociadas
 public boolean NuevaDescripcion(int asunto,ArrayList<String> codigosNOTAC,ArrayList<String> codigosSPL,ArrayList<String> codigosOCT)throws SQLException{
        Connection con = null;
        SerieRA S=new SerieRA();
        boolean b=false;  
        try{   
            con = DriverManager.getConnection(url, user, pass); 
            con.setAutoCommit(false);
            
            String sql_SPL="Insert into DescripcionSPL values (?,?)";
            String sql_OCT="Insert into DescripcionOCT values (?,?)";
            PreparedStatement ps_SPL = con.prepareStatement(sql_SPL);
            PreparedStatement ps_OCT = con.prepareStatement(sql_OCT);
            
            int n1=codigosNOTAC.size();
            int n2=codigosSPL.size();
            int n3=codigosOCT.size();
            // No acústicas
            for (int i=0;i<n1;i++){
                int serie=S.getSerie(codigosNOTAC.get(i));
                ps_SPL.setInt(1, asunto);
                ps_OCT.setInt(1, asunto);
                ps_SPL.setInt(2,serie );
                ps_OCT.setInt(2,serie );
                System.out.println("Insert into DescripcionSPL values ("+asunto+","+serie+")");
                System.out.println("Insert into DescripcionOCT values ("+asunto+","+serie+")");
                ps_SPL.execute();
                ps_OCT.execute();
            }
            // SPL
            for (int i=0;i<n2;i++){
                int serie=S.getSerie(codigosSPL.get(i));
                ps_SPL.setInt(1, asunto);
                ps_SPL.setInt(2,serie );
                System.out.println("Insert into DescripcionSPL values ("+asunto+","+serie+")");
                ps_SPL.execute();
            }
            // OCT
            for (int i=0;i<n3;i++){
                int serie=S.getSerie(codigosOCT.get(i));
                ps_OCT.setInt(1, asunto);
                ps_OCT.setInt(2,serie );
                System.out.println("Insert into DescripcionOCT values ("+asunto+","+serie+")");
                ps_OCT.execute();
            }
            ps_SPL.close();  
            ps_OCT.close();  
            
            // Tablas
            String sql1 = "CREATE TABLE "+"DatosSPL".concat(String.valueOf(asunto))+"(Id int,Fecha_Hora char(18) not null," +
                    "Valido int default 1," +
                    "RF int default 0"; 
            
            Statement stmt = con.createStatement();   
            for (int i=0;i<n1;i++){
                 String codigo=codigosNOTAC.get(i);       
                 sql1=sql1.concat(","+codigo+" decimal(18,5) default 8888.88");          
            }
            for (int i=0;i<n2;i++){
                 String codigo=codigosSPL.get(i);       
                 sql1=sql1.concat(","+codigo+" decimal(18,5) default 8888.88");          
            }
            sql1=sql1.concat(",Tbuje decimal(18,5) default 8888.88");
            sql1=sql1.concat(",Pbuje decimal(18,5) default 8888.88");
            sql1=sql1.concat(", primary key(Id))");  
            System.out.println(sql1);
            stmt.executeUpdate(sql1);  
            
            String sql2 = "CREATE TABLE "+"DatosOCT".concat(String.valueOf(asunto))+"(Id int,Fecha_Hora char(18) not null," +
                    "Valido int default 1," +
                    "RF int default 0"; 
           
            for (int i=0;i<n1;i++){
                 String codigo=codigosNOTAC.get(i);       
                 sql2=sql2.concat(","+codigo+" decimal(18,5) default 8888.88");          
            }
            for (int i=0;i<n3;i++){
                 String codigo=codigosOCT.get(i);       
                 sql2=sql2.concat(","+codigo+" decimal(18,5) default 8888.88");          
            }
            sql2=sql2.concat(",Tbuje decimal(18,5) default 8888.88");
            sql2=sql2.concat(",Pbuje decimal(18,5) default 8888.88");
            sql2=sql2.concat(", primary key(Id))");  
            System.out.println(sql2);
            stmt.executeUpdate(sql2);  
            
            stmt.close();
            con.commit();
            con.close();
            b=true;    
        }catch (SQLException e){
               e.printStackTrace();
               con.rollback();
               throw e;
        }
        finally{
            try {
              if (con!=null) 
                  con.close();
            } catch (SQLException e){
               e.printStackTrace();
            }
        }
        return b;
    }

//--------------------------------------------------------------------------------------------//    
}